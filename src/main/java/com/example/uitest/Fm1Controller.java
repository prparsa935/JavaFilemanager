package com.example.uitest;

import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.io.*;
import java.net.URL;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class Fm1Controller implements Initializable {

    Controller controller=new Controller();

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
    private void SetIcon(Image image,Map.Entry<String, String> set){
        if(set.getValue().equals("DIR")){

        }
        ImageView ImageView=new ImageView();
        ImageView.setImage(image);
        Button B=new Button();
        B.setText(set.getKey());
        B.setGraphic(ImageView);
        List.getChildren().add(B);
        B.setContentDisplay(ContentDisplay.TOP);
//                B.getStyleClass().add("bb");
        MenuItem copy=new MenuItem("copy");
        final ContextMenu CM=new ContextMenu();
        CM.getItems().add(copy);

        copy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(B.getText());
            }
        });
        B.setContextMenu(CM);
        B.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount()==2){
                        List.getChildren().clear();
                        OpenFolder(B.getText());
                    }
                }
            }
        });

    }
    private String getfileformat(String filename){
        return filename.substring(filename.lastIndexOf('.')+1,filename.length());
    }


    private void OpenFolder(String to_open_dir){
        try {
            HashMap<String,String> f=controller.open_dir(to_open_dir);
            InputStream FolderStream = new FileInputStream(getClass().getResource("icons8-folder-96.png").getPath());
            InputStream pictureStream = new FileInputStream(getClass().getResource("icons8-gallery-96.png").getPath());
            InputStream videoStream = new FileInputStream(getClass().getResource("icons8-video-96.png").getPath());
            InputStream appStream = new FileInputStream(getClass().getResource("icons8-application-window-96.png").getPath());
            InputStream pdfStream = new FileInputStream(getClass().getResource("icons8-pdf-80.png").getPath());
            InputStream fileStream = new FileInputStream(getClass().getResource("icons8-file-64.png").getPath());

            Image Folderimage = new Image(FolderStream);
            Image pictureimage = new Image(pictureStream);
            Image videoimage = new Image(videoStream);
            Image appimage = new Image(appStream);
            Image pdfimage = new Image(pdfStream);
            Image fileimage = new Image(fileStream);
            for(Map.Entry<String, String> set:f.entrySet()){
                String fileformat = "";
                try {
                    fileformat=getfileformat(set.getKey()).toLowerCase();

                }
                catch (Exception e){


                }

                if (set.getValue().equals("DIR")||set.getValue().equals("drive")){
                    SetIcon(Folderimage,set);

                }
                else if(fileformat.equals("jpeg")||fileformat.equals("gif")||fileformat.equals("tiff")||fileformat.equals("jpg")){
                    SetIcon(pictureimage,set);

                }
                else if(fileformat.equals("exe")||fileformat.equals("bat")){
                    SetIcon(appimage,set);
                }
                else if(fileformat.equals("mp4")||fileformat.equals("mov")||fileformat.equals("wmv")||fileformat.equals("avi")){
                    SetIcon(videoimage,set);
                }
                else if(fileformat.equals("pdf")){
                    SetIcon(pdfimage,set);
                }
                else{
                    SetIcon(fileimage,set);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void back(){
        try {
            List.getChildren().clear();
            HashMap<String,String> f=controller.back_dir();
            InputStream stream = new FileInputStream(getClass().getResource("icons8-folder-96.png").getPath());
            Image image = new Image(stream);
            for(Map.Entry<String, String> set:f.entrySet()){
                ImageView ImageView=new ImageView();
                ImageView.setImage(image);
                Button B=new Button();
                B.setText(set.getKey());
                B.setGraphic(ImageView);
                List.getChildren().add(B);
                B.setContentDisplay(ContentDisplay.TOP);
//                B.getStyleClass().add("bb");
                MenuItem copy=new MenuItem("copy");
                final ContextMenu CM=new ContextMenu();
                CM.getItems().add(copy);

                copy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        System.out.println(B.getText());
                    }
                });
                B.setContextMenu(CM);
                B.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                            if(mouseEvent.getClickCount()==2){
                                List.getChildren().clear();
                                OpenFolder(B.getText());
                            }
                        }
                    }
                });
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        OpenFolder(Controller.current_loc);
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