package com.example.osproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private InputController controller;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("input.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        Scene scene = new Scene(root);
        Image icon= new Image("OIP.png");
        stage.getIcons().add(icon);
        stage.setTitle("OS Project");
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("input.css").toExternalForm());
        stage.setHeight(500);
        stage.setWidth(680);
        stage.setResizable(false);
        stage.show();

        stage.setOnCloseRequest(Event ->{
            Event.consume();
            logOut(stage);
        });
    }
    public void logOut(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
        ImageView image = new ImageView(new Image("logout.png"));
        ButtonType yesSaveButton = new ButtonType("Save");
        ButtonType dontSaveButton = new ButtonType("Don't Save");
        ButtonType cancelButton = new ButtonType("Cancel");
        alertStage.getIcons().add(new Image("logout2.png"));

        alert.setTitle("Logout");
        alert.setHeaderText("   You're about to logout!");
        alert.setGraphic(image);
        alert.getButtonTypes().setAll(yesSaveButton, dontSaveButton, cancelButton);

        Button yesButton = (Button)alert.getDialogPane().lookupButton(yesSaveButton);
        Button dontSaveBtn = (Button)alert.getDialogPane().lookupButton(dontSaveButton);
        Button cancelBtn = (Button)alert.getDialogPane().lookupButton(cancelButton);
        yesButton.setId("yesBtn");
        dontSaveBtn.setId("dontSaveBtn");
        cancelBtn.setId("cancelBtn");

        alertStage.setWidth(360);
        alertStage.setHeight(100);

        image.setFitWidth(120);
        image.setFitHeight(120);
        image.setTranslateX(-10);
        image.setTranslateY(40);

        yesButton.setTranslateX(-70);
        yesButton.setTranslateY(-90);
        dontSaveBtn.setTranslateX(-70);
        dontSaveBtn.setTranslateY(-90);
        cancelBtn.setTranslateX(-205);
        cancelBtn.setTranslateY(-50);

        alert.getDialogPane().getStylesheets().add(getClass().getResource("logout.css").toExternalForm());
        ButtonType result =alert.showAndWait().orElse(cancelButton);
        if (result == yesSaveButton) {
            controller.saveData();
            System.out.println("Saved!");
            stage.close();
        }
        else if (result == dontSaveButton) {
            System.out.println("Closed without saving");
            stage.close();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}