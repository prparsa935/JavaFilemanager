package com.example.uitest;
import Controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class HelloController {
    Controller controller = new Controller();
    @FXML
    private TextArea Directory;
    @FXML
    private TextField Target_dir;
    @FXML
    private Button Open_dir;
    @FXML
    public void Scan(){
        try {
            Directory.setText(controller.scan_files(controller.current_loc));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void Back(){
        try {
            Directory.setText(controller.back_dir());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void Open(){
        try {
            Directory.setText(controller.open_dir(Target_dir.getText()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}