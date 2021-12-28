module com.example.uidemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.uidemo to javafx.fxml;
    exports com.example.uidemo;
}