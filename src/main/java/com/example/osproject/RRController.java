package com.example.osproject;

import com.example.osproject.Algorithms.RR;
import com.example.osproject.Models.AlgoEval;
import com.example.osproject.Models.process;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
public class RRController {

    @FXML
    private FlowPane ganttBox;
    @FXML
    private TableView<process> tableView;
    @FXML
    private Label avgWT, avgTAT, avgRT;

    public void loadData(List<process> processes, int quantum) {
        AlgoEval algoEval = new AlgoEval(processes, quantum);
        process[] rr = algoEval.getRrProcesses();
        RR round = new RR();
        HashMap<Integer, Queue> hashMap= new HashMap<>();
        process[] copies = processes.stream().map(process::copy).toArray(process[]::new);
        round.RR(copies, quantum, hashMap);
        List<process> executionOrder = round.getExecutionOrder();
        HashMap<Integer, Integer> durations = round.getExecutionDurations();
        HashMap<Integer, process> timeLine = round.getExecutionTimeline();

        Gantt(durations, executionOrder, timeLine);
        setupTable();

        tableView.getItems().addAll(rr);
        avgWT.setText("Avg WT= " + algoEval.getRrAvgWT());
        avgTAT.setText("Avg TAT= " + algoEval.getRrAvgTAT());
        avgRT.setText("Avg RT= " + algoEval.getRrAvgRT());
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
        tableView.prefHeightProperty().bind(tableView.fixedCellSizeProperty().multiply(Bindings.size(tableView.getItems()).add(3)) );
    }

    private void Gantt(HashMap<Integer, Integer> durations, List<process> executionOrder, HashMap<Integer, process> timeline) {
        ganttBox.getChildren().clear();

        int currentTime = timeline.keySet().stream().min(Integer::compare).orElse(0);
        for (process p : executionOrder) {
            int duration = durations.getOrDefault(currentTime, 1);
            Label block = new Label("P" + p.pid());
            Label timeLabel = new Label(String.valueOf(currentTime));
            VBox cell = new VBox();

            block.setPrefWidth(40);
            block.getStyleClass().add("gantt-block");
            timeLabel.setPrefWidth(block.getPrefWidth());
            timeLabel.setAlignment(Pos.TOP_LEFT);
            cell.getChildren().addAll(block, timeLabel);

            ganttBox.getChildren().add(cell);
            currentTime += duration;
        }
        Label blockF = new Label("");
        Label finalTime = new Label(String.valueOf(currentTime));
        VBox cell = new VBox();

        blockF.setPrefHeight(31);
        finalTime.setPrefWidth(blockF.getPrefWidth());
        finalTime.setAlignment(Pos.TOP_LEFT);
        cell.getChildren().addAll(blockF, finalTime);

        ganttBox.getChildren().add(cell);
    }
    @FXML
    public void goToInput(ActionEvent e) throws IOException {
        SceneSwitcher.getInstance().switchToInputScene(e);
    }
    @FXML
    public void goToSJF(ActionEvent e) throws IOException {
        SceneSwitcher.getInstance().switchToSJFScene(e);
    }
    @FXML
    public void goToCT(ActionEvent e) throws IOException {
        SceneSwitcher.getInstance().switchToComparisonScene(e);
    }
}
