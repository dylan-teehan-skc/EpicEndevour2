module com.example.epicfxworking {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;

    opens com.example.EPICFX to javafx.fxml;
    exports com.example.EPICFX;
}