package com.example.osproject;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import java.util.List;

public class RRController {

    @FXML
    private HBox ganttBox;

    @FXML
    private TableView<process> tableView;

    @FXML
    private Label avgWTLabel, avgTATLabel, avgRTLabel;

    public void loadData(List<process> processes, int quantum) {
        AlgoEval algoEval = new AlgoEval(processes, quantum);
        process[] rr = algoEval.getRrProcesses();


        tableView.getItems().addAll(rr);


        avgWTLabel.setText("Avg WT: " + algoEval.getRrAvgWT());
        avgTATLabel.setText("Avg TAT: " + algoEval.getRrAvgTAT());
        avgRTLabel.setText("Avg RT: " + algoEval.getRrAvgRT());
    }

    private void setupTable() {
        tableView.getColumns().clear();

        TableColumn<process, Integer> processCol = new TableColumn<>("Process");
        processCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().pid()).asObject());

        TableColumn<process, Integer> wtCol = new TableColumn<>("Waiting Time");
        wtCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getWaitingtime()).asObject());

        TableColumn<process, Integer> tatCol = new TableColumn<>("Turnaround Time");
        tatCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTurnaroundtime()).asObject());

        TableColumn<process, Integer> rtCol = new TableColumn<>("Response Time");
        rtCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getResponsetime()).asObject());
        tableView.getColumns().addAll(processCol, wtCol, tatCol, rtCol);
    }
}
