package com.example.uitest;

import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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



    private String[] ll={"ss","aas"};

    public void show_drives() throws IOException {
        for (Path root : FileSystems.getDefault().getRootDirectories()) {
            FileStore fileStore = Files.getFileStore(root);
            System.out.format("%s\t%s\n", root, fileStore.getAttribute("volume:isRemovable"));
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            HashMap<String,String> f=controller.scan_files(Controller.current_loc);
            InputStream stream = new FileInputStream("E:\\FinalManager\\FileManagerJAVA\\src\\main\\resources\\com\\example\\uitest\\icons8-folder-96.png");
            Image image = new Image(stream);
            for(Map.Entry<String, String> set:f.entrySet()){
                ImageView ImageView=new ImageView();
                ImageView.setImage(image);
                Button B=new Button();
                B.setText(set.getKey());
                B.setGraphic(ImageView);
                List.getChildren().add(B);
                B.setStyle("-fx-background-color: #f4f4f4; ");
                B.setContentDisplay(ContentDisplay.TOP);
                B.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        System.out.println("WOW YOU HAVE CLICKED ON ME");
                        System.out.println(B.getText());
                    }
                });
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}