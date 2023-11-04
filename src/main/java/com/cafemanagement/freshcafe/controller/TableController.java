package com.cafemanagement.freshcafe.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

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
    private TextField textName;
    @FXML
    private Circle setCircle;
    private Button selectButton;

    @FXML
    public void setTable(ActionEvent e) {
        selectButton = (Button) e.getSource();
        settable.setVisible(true);
        textTable.setText(selectButton.getText());
    }

    @FXML
    public void clearTable1(MouseEvent e) {
        selectButton.setStyle("-fx-background-color: #e96767; -fx-background-radius: 20;");
        setCircle.setStyle(selectButton.getStyle());
        textName.setText(null);
    }

    @FXML
    public void completeTable1(MouseEvent e) {
        selectButton.setStyle("-fx-background-color: #7eeb6c; -fx-background-radius: 20;");
        setCircle.setStyle("-fx-background-color: #7eeb6c; ");
    }

    @FXML
    public void changeTable(MouseEvent e) {
        selectButton.setStyle("-fx-background-color: #e6e967; -fx-background-radius: 20;");
        setCircle.setStyle(selectButton.getStyle());
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> gradesValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20);
        this.gradeSpinner.setValueFactory(gradesValueFactory);

    }
}