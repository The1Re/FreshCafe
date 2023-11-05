package com.cafemanagement.freshcafe.controller;

import com.cafemanagement.freshcafe.util.PDFReceiptGenarator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class PaymentController {
    private Stage stage;
    private MenuController menuController;
    private String pathPdf = null;
    @FXML
    private Label pricePay;
    @FXML
    private Label log;
    @FXML
    private Button openPdfBtn;
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
        pathPdf = PDFReceiptGenarator.save(menuController.getBillProduct(), "receipt.pdf");
        openPdfBtn.getStyleClass().add("primary-bg");
        log.setText("Save pdf as " + pathPdf);
    }

    @FXML
    public void openPdf(){
        if (pathPdf != null && Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(pathPdf);
                Desktop.getDesktop().open(myFile);
            } catch (IOException e) {
                System.out.println("Cannot open Pdf!");
                e.printStackTrace();
            }
        }
    }
}
