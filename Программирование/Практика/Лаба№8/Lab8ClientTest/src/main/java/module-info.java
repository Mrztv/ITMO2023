module org.example.lab8clienttest {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.lab8clienttest to javafx.fxml;
    exports org.example.lab8clienttest;
}