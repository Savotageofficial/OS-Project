package com.example.osproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class ComparisonController implements Initializable {
    @FXML
    TableView <Data>table;

    @FXML
    TableColumn <Data,String>met;


    ObservableList<Data> list = FXCollections.observableArrayList(
            new Data ("WT"),
            new Data ("TAT"),
            new Data ("RT")

    );
    @Override
    public void initialize(URL url, ResourceBundle rb){
        met.setCellValueFactory(new PropertyValueFactory<>("met"));

        table.setItems(list);
    }

}

