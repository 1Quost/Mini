module hello.finalp {
    requires javafx.controls;
    requires javafx.fxml;


    opens hello.finalp to javafx.fxml;
    exports hello.finalp;
}