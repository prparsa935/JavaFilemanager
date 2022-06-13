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

public class LoginpController implements Initializable {


    @FXML
    private Button done;
    @FXML
    private Label error;

    @FXML
    private TextField email;

    @FXML
    private Pane pane;

    @FXML
    private Pane pane1;

    @FXML
    private PasswordField password;

    @FXML
    private Text text;

    @FXML
    private TextFlow textflow;

    @FXML
    private TextField username;
    @FXML
    private BorderPane mainpane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            UserService.getInstance().setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            UserEnt userEnt=new UserEnt().setId(0L).setUsername("username").setPassword("password").setEmail("user@gmail.com").setName("user");
            UserEnt userEntM=UserService.getInstance().authenticate(userEnt.getUsername(),userEnt.getPassword());
            if(userEntM==null){
                UserService.getInstance().save(userEnt);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        error.setVisible(false);
        done.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {


                UserEnt user=null;
                try {
                    user=UserService.getInstance().authenticate(username.getText(),password.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(!username.getText().equals("")&&!password.getText().equals("")){
                    if(user!=null){
                        Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getResource("Fm1.fxml"));
                            //                    mainpane.getChildren().setAll(root);
                            Stage stage = new Stage();
                            stage.setTitle("File Manager");
                            stage.setScene(new Scene(root));
                            stage.getScene().getStylesheets().add(getClass().getResource("Fm1Style.css").toExternalForm());
                            stage.show();
                            // Hide this current window (if this is what you want)
                            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                    else{
                        username.setText("");
                        email.setText("");
                        password.setText("");
                        error.setText("Incorrect username or password!\n" +
                                " please try again");
                        error.setVisible(true);
                    }


                }
                else{
                    error.setText("       please fill the form first");
                    error.setVisible(true);
                }




            }
        });
    }
}
