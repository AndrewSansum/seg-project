module com.example.segproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.segproject to javafx.fxml;
    exports com.example.segproject;
}