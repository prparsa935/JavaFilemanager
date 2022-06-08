package com.example.uitest;

import Controller.Controller;
import filemodel.fileentity.Fileenti;
import filemodel.fileservice.Fileserv;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class Fm1Controller implements Initializable {
    Fileserv fileserv;
    static Fileenti current_file;


    Controller controller=new Controller();
    InputStream FolderStream;
    InputStream pictureStream;
    InputStream videoStream;
    InputStream appStream;
    InputStream pdfStream;
    InputStream fileStream;
    Image Folderimage;
    Image pictureimage;
    Image videoimage;
    Image appimage;
    Image pdfimage;
    Image fileimage ;
    ContextMenu MainContextMenu;
    @FXML
    private Button del;

    @FXML
    private Button edit;

    @FXML
    private Label fm;

    @FXML
    private Pane pane;

    @FXML
    private TextField search;
    @FXML
    private FlowPane FlowPane;
    @FXML
    private FlowPane List;
    @FXML
    private ScrollPane ScBar;
    @FXML
    private Button BackButt;
    private void SetIcon(Image image,Fileenti file){
        String fileformat=file.getFormat();
        ImageView ImageView=new ImageView();
        ImageView.setImage(image);
        Button B=new Button();
        B.setText(file.getName());
        B.setGraphic(ImageView);
        List.getChildren().add(B);
        B.setContentDisplay(ContentDisplay.TOP);
//                B.getStyleClass().add("bb");
        if(!fileformat.equals("Drive")){
            MenuItem rename=new MenuItem("rename");
            MenuItem delete=new MenuItem("delete");
            final ContextMenu CM=new ContextMenu();
            CM.getItems().addAll(rename,delete);
            delete.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(fileformat.equals("")){
                        try {
                            System.out.println("here");
                            controller.deleteDir(current_file.getPath()+"\\"+file.getName());
                            fileserv.remove(file);
                            List.getChildren().remove(B);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                    else if(!fileformat.equals("")&&!fileformat.equals("Drive")){

                        try {
                            controller.deletefile(current_file.getPath()+"\\"+file.getName());
                            fileserv.remove(file);
                            List.getChildren().remove(B);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            });

            rename.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        Parent root;
                        FXMLLoader Fxmlloader=new FXMLLoader(getClass().getResource("renamefield.fxml"));
                        root = Fxmlloader.load();
                        RenamefieldController renameC= Fxmlloader.getController();
                        String presentname=B.getText();
                        if(!fileformat.equals("")&&!fileformat.equals("Drive"))
                            renameC.getNewnamefield().setText(presentname.substring(0,presentname.lastIndexOf('.')));
                        else if(fileformat.equals("")){
                            renameC.getNewnamefield().setText(presentname);
                        }

                        //                    mainpane.getChildren().setAll(root);
                        Stage stage = new Stage();
                        stage.setTitle("rename");
                        stage.setScene(new Scene(root));
                        stage.show();
                        renameC.getSave().setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                String newfilename=renameC.getNewnamefield().getText();
                                System.out.println(newfilename);
                                String lastname=B.getText();
                                System.out.println(lastname);
                                if(!newfilename.equals("")){
                                    file.setName(newfilename+lastname.substring(lastname.lastIndexOf("."),lastname.length()));
                                    try {
                                        fileserv.edit(file);
                                        controller.renamefile(current_file.getPath()+"\\"+lastname,newfilename);
                                        stage.close();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    if(!fileformat.equals("")&&!fileformat.equals("Drive")){
                                        System.out.println(Controller.current_loc);
                                        B.setText(newfilename+lastname.substring(lastname.lastIndexOf("."),lastname.length()));

                                    }

                                    else if(fileformat.equals("")){
                                        B.setText(newfilename);

                                    }
                                }

                            }
                        });

                        // Hide this current window (if this is what you want)
    //                    ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            B.setContextMenu(CM);
        }

        B.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MainContextMenu.hide();
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount()==2){
                        if(!fileformat.equals("")&&!fileformat.equals("Drive")){
                            controller.runfile(current_file.getPath()+"\\"+B.getText());
                        }
                        else{
                            List.getChildren().clear();
                            OpenFolder(file);
                            current_file=file;
                        }

                    }
                }
            }
        });

    }
    private String getfileformat(String filename){
        try {
            return filename.substring(filename.lastIndexOf('.')+1,filename.length());
        }catch (Exception e){

        }
        return null;

    }


    private void OpenFolder(Fileenti folder){
        try {
            List<Fileenti>files=fileserv.open_Folder(folder.getId());
//            HashMap<String,String> f=controller.open_dir(folder.getPath());
            for(Fileenti file :files){
                String fileformat = "";
                try {
                    fileformat=file.getFormat();

                }
                catch (Exception e){


                }

                if (file.getFormat().equals("")||fileformat.equals("Drive")){
                    SetIcon(Folderimage,file);

                }
                else if(fileformat.equals("jpeg")||fileformat.equals("gif")||fileformat.equals("tiff")||fileformat.equals("jpg")){
                    SetIcon(pictureimage,file);

                }
                else if(fileformat.equals("exe")||fileformat.equals("bat")){
                    SetIcon(appimage,file);
                }
                else if(fileformat.equals("mp4")||fileformat.equals("mov")||fileformat.equals("wmv")||fileformat.equals("avi")){
                    SetIcon(videoimage,file);
                }
                else if(fileformat.equals("pdf")){
                    SetIcon(pdfimage,file);
                }
                else{
                    SetIcon(fileimage,file);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void back(){
        try {

            List.getChildren().clear();
//            current_file.getIn_Folder()!=-1
            if(true){
                System.out.println(current_file.getPath());
                current_file=fileserv.open_Folder_id(current_file.getIn_Folder());
                List<Fileenti>files=fileserv.open_Folder(current_file.getId());
//            HashMap<String,String> f=controller.back_dir();
                for(Fileenti file:files){
                    String fileformat = "";
                    try {
                        fileformat=file.getFormat();

                    }
                    catch (Exception e){


                    }

                    if (fileformat.equals("")||fileformat.equals("Drive")){
                        SetIcon(Folderimage,file);


                    }
                    else if(fileformat.equals("jpeg")||fileformat.equals("gif")||fileformat.equals("tiff")||fileformat.equals("jpg")){
                        SetIcon(pictureimage,file);

                    }
                    else if(fileformat.equals("exe")||fileformat.equals("bat")){
                        SetIcon(appimage,file);
                    }
                    else if(fileformat.equals("mp4")||fileformat.equals("mov")||fileformat.equals("wmv")||fileformat.equals("avi")){
                        SetIcon(videoimage,file);
                    }
                    else if(fileformat.equals("pdf")){
                        SetIcon(pdfimage,file);
                    }
                    else{
                        SetIcon(fileimage,file);
                    }


            }


            }
        } catch (FileNotFoundException e) {
            System.out.println("fohsh1");

        } catch (IOException e) {
            System.out.println("fohsh2");
        } catch (Exception e) {
            OpenFolder(new Fileenti().setId(1));

        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            fileserv=Fileserv.getInstance();
            current_file=new Fileenti();
            current_file.setId(1);
            current_file.setFormat("root");
//            System.out.println(current_file);

            FolderStream = new FileInputStream(getClass().getResource("icons8-folder-96.png").getPath());
            pictureStream = new FileInputStream(getClass().getResource("icons8-gallery-96.png").getPath());
            videoStream = new FileInputStream(getClass().getResource("icons8-video-96.png").getPath());
            appStream = new FileInputStream(getClass().getResource("icons8-application-window-96.png").getPath());
            pdfStream = new FileInputStream(getClass().getResource("icons8-pdf-80.png").getPath());
            fileStream = new FileInputStream(getClass().getResource("icons8-file-64.png").getPath());
            Folderimage = new Image(FolderStream);
            pictureimage = new Image(pictureStream);
            videoimage = new Image(videoStream);
            appimage = new Image(appStream);
            pdfimage = new Image(pdfStream);
            fileimage = new Image(fileStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OpenFolder(current_file);
        // Create the context menu items
        MenuItem createfolder = new MenuItem("Create Folder");
        createfolder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Parent root;
                    FXMLLoader Fxmlloader=new FXMLLoader(getClass().getResource("createfile.fxml"));
                    root = Fxmlloader.load();
                    CreatefileController Createfile= Fxmlloader.getController();
                    Stage stage = new Stage();
                    stage.setTitle("create new file/folder");
                    stage.setScene(new Scene(root));
                    stage.show();
                    Createfile.getCreateFile().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            String filename=Createfile.getNameFiled().getText();
                            Fileenti fileenti=new Fileenti();
                            fileenti.setName(filename);
                            fileenti.setIn_Folder(current_file.getId());
                            fileenti.setPath(current_file.getPath()+"\\"+filename);
                            Date date = new Date(System.currentTimeMillis());
                            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");

                            fileenti.setDatecreated(date);
                            System.out.println(formatter.format(fileenti.getDatecreated()));
                            if(filename.indexOf('.')!=-1){
                                fileenti.setFormat(filename.substring(filename.lastIndexOf('.')+1,filename.length()));
                                try {
                                    controller.createfile(fileenti.getPath());
                                    fileserv.save(fileenti);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            else{
                                fileenti.setFormat("");
                                try {
                                    controller.createfolder(fileenti.getPath());
                                    fileserv.save(fileenti);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            }
                            List.getChildren().clear();
                            OpenFolder(current_file);
                            stage.close();

                        }
                    });




                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });


        // Create a context (i.e., popup) menu that shows edit options.
        MainContextMenu = new ContextMenu(createfolder);
        pane.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent contextMenuEvent) {
                MainContextMenu.show(pane, contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY());

            }
        });
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MainContextMenu.hide();
            }
        });

        BackButt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                back();
            }
        });


//
//        try {
//            HashMap<String,String> f=controller.scan_files(Controller.current_loc);
//            InputStream stream = new FileInputStream(getClass().getResource("icons8-folder-96.png").getPath());
//            Image image = new Image(stream);
//            for(Map.Entry<String, String> set:f.entrySet()){
//                ImageView ImageView=new ImageView();
//                ImageView.setImage(image);
//                Button B=new Button();
//                B.setText(set.getKey());
//                B.setGraphic(ImageView);
//                List.getChildren().add(B);
////                B.setStyle("-fx-background-color: #f4f4f4; ");
//                B.setContentDisplay(ContentDisplay.TOP);
//                B.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent mouseEvent) {
//                        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
//                            if(mouseEvent.getClickCount()==2){
//                                List.getChildren().clear();
//                                OpenFolder(B.getText());
//                            }
//
//                        }
//
//                    }
//                });
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}