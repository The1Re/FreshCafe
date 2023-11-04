package com.cafemanagement.freshcafe.controller;

import com.cafemanagement.freshcafe.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BillCardController {
    private MenuController.BillProduct product;
    @FXML
    private Label pdAmount;

    @FXML
    private ImageView pdImage;

    @FXML
    private Label pdName;

    @FXML
    private Label pdPrice;
    public void setData(MenuController.BillProduct p){
        product = p;
        pdName.setText(p.getName());
        pdAmount.setText("x " + p.getAmount());
        pdPrice.setText(p.getPrice()*p.getAmount() + " THB");
        pdImage.setImage(new Image(p.getImage().getUrl()));
    }

    public MenuController.BillProduct getProduct(){
        return product;
    }
}
