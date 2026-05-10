module com.example.osproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens com.example.osproject to javafx.fxml;
    exports com.example.osproject;
    exports com.example.osproject.Algorithms;
    opens com.example.osproject.Algorithms to javafx.fxml;
    exports com.example.osproject.Models;
    opens com.example.osproject.Models to javafx.fxml;
}