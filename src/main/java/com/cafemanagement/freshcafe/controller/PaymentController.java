package com.cafemanagement.freshcafe.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class PaymentController {
    @FXML
    private Label pricePay;
    public void setData(double price){
        DecimalFormat format = new DecimalFormat("0.##");
        pricePay.setText(format.format(price) + " THB");
    }
    @FXML
    public void goBack(){
        Stage stage = (Stage) pricePay.getScene().getWindow();
        stage.close();
    }
}
