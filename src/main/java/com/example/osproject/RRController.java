package com.example.osproject;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class RRController {

    @FXML
    private HBox ganttBox;

    @FXML
    private TableView<process> tableView;

    @FXML
    private Label avgWT, avgTAT, avgRT;

    public void loadData(List<process> processes, int quantum) {
        AlgoEval algoEval = new AlgoEval(processes, quantum);
        process[] rr = algoEval.getRrProcesses();

        tableView.getItems().addAll(rr);

        avgWT.setText("Avg WT: " + algoEval.getRrAvgWT());
        avgTAT.setText("Avg TAT: " + algoEval.getRrAvgTAT());
        avgRT.setText("Avg RT: " + algoEval.getRrAvgRT());
    }

    private void setupTable() {
        tableView.getColumns().clear();

        TableColumn<process, Integer> processCol = new TableColumn<>("Process");
        processCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().pid()).asObject());
        processCol.prefWidthProperty().bind(tableView.widthProperty().divide(4));

        TableColumn<process, Integer> wtCol = new TableColumn<>("Waiting Time");
        wtCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getWaitingtime()).asObject());
        wtCol.prefWidthProperty().bind(tableView.widthProperty().divide(4.08));

        TableColumn<process, Integer> tatCol = new TableColumn<>("Turnaround Time");
        tatCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTurnaroundtime()).asObject());
        tatCol.prefWidthProperty().bind(tableView.widthProperty().divide(4.08));

        TableColumn<process, Integer> rtCol = new TableColumn<>("Response Time");
        rtCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getResponsetime()).asObject());
        rtCol.prefWidthProperty().bind(tableView.widthProperty().divide(4.08));

        tableView.getColumns().addAll(processCol, wtCol, tatCol, rtCol);

        tableView.setPlaceholder(new Label(""));

        tableView.setFixedCellSize(25);
        tableView.prefHeightProperty().bind(tableView.fixedCellSizeProperty().multiply(Bindings.size(tableView.getItems()).add(2)) );
    }
}
