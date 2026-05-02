package com.example.osproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchToInputScene(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("input.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(SceneSwitcher.class.getResource("input.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void switchToRRScene(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("RR-result.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(SceneSwitcher.class.getResource("RRStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void switchToSJFScene(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SJF.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(SceneSwitcher.class.getResource("SJFStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void switchToComparisonScene(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("comparison.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(SceneSwitcher.class.getResource("ComStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void switchToProcessTableScene(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("processtable.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
