package com.example.uitest;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;

public class RenamefieldController implements Initializable {
    @FXML
    private TextField newnamefield;
    @FXML
    private Button save;

    public TextField getNewnamefield() {
        return newnamefield;
    }

    public void setNewnamefield(TextField newnamefield) {
        this.newnamefield = newnamefield;
    }

    public Button getSave() {
        return save;
    }

    public void setSave(Button save) {
        this.save = save;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        save.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                newnamefield.getText();
//
//
//            }
//        });

    }
}
