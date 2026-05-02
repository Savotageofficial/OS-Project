package com.example.osproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputController {

    @FXML
    private Label L1,L2,L3,L4;
    @FXML
    private TextField pid;
    @FXML
    private TextField arrival;
    @FXML
    private TextField burst;
    @FXML
    private TextField quantum;


    List<process> processes = new ArrayList<>();

    private void resetError() {
        L1.setText("");
        L2.setText("");
        L3.setText("");
        L4.setText("");
    }

    public process createProcess() {
         resetError();
        int id = Integer.parseInt(pid.getText());
        int arr = Integer.parseInt(arrival.getText());
        int bur = Integer.parseInt(burst.getText());
        int q = Integer.parseInt(quantum.getText());
        if (arr< 0) {
            L2.setText("Invalid value");
            return null;
        }

        if (bur<= 0) {
            L3.setText("Invalid value");
            return null;
        }
        if(q<=0){
            L4.setText("Invalid value");
            return null;
        }
        return new process(id, arr, bur);
    }

    public void addProcess(ActionEvent e) {
        resetError();
        process p = createProcess();

        if (p == null) return;

        for (process x : processes) {
            if (x.pid() == p.pid()) {
                L1.setText("ID already exists");
                return;
            }
        }
        processes.add(p);
        System.out.println("Added process: " + p.pid());
        pid.clear();
        arrival.clear();
        burst.clear();
        quantum.clear();
    }




    SceneSwitcher s= new SceneSwitcher();
    public void goToRR(ActionEvent e) throws IOException {
        s.switchToRRScene(e);
    }
    public void goToSJF(ActionEvent e) throws IOException {
        s.switchToSJFScene(e);
    }
    public void goToComparison(ActionEvent e) throws IOException {
        s.switchToComparisonScene(e);
    }
    public void goToProcessTable(ActionEvent e) throws IOException {
        s.switchToProcessTableScene(e);
    }


}