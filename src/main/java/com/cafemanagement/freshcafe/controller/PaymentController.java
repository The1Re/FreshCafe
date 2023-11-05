package com.cafemanagement.freshcafe.controller;

import com.cafemanagement.freshcafe.util.PDFReceiptGenarator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class PaymentController {
    private Stage stage;
    private MenuController menuController;
    @FXML
    private Label pricePay;
    @FXML
    private Label log;
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

    @FXML
    public void savePdf(){
        String path = PDFReceiptGenarator.save(menuController.getBillProduct(), "receipt.pdf");
        log.setText("Save pdf as " + path);
    }
}
