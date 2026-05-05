package com.example.osproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SceneSwitcher {
    private List<process> processes;
    private int quantum;

    public void setProcesses(List<process> processes) {
        this.processes = processes;
    }

    public List<process> getProcesses() {
        return processes;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public int getQuantum() {
        return quantum;
    }

    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchToInputScene(ActionEvent e) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("input.fxml"));
        root=loader.load();
        stage=(Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(SceneSwitcher.class.getResource("input.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void switchToRRScene(ActionEvent e) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("RR-result.fxml"));
        root=loader.load();
        stage=(Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        RRController rr = loader.getController();
       // rr.loadData(processes,quantum);
        scene.getStylesheets().add(SceneSwitcher.class.getResource("RRStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void switchToSJFScene(ActionEvent e) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("SJF.fxml"));
        root=loader.load();
        stage=(Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        SJFController s = loader.getController();
        s.setProcesses(processes);
        scene.getStylesheets().add(SceneSwitcher.class.getResource("SJFStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void switchToComparisonScene(ActionEvent e) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Comparison.fxml"));
        root=loader.load();
        stage=(Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        ComparisonController c = loader.getController();
        //c.setData(processes,quantum);
        scene.getStylesheets().add(SceneSwitcher.class.getResource("ComStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

}
