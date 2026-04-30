package com.example.osproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
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


}








