module com.example.week6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.week6 to javafx.fxml;
    exports com.example.week6;
}