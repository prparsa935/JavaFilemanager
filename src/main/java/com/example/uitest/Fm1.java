package com.example.uitest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Fm1 extends Application {
    @Override
    public void start(Stage primarystage) throws Exception{
        Parent FxmLLoader= FXMLLoader.load(getClass().getResource("Fm1.fxml"));
        primarystage.setTitle("File Manager");
        Scene scene=new Scene(FxmLLoader);
        scene.getStylesheets().add(getClass().getResource("Fm1Style.css").toExternalForm());
        primarystage.setScene(scene);
        primarystage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
