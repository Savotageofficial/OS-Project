package com.example.osproject;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.*;
import javafx.geometry.Pos;

public class ComparisonController implements Initializable {

    @FXML
    private TableView<CompareRow> table;

    @FXML
    private TableColumn<CompareRow, String> met;

    @FXML
    private TableColumn<CompareRow, Double> rr;

    @FXML
    private TableColumn<CompareRow, Double> sjf;

    @FXML
    private Label resultt;
    @FXML
    private Button pr;
    @FXML
    private Button ne;
    @FXML
    private Label ti;
    @FXML
    private Label time;
    @FXML
    private HBox rq;
    @FXML
    private Button rb;
    @FXML
    private Button sb;

    @FXML
    private StackPane overlay;
    private List<process> processes;
    private List<Integer> timeSteps;
    private int currentTimeIndex = 0;
    private HashMap<Integer, Queue> rrReadyQueues;

    SceneSwitcher s = SceneSwitcher.getInstance();
    private List<List<String>> queueSteps = new ArrayList<>();



    private int currentStep = 0;

    @FXML
    public void next() {

        if (queueSteps == null || queueSteps.isEmpty())
            return;

        if (currentStep < queueSteps.size() - 1) {
            currentStep++;
            showStep(currentStep);
        }
    }

    @FXML
    public void prev() {

        if (queueSteps == null || queueSteps.isEmpty())
            return;

        if (currentStep > 0) {
            currentStep--;
            showStep(currentStep);
        }
    }

    @FXML
    public void goToInput(ActionEvent e) throws IOException {

        SceneSwitcher.getInstance().switchToInputScene(e);
    }
    @FXML
    public void goToSJF(ActionEvent e) throws IOException {
        SceneSwitcher.getInstance().switchToSJFScene(e);
    }
    @FXML public void goToRR(javafx.event.ActionEvent e)throws  java.io.IOException{
        SceneSwitcher.getInstance().switchToRRScene(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        met.setCellValueFactory(new PropertyValueFactory<>("metric"));
        rr.setCellValueFactory(new PropertyValueFactory<>("rr"));
        sjf.setCellValueFactory(new PropertyValueFactory<>("srtf"));

        overlay.setVisible(false);
    }
    public void setData(List<process> processes) {

        this.processes = processes;
        int q = s.getQuantum();
        AlgoEval eval = new AlgoEval(processes, q);

        ObservableList<CompareRow> list = FXCollections.observableArrayList();
        list.add(new CompareRow("WT", eval.getRrAvgWT(), eval.getSrtfAvgWT()));
        list.add(new CompareRow("TAT", eval.getRrAvgTAT(), eval.getSrtfAvgTAT()));
        list.add(new CompareRow("RT", eval.getRrAvgRT(), eval.getSrtfAvgRT()));

        table.setItems(list);
        rrReadyQueues = eval.getRrReadyQueues();
        buildQueue();
        showStep(0);
    }

    private void buildQueue() {

        queueSteps.clear();
        timeSteps = new ArrayList<>();

        if (rrReadyQueues == null || rrReadyQueues.isEmpty())
            return;

        timeSteps = new ArrayList<>(rrReadyQueues.keySet());
        Collections.sort(timeSteps);

        for (int t : timeSteps) {
            List<String> step = new ArrayList<>();
            Queue queue = rrReadyQueues.get(t);
            if (queue != null) {
                for (Object pid : queue) {
                    step.add("P" + pid);
                }
            }
            queueSteps.add(step);
        }
    }
    private void showStep(int step) {
        if (queueSteps.isEmpty())
            return;
        if (step < 0 || step >= queueSteps.size())
            return;

        List<String> q = queueSteps.get(step);
        rq.getChildren().clear();

        if (q.isEmpty()) {
            Label empty = new Label("Empty");
            empty.setStyle("-fx-text-fill: #9ca3af; -fx-font-style: italic;");
            rq.getChildren().add(empty);
        } else {
            for (String name : q) {
                Label pLabel = new Label(name);
                pLabel.setPrefSize(45, 45);
                pLabel.setAlignment(Pos.CENTER);
                pLabel.setStyle(
                        "-fx-background-color: #3b82f6;" +
                                "-fx-text-fill: white;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 5;");
                rq.getChildren().add(pLabel);
            }
        }
        int actualTime = (timeSteps != null && step < timeSteps.size()) ? timeSteps.get(step) : step;
        time.setText("T = " + actualTime);

        pr.setDisable(currentStep == 0);
        ne.setDisable(currentStep >= queueSteps.size() - 1);
    }

    @FXML
    public void result() {

        if (processes == null || processes.isEmpty()) {
            resultt.setText("No processes available!");
            overlay.setVisible(true);
            return;
        }
        int q = s.getQuantum();

        AlgoEval eval = new AlgoEval(processes, q);

        resultt.setText(eval.getConclusion());
        overlay.setOpacity(0);
        overlay.setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.millis(300), overlay);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    @FXML
    public void closeOverlay() {
        overlay.setVisible(false);
    }
}