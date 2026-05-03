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
    private Label L1,L2,L3,L4,L5,L;
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
        L5.setText("");
    }

    public process createProcess() {
         resetError();
         try{
             int id = Integer.parseInt(pid.getText());
        int arr = Integer.parseInt(arrival.getText());
        if (arr< 0) {
            L2.setText("Invalid value in arrival time");
            return null;
        }
        int bur = Integer.parseInt(burst.getText());
        if (bur<= 0) {
            L3.setText("Invalid value in burst time");
            return null;
        }
        return new process(id, arr, bur);
         }catch (NumberFormatException ex){
             L5.setText("Please enter valid numbers");
             return null;
         }
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

    }

    SceneSwitcher s= new SceneSwitcher();
    int x;
    public void goToRR(ActionEvent e) throws IOException {
        try{
        int q = Integer.parseInt(quantum.getText());
        if(q<=0){
            L4.setText("Invalid value");
            return;
        }
        x=q;
        s.setProcesses(processes);
        s.setQuantum(q);
        s.switchToRRScene(e);
        }catch (NumberFormatException ex){
            L4.setText("enter value");
        }
    }

    public void goToSJF(ActionEvent e) throws IOException {
        s.setProcesses(processes);
        s.switchToSJFScene(e);
    }
    public void goToComparison(ActionEvent e) throws IOException {
        s.setProcesses(processes);
        s.switchToComparisonScene(e);
    }
    public void goToProcessTable(ActionEvent e) throws IOException {
        s.setProcesses(processes);
        s.setQuantum(x);
        s.switchToProcessTableScene(e);
    }
}