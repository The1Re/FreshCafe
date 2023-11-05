package com.cafemanagement.freshcafe.controller;

import com.cafemanagement.freshcafe.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NavbarController implements Initializable {
    @FXML private ImageView menubtn, tablebtn, stockbtn, exitbtn;
    private BorderPane container;
    private ImageView selected;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initToolTip();
    }
    public void initToolTip(){
//        Tooltip dashboardTooltip = new Tooltip("Dashboard");
//        dashboardTooltip.setShowDelay(Duration.millis(300));
        Tooltip menuTooltip = new Tooltip("Menu");
        menuTooltip.setShowDelay(Duration.millis(300));
        Tooltip tableTooltip = new Tooltip("Table");
        tableTooltip.setShowDelay(Duration.millis(300));
        Tooltip stockTooltip = new Tooltip("Stock");
        stockTooltip.setShowDelay(Duration.millis(300));

//        Tooltip.install(homebtn, dashboardTooltip);
        Tooltip.install(menubtn, menuTooltip);
        Tooltip.install(tablebtn, tableTooltip);
        Tooltip.install(stockbtn, stockTooltip);

        selected = menubtn;
        selected.setStyle("-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 0);");
    }
    public void getPage(MouseEvent e) throws IOException {
        ImageView src = (ImageView) e.getSource();
        if (selected == src)
            return;
        container = (BorderPane) src.getScene().getRoot();
        Node page = FXMLLoader.load(Main.class.getResource(getPath(src)));
        container.setCenter(page);
        selected = src;
//        homebtn.setStyle("-fx-effect: null");
        menubtn.setStyle("-fx-effect: null");
        tablebtn.setStyle("-fx-effect: null");
        stockbtn.setStyle("-fx-effect: null");
        selected.setStyle("-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 0);");
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
