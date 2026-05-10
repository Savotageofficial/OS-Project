package com.example.osproject;
import com.example.osproject.Algorithms.SJF;
import com.example.osproject.Models.process;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.collections.*;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.ContentDisplay;

import java.io.IOException;
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
    private void drawGanttChart(process[] processes) {


            ganttBox.getChildren().clear();

            VBox container = new VBox(5);
            container.setAlignment(Pos.CENTER);

            HBox blocks = new HBox(0);
            blocks.setAlignment(Pos.CENTER);

            int scale = 15;
            int minimumWidth = 35;

            for (process p : processes) {

                int width = Math.max(p.getBursttime() * scale, minimumWidth);

                Label block = new Label("P" + p.pid());

                block.getStyleClass().add("gantt-block");

                block.setPrefWidth(width);
                block.setMinWidth(width);
                block.setMaxWidth(width);

                block.setPrefHeight(40);

                block.setAlignment(Pos.CENTER);

                block.setContentDisplay(ContentDisplay.CENTER);

                block.setEllipsisString("");

                blocks.getChildren().add(block);
            }

            Pane times = new Pane();

            times.setPrefHeight(30);

            int currentTime = processes[0].getArrivaltime();

            int position = 0;

            Label start = new Label(String.valueOf(currentTime));

            start.setLayoutX(0);

            start.setLayoutY(0);

            start.getStyleClass().add("gantt-time");

            times.getChildren().add(start);

            for (process p : processes) {

                int width = Math.max(p.getBursttime() * scale, minimumWidth);

                position += width;

                currentTime += p.getBursttime();

                Label t = new Label(String.valueOf(currentTime));

                t.setLayoutX(position - 12);

                t.setLayoutY(0);

                t.getStyleClass().add("gantt-time");

                times.getChildren().add(t);
            }

            blocks.setMaxWidth(Region.USE_PREF_SIZE);

            times.setPrefWidth(position);

            times.setMaxWidth(position);

            container.getChildren().addAll(blocks, times);

            container.setAlignment(Pos.CENTER);

            ganttBox.setAlignment(Pos.CENTER);

            ganttBox.getChildren().add(container);
        }


    @FXML
    public void goToInput(ActionEvent e) throws IOException {

        SceneSwitcher.getInstance().switchToInputScene(e);
    }


    @FXML
    public void goToRR(ActionEvent e) throws IOException {

        if (SceneSwitcher.getInstance().getQuantum() <= 0) {

            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Warning");

            alert.setHeaderText("Missing Quantum");

            alert.setContentText("Please enter Quantum first!");

            alert.getDialogPane().getStylesheets().add(
                    getClass().getResource("SJFStyle.css").toExternalForm()
            );

            alert.showAndWait();

            SceneSwitcher.getInstance().switchToInputScene(e);

            return;
        }

        SceneSwitcher.getInstance().switchToRRScene(e);
    }


    @FXML
    public void goToComparison(ActionEvent e) throws IOException {

        if (SceneSwitcher.getInstance().getQuantum() <= 0) {

            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Warning");

            alert.setHeaderText("Missing Quantum");

            alert.setContentText("Please enter Quantum first!");

            alert.getDialogPane().getStylesheets().add(
                    getClass().getResource("SJFStyle.css").toExternalForm()
            );

            alert.showAndWait();

            SceneSwitcher.getInstance().switchToInputScene(e);

            return;
        }

        SceneSwitcher.getInstance().switchToComparisonScene(e);
    }
}
