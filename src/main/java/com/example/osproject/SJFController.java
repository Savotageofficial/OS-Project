package com.example.osproject;
import com.example.osproject.SJF;
import com.example.osproject.process;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.collections.*;
import javafx.scene.control.OverrunStyle;

import java.util.List;

public class  SJFController {
    @FXML private TableView<process> table;
    @FXML private TableColumn<process , Integer> pidCol;
    @FXML private TableColumn<process , Integer> wtCol;
    @FXML private TableColumn<process , Integer> tatCol;
    @FXML private TableColumn<process , Integer> rtCol;

    @FXML private VBox ganttBox;

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
        process[]arr=processes.toArray(new process[0]);

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

     table.setFixedCellSize(40);
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
         avgWT.setText("Average WT= " + totalWT + "/" + n + "=" + String.format("%.3f",totalWT / n));
         avgTAT.setText("Average TAT= " + totalTAT + "/" + n + "=" + String.format ("%.3f",totalTAT / n));
         avgRT.setText("Average RT= " + totalRT + "/" + n + "=" +  String.format("%.3f",totalRT / n));
     }
     private void drawGanttChart(process[] processes){

             ganttBox.getChildren().clear();

             VBox container = new VBox(5);
             container.setAlignment(Pos.CENTER);

             HBox blocks = new HBox(0);
             blocks.setAlignment(Pos.CENTER);

             HBox times = new HBox(0);
             times.setAlignment(Pos.CENTER);

             int currentTime = processes[0].getArrivaltime();
            int scale= 15;

             Label start = new Label(String.valueOf(currentTime));
             start.setMinWidth(0);
             times.getChildren().add(start);
             start.getStyleClass().add("gantt-time");

             for (process p : processes) {
                 int width=p.getBursttime()*scale;


                 Label block = new Label("P" + p.pid());
                 block.getStyleClass().add("gantt-block");
                 int finalWidth=Math.max(width,60);
                 block.setMinWidth(finalWidth);
                 block.setMaxWidth(finalWidth);
                 block.setPrefWidth(finalWidth);
                  block.setTextOverrun(OverrunStyle.CLIP);
                 blocks.getChildren().add(block);

                 currentTime += p.getBursttime();

                 Label t = new Label(String.valueOf(currentTime));
                 t.setMinWidth(width);
                 t.setMaxWidth(width);
                 t.getStyleClass().add("gantt-time");
                 t.setAlignment(Pos.CENTER_RIGHT);

                 times.getChildren().add(t);
             }

             container.getChildren().addAll(blocks, times);
             ganttBox.getChildren().add(container);
         }

     @FXML public void goToInput(javafx.event.ActionEvent e)throws  java.io.IOException{
         SceneSwitcher.getInstance().switchToInputScene(e);
     }
    @FXML public void goToRR(javafx.event.ActionEvent e)throws  java.io.IOException{
         SceneSwitcher.getInstance().switchToRRScene(e);
    }
    @FXML public void goToComparison(javafx.event.ActionEvent e)throws  java.io.IOException{
        SceneSwitcher.getInstance().switchToComparisonScene(e);
    }

}


