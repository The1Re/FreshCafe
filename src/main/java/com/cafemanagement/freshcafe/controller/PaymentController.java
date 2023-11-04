package com.cafemanagement.freshcafe.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class PaymentController {
    private Stage stage;
    private MenuController menuController;
    @FXML
    private Label pricePay;
    public void setData(double price, Stage stage, MenuController menuController){
        this.stage = stage;
        this.menuController = menuController;
        DecimalFormat format = new DecimalFormat("0.##");
        pricePay.setText(format.format(price) + " THB");
    }
    @FXML
    public void goBack(){
        stage.close();
        menuController.resetBill();
    }
}
