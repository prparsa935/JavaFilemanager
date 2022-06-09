package com.example.uitest;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;
public class PropertiesController {
    public Label getFilename() {
        return filename;
    }

    public Label getFormat() {
        return format;
    }

    public Label getLastdatemodiafied() {
        return lastdatemodiafied;
    }

    public Label getDatecreated() {
        return datecreated;
    }

    public Label getCode() {
        return code;
    }

    public Label getPath() {
        return path;
    }

    public ImageView getFileimage() {
        return fileimage;
    }

    @FXML
    private Label filename;
    @FXML
    private Label format;
    @FXML
    private Label lastdatemodiafied;
    @FXML
    private Label datecreated;
    @FXML
    private Label code;
    @FXML
    private Label path;
    @FXML
    private ImageView fileimage;
}
