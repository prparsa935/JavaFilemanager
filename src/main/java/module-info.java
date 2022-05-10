module com.example.uitest {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.uitest to javafx.fxml;
    exports com.example.uitest;
}