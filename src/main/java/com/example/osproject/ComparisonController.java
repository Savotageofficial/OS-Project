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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
    public void updateTime(int t) {
        time.setText("T = " + t);
    }
    @FXML
    private Label p1;
    @FXML
    private Label p2;
    @FXML
    private Label p3;
    @FXML
    private Label p4;


    @FXML
    private StackPane overlay;
    private List<process> processes;
    SceneSwitcher s = SceneSwitcher.getInstance();
    private List<List<String>> queueSteps = new ArrayList<>();
    private int currentStep = 0;
    @FXML
    public void next() {

        if (queueSteps == null || queueSteps.isEmpty()) return;

        if (currentStep < queueSteps.size() - 1) {
            currentStep++;
            showStep(currentStep);
        }
    }

    @FXML
    public void prev() {

        if (queueSteps == null || queueSteps.isEmpty()) return;

        if (currentStep > 0) {
            currentStep--;
            showStep(currentStep);
        }
    }

    @FXML
    public void goToInput(ActionEvent e) throws IOException {

        SceneSwitcher.getInstance().switchToInputScene(e);
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
        buildQueue();
        showStep(0);
    }
    private void buildQueue() {

        queueSteps.clear();

        for (int i = 0; i < processes.size(); i++) {
            List<String> step = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                step.add("P" + processes.get(j).getPid());
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
        Label[] panes = {p1, p2, p3, p4};
        for (Label p : panes) {
            p.setText("");
        }

        for (int i = 0; i < q.size() && i < panes.length; i++) {
            panes[i].setText(q.get(i));
        }

        time.setText("T = " + step);
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