package com.example.osproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("RR-result.fxml"));
        Scene scene = new Scene(fxmlLoader.load());


        Group root = new Group();
        Image icon =new Image("OIP.png");
        stage.getIcons().add(icon);
        Image imagee= new Image("OIP.png");
        ImageView imageView= new ImageView(imagee);


        //round robin style path
        String RRcss= this.getClass().getResource("RRStyle.css").toExternalForm();
        scene.getStylesheets().add(RRcss);

        stage.setTitle("OS project");
        root.getChildren().add(imageView);
        stage.setScene(scene);
        stage.show();
        //logout
        stage.setOnCloseRequest(Event ->{
            Event.consume();
            logOut(stage);
        });
    }
    public void logOut (Stage stage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("logout");
        alert.setHeaderText("you're about logout!");
        alert.setContentText("do you want to save before exiting?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("you successfully logged out!");
            stage.close();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}