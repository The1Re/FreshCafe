package com.cafemanagement.freshcafe.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {
    @FXML
    private AreaChart myChart;
    @FXML
    private Button addEmpolyee,viewAllEmpolyee;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series series = new XYChart.Series();
        series.setName("July");
        series.getData().add(new XYChart.Data<>(1,4500));
        series.getData().add(new XYChart.Data<>(3,2550));
        series.getData().add(new XYChart.Data<>(6,8100));
        series.getData().add(new XYChart.Data<>(9,6200));
        series.getData().add(new XYChart.Data<>(12,5300));
        series.getData().add(new XYChart.Data<>(14,2400));
        series.getData().add(new XYChart.Data<>(21,4500));
        series.getData().add(new XYChart.Data<>(31,4600));

        myChart.getData().addAll(series);
    }
}
