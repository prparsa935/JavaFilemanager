package com.example.uitest;

import javafx.scene.control.Label;
import usermodel.userent.UserEnt;
import usermodel.userservice.UserService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreatefileController implements Initializable {
    @FXML
    private Button CreateFileButt;
    @FXML
    private TextField NameFiled;

    public Button getCreateFile() {
        return CreateFileButt;
    }

    public TextField getNameFiled() {
        return NameFiled;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




    }
}
