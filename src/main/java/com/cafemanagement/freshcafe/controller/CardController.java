package com.cafemanagement.freshcafe.controller;

import com.cafemanagement.freshcafe.model.BillProduct;
import com.cafemanagement.freshcafe.model.Product;
import com.cafemanagement.freshcafe.util.GlobalVar;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class CardController implements Initializable {
    private MenuController menuController;
    @FXML
    public AnchorPane pane;
    @FXML
    private ImageView pdImage;

    @FXML
    private Label pdName;

    @FXML
    private Label pdPrice;
    @FXML
    private Spinner<Integer> amount;
    private boolean isAdd = false;
    private BillProduct temp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100);
        valueFactory.setValue(0);
        amount.setValueFactory(valueFactory);
    }

    public void setData(Product product, MenuController menuController){
        this.menuController = menuController;

        pdImage.setImage(new Image(new File(GlobalVar.RESOURCE_PATH + "/database/images/" + product.getId()+product.getName() + ".jpg").getAbsolutePath()));
        pdName.setText(product.getName());
        pdPrice.setText(product.getPrice() + " THB");
    }

    @FXML
    public void addBtn(){
        if (isAdd && amount.getValue()!=0){
            temp.setAmount(amount.getValue());
            menuController.editBills(temp);
        }else{
            if (amount.getValue() == 0){
                if (temp == null)
                    return;
                menuController.removeBills(temp);
                temp = null;
            }else{
                BillProduct product = new BillProduct(
                        pdName.getText(),
                        Double.parseDouble(pdPrice.getText().substring(0, pdPrice.getText().length()-4)),
                        amount.getValue(),
                        pdImage.getImage()
                );

                menuController.addBills(product);
                temp = product;
            }
            isAdd = !isAdd;
        }
        menuController.updateBills();
    }
}
