package com.example.osproject;
import com.example.osproject.SJF;
import com.example.osproject.process;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.collections.*;

import java.util.List;

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
    @FXML public void initialize() {
        pidCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(
                        cellData.getValue().pid()
                ).asObject() );
        wtCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(
                        cellData.getValue().getWaitingtime()
                ).asObject()
        );
        tatCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(
                        cellData.getValue().getTurnaroundtime()
                ).asObject()
        );
        rtCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(
                        cellData.getValue().getResponsetime()
                ).asObject()
        );

        }

    public void setProcesses(List<process> processes){
        runSJF(processes);
    }
    private void runSJF(List<process>processes){
        if(processes==null||processes.isEmpty())return;
        process[] arr = processes.stream().map(process::copy).toArray(process[]::new);


        SJF sjf=new SJF(arr);
        sjf.Run();

        process[] result = sjf.getProcesses();
        fillTable(result);
        calculateAverages(result);
        drawGanttChart(result);
    }
private void fillTable(process[]processes){
        ObservableList<process>data =FXCollections.observableArrayList();
     for (process p:processes)  {
         data.add(p);}
     table.setItems(data);

     table.setFixedCellSize(30);
     table.prefHeightProperty().bind(
             table.fixedCellSizeProperty().multiply(
                     javafx.beans.binding.Bindings.size(table.getItems()).add(1)
             ));

    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);}

     private void calculateAverages(process[]processes) {
         double totalWT = 0;
         double totalTAT = 0;
         double totalRT = 0;
         for (process p : processes) {
             totalWT += p.getWaitingtime();
             totalTAT += p.getTurnaroundtime();
             totalRT += p.getResponsetime();
         }
         int n = processes.length;
         avgWT.setText("Average WT= " + totalWT + "/" + n + "=" + (totalWT / n));
         avgTAT.setText("Average TAT= " + totalTAT + "/" + n + "=" + (totalTAT / n));
         avgRT.setText("Average RT= " + totalRT + "/" + n + "=" + (totalRT / n));
     }
     private void drawGanttChart(process[] processes){

        ganttBox.getChildren().clear();
        for (process p:processes){
            Label block =new Label("p"+p.pid());
            block.getStyleClass().add("gantt-block");
            ganttBox.getChildren().add(block);
        }
     }
}


