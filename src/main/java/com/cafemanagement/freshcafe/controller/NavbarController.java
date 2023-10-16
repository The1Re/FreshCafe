package com.cafemanagement.freshcafe.controller;

import com.cafemanagement.freshcafe.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class NavbarController {
    @FXML private ImageView homebtn, menubtn, tablebtn, stockbtn, exitbtn;
    private BorderPane container;

    public void getPage(MouseEvent e) throws IOException {
        ImageView src = (ImageView) e.getSource();
        container = (BorderPane) src.getScene().getRoot();
        Node page = FXMLLoader.load(Main.class.getResource(getPath(src)));
        container.setCenter(page);
    }
    private String getPath(ImageView src){
        if (src == menubtn)
            return "pages/MenuPage.fxml";
        else if (src == tablebtn)
            return "pages/TablePage.fxml";
        else if (src == stockbtn)
            return "pages/StockPage.fxml";
        else
            return "pages/DashboardPage.fxml"; //HomePage
    }
    public void clickExitBtn(){
        System.exit(0);
    }
}
