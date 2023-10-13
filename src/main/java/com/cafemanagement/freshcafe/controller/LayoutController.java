package com.cafemanagement.freshcafe.controller;

import com.cafemanagement.freshcafe.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LayoutController implements Initializable {
    @FXML
    private HBox container;

    private Node nav;
    private Node page;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            nav = FXMLLoader.load(Main.class.getResource("pages/Navigation.fxml"));
            page = FXMLLoader.load(Main.class.getResource("pages/DashBoardPage.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        container.setPadding(new Insets(15,15,15,15));
        container.getChildren().add(nav);
        container.getChildren().add(page);

    }
}
