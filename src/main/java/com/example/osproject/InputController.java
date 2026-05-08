package com.example.osproject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class InputController implements Initializable {

    @FXML
    private TextField processid;
    @FXML
    private TextField arrival;
    @FXML
    private TextField  burst;
    @FXML
    private TextField quantum;
    @FXML
    private Label L,L2,L3,L4;
    @FXML
    private TableView<process> table;
    @FXML
    private TableColumn<process,Integer> PID;
    @FXML
    private TableColumn<process,Integer> AT;
    @FXML
    private TableColumn<process,Integer> BT;

    private int initialId =1;
    public void resetError(){
        L.setText("");
        L2.setText("");
        L3.setText("");
        L4.setText("");
    }
    ObservableList<process> processes = FXCollections.observableArrayList();
    int x=0;
    public process createProcess() {
       resetError();
        try {
            int id = initialId;
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
            initialId++;
            return new process(id, arr, bur);
        }catch (NumberFormatException ex){
            L4.setText("Please enter valid numbers");
            return null;
        }
    }

    public void addProcess(ActionEvent e) {
        resetError();
        process p = createProcess();
        processid.setText(String.valueOf(initialId));
        if (p == null) return;
        processes.add(p);
        System.out.println("Added process: " + p.pid());
        arrival.clear();
        burst.clear();
    }
    public void clear(ActionEvent e){
        processes.clear();
        initialId = 1;
        processid.setText(String.valueOf(initialId));
        arrival.clear();
        burst.clear();
        resetError();
    }
    private boolean hasProcesses() {
        if (processes.isEmpty()) {
            L4.setText("You must add process");
            return false;
        }
        return true;
    }
    SceneSwitcher s = SceneSwitcher.getInstance();

    public void goToRR(ActionEvent e) throws IOException {
        try {
            resetError();
            if (!hasProcesses()) return;

            if (quantum.getText().isEmpty()) {
                L.setText("You Must enter Quantum Number");
                return;
            }
            int q = Integer.parseInt(quantum.getText());

            if (q <= 0) {
                L4.setText("Invalid value");
                return;
            }
            SceneSwitcher.getInstance().setProcesses(processes);
            SceneSwitcher.getInstance().setQuantum(q);
            SceneSwitcher.getInstance().switchToRRScene(e);

        } catch (NumberFormatException ex) {
            L.setText("Enter valid value");
        }
    }
    public void goToSJF(ActionEvent e) throws IOException {
        try {
            resetError();
            if (!hasProcesses()) return;
            int q = quantum.getText().isEmpty() ? 0 : Integer.parseInt(quantum.getText());
            SceneSwitcher.getInstance().setProcesses(processes);
            SceneSwitcher.getInstance().setQuantum(q);
            SceneSwitcher.getInstance().switchToSJFScene(e);
        } catch (NumberFormatException ex) {
            L.setText("Enter valid value");
        }
    }

    public void goToComparison(ActionEvent e) throws IOException {
        try {
            resetError();
            if (!hasProcesses()) return;
            if (quantum.getText().isEmpty()) {
                L.setText("You Must enter Quantum Number");
                return;
            }
            int q = Integer.parseInt(quantum.getText());
            if (q <= 0) {
                L4.setText("Invalid value");
                return;
            }
            SceneSwitcher.getInstance().setProcesses(processes);
            SceneSwitcher.getInstance().setQuantum(q);
            SceneSwitcher.getInstance().switchToComparisonScene(e);
        } catch (NumberFormatException ex) {
            L.setText("Enter valid value");
        }
    }

    public void loadData(List<process> savedProcesses, int q) {
        if (savedProcesses != null) {
            processes.clear();
            processes.addAll(savedProcesses);
            initialId = processes.size() + 1;
            processid.setText(String.valueOf(initialId));
        }
        table.setItems(processes);
        table.refresh();
        if (q > 0) {
            quantum.setText(String.valueOf(q));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        processid.setText(String.valueOf(initialId));
        PID.setCellValueFactory(new PropertyValueFactory<>("pid"));
        AT.setCellValueFactory(new PropertyValueFactory<>("Arrivaltime"));
        BT.setCellValueFactory(new PropertyValueFactory<>("Bursttime"));
        table.setItems(processes);
        table.refresh();
    }
}