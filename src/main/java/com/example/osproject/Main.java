package com.example.osproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("input.fxml"));
        Scene scene = new Scene(root);
        Image icon= new Image("OIP.png");
        stage.getIcons().add(icon);
        stage.setTitle("OS Project");
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("input.css").toExternalForm());
        stage.setHeight(600);
        stage.setWidth(899);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}