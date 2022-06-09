package com.example.uitest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
