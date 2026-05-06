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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.net.URL;
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
    private StackPane overlay;
    private List<process> processes;

    SceneSwitcher s = new SceneSwitcher();
    public void goToInput(ActionEvent e) throws IOException {
        s.switchToInputScene(e);
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
        AlgoEval eval = new AlgoEval(processes, 3);

        ObservableList<CompareRow> list = FXCollections.observableArrayList();
        list.add(new CompareRow("WT", eval.getRrAvgWT(), eval.getSrtfAvgWT()));
        list.add(new CompareRow("TAT", eval.getRrAvgTAT(), eval.getSrtfAvgTAT()));
        list.add(new CompareRow("RT", eval.getRrAvgRT(), eval.getSrtfAvgRT()));
        table.setItems(list);
    }


    @FXML
    public void result() {
        if (processes == null || processes.isEmpty()) {
            resultt.setText("No processes available!");
            overlay.setVisible(true);
            return;
        }

        AlgoEval eval = new AlgoEval(processes, 3);
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