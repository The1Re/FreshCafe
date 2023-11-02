package com.cafemanagement.freshcafe.controller;

import com.cafemanagement.freshcafe.Main;
import com.cafemanagement.freshcafe.model.Product;
import com.cafemanagement.freshcafe.util.DBConnection;
import com.cafemanagement.freshcafe.util.GlobalVar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable{
    private ObservableList<Product> data;
    @FXML
    private FlowPane pane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            data = FXCollections.observableArrayList(DBConnection.getProductData());
            pane.setAlignment(Pos.CENTER);
            for (Product p : data){
                File file = new File(GlobalVar.RESOURCE_PATH + "/database/images/" +p.getId()+p.getName() + ".jpg");
                if (file.exists()){
                    ImageView iv = new ImageView(new Image(file.getAbsolutePath()));
                    iv.setPreserveRatio(false);
                    iv.setFitWidth(124);
                    iv.setFitHeight(143);
                    pane.getChildren().add(iv);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
