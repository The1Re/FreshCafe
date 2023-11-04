package com.cafemanagement.freshcafe.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class TableController implements Initializable {
    private static final int READY=0, BUSY=1, PREPARE=2;
    private Map<Button, SetupCard> tables = new HashMap<>();
    private Button selectedTable;
    SpinnerValueFactory<Integer> valueFactory;
    @FXML
    private Circle STATUS_BUSY;

    @FXML
    private Circle STATUS_READY;

    @FXML
    private TextField nameText;

    @FXML
    private Spinner<Integer> spinner;

    @FXML
    private Circle status;
    @FXML
    private Label tableName;

    @FXML
    private GridPane gridTable;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Tooltip ttReady = new Tooltip("Ready Status");
        ttReady.setShowDelay(Duration.ZERO);
        Tooltip ttBusy = new Tooltip("Busy Status");
        ttBusy.setShowDelay(Duration.ZERO);
        Tooltip.install(STATUS_READY, ttReady);
        Tooltip.install(STATUS_BUSY, ttBusy);
        valueFactory  = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,4);
        spinner.setValueFactory(valueFactory);

        int i=0;
        for (Node node : gridTable.getChildren()){
            Button table = (Button)node;
            tables.put(table, new SetupCard(table.getText()));
            if (i++==0){
                selectedTable = table;
                tables.get(selectedTable).load();
            }
        }
    }

    @FXML
    public void okBtn(ActionEvent e){
        String tn = selectedTable.getText();
        String name = nameText.getText();
        int amount = spinner.getValue();
        SetupCard setup = tables.get(selectedTable);
        setup.setData(tn, BUSY, name, amount);
        setup.load();
    }
    @FXML
    public void clearBtn(ActionEvent e){
        SetupCard setup = tables.get(selectedTable);
        setup.clearData(selectedTable.getText());
        setup.load();
    }
    @FXML
    public void setup(ActionEvent e){
        Button node = (Button) e.getSource();
        selectedTable = node;

        tables.get(node).load();

    }

    public class SetupCard{
        private String tableName;
        private int status;
        private String name;
        private int personAmount;
        public SetupCard(String tableName){
            this.tableName = tableName;
            status = READY;
        }
        public void setData(String tableName, int status, String name, int personAmount) {
            this.tableName = tableName;
            this.status = status;
            this.name = name;
            this.personAmount = personAmount;
        }
        public void clearData(String tableName){
            this.tableName = tableName;
            status = READY;
            name = "";
            personAmount = 0;
        }

        public void load(){
            TableController.this.tableName.setText(tableName);
            TableController.this.nameText.setText(name);
            switch (status){
                case READY:
                    selectedTable.getStyleClass().removeAll("table-busy", "table-prepare");
                    selectedTable.getStyleClass().add("table-ready");
                    TableController.this.status.setFill(Paint.valueOf("#7eeb6c"));
                    break;
                case BUSY:
                    selectedTable.getStyleClass().removeAll("table-ready", "table-prepare");
                    selectedTable.getStyleClass().addAll("table-busy");
                    TableController.this.status.setFill(Paint.valueOf("#e56767"));
                    break;
            }
            valueFactory.setValue(personAmount);
        }
    }

}