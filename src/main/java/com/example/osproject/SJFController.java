package com.example.osproject;
import com.example.osproject.SJF;
import com.example.osproject.process;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.collections.*;

public class  SJFController {
    @FXML private TableView<process> table;
    @FXML private TableColumn<process , Integer> pidCol;
    @FXML private TableColumn<process , Integer> wtCol;
    @FXML private TableColumn<process , Integer> tatCol;
    @FXML private TableColumn<process , Integer> rtCol;

    @FXML private HBox ganttBox;

    @FXML private Label avgWT;
    @FXML private Label avgTAT;
    @FXML private Label avgRT;
    @FXML public void initialize(){
        pidCol.setCellValueFactory(new PropertyValueFactory<>("pid"));
        pidCol.setCellValueFactory(new PropertyValueFactory<>("waitingTime"));
        pidCol.setCellValueFactory(new PropertyValueFactory<>("turnaroundTime"));
        pidCol.setCellValueFactory(new PropertyValueFactory<>("responseTime"));
    }
    public void setProcesses(process[] processes){
        runSJF(processes);
    }
    private void runSJF(process[] arr){
        if(arr==null||arr.length==0)return;
        SJF sjf=new SJF(arr);
        sjf.Run(arr);
        process[] result = sjf.getProcesses();
        fillTable(result);
        calculateAverages(result);
        drawGanttChart(result);
    }








}


