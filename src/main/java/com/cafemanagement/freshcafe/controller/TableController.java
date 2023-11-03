package com.cafemanagement.freshcafe.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TableController implements Initializable {
    @FXML
    private Button Table1;

    @FXML
    private AnchorPane settable;
    @FXML
    private Spinner<Integer> gradeSpinner;
    @FXML
    private Label textTable;

    @FXML
    public void setTable() {
        settable.setVisible(true);
    }

    @FXML
    public void clearTable1(MouseEvent e){
        Button cilk = (Button)e.getSource();
        cilk.setStyle("-fx-background-color: #e96767; -fx-background-radius: 20;");
    }
    @FXML
    public void completeTable1(MouseEvent e){
        Button cilk = (Button)e.getSource();
        cilk.setStyle("-fx-background-color: #7eeb6c; -fx-background-radius: 20;");
    }
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> gradesValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,20);
        this.gradeSpinner.setValueFactory(gradesValueFactory);


        //textTable.setText(String.toString(table));
    }

}
