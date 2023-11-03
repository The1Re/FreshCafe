package com.cafemanagement.freshcafe.controller;

import com.cafemanagement.freshcafe.Main;
import com.cafemanagement.freshcafe.model.Product;
import com.cafemanagement.freshcafe.util.DBConnection;
import com.cafemanagement.freshcafe.util.GlobalVar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable{
    private ObservableList<Product> data;
    @FXML
    private ScrollPane productPane;
    @FXML
    private Pane catagoryAllmenu;
    @FXML
    private Pane catagoryCoffee;
    @FXML
    private Pane catagoryMilk;
    @FXML
    private Pane catagoryTea;
    @FXML
    private ScrollPane billScrollpane;
    private VBox billList;
    private Pane selectedCatagory;
    private GridPane listProduct;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            data = FXCollections.observableList(DBConnection.getProductData());
            selectedCatagory = catagoryAllmenu;
            listProduct = new GridPane();
            listProduct.setHgap(80);
            listProduct.setVgap(40);
            listProduct.setPadding(new Insets(0,0,20,45));
            billList = new VBox();

            updateList();
        } catch (IOException e) {
            System.out.println("Fail load data!");
            e.printStackTrace();
        }
    }

    @FXML
    public void selectCatagory(MouseEvent e){
        selectedCatagory = (Pane) e.getSource();
        updateList();

        catagoryAllmenu.getStyleClass().removeAll("primary-bg");
        catagoryMilk.getStyleClass().removeAll("primary-bg");
        catagoryCoffee.getStyleClass().removeAll("primary-bg");
        catagoryTea.getStyleClass().removeAll("primary-bg");
        selectedCatagory.getStyleClass().addAll("primary-bg");
    }

    public void updateList(){
        listProduct.getChildren().clear();
        int row = 0, col = 0;
        for (Product product : data){
            String catagory = product.getCategory();

            if (selectedCatagory == catagoryCoffee && catagory.equals("Coffee")){
                loadData(product, col++, row);
            }else if (selectedCatagory == catagoryTea && catagory.equals("Tea")){
                loadData(product, col++, row);
            }else if (selectedCatagory == catagoryMilk && catagory.equals("Milk")){
                loadData(product, col++, row);
            }else if (selectedCatagory == catagoryAllmenu){
                loadData(product, col++, row);
            }

            if (col == 2){
                col = 0;
                row++;
            }
        }
        productPane.setContent(listProduct);

    }

    public void loadData(Product product, int col, int row){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("pages/Card.fxml"));
            Pane pane = loader.load();
            CardController card = loader.getController();
            card.setData(product, this);

            listProduct.add(pane, col, row);
        }catch (IOException e){
            System.out.println("Fail load FXML!");
        }
    }

    public void addBills(BillProduct p){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("pages/BillCard.fxml"));
            Node node = loader.load();
            BillCardController controller = loader.getController();
            controller.setData(p);

            billList.getChildren().add(node);
            billScrollpane.setContent(billList);
        }catch (IOException e){
            System.out.println("Fail load FXML!");
            e.printStackTrace();
        }
    }

    public static class BillProduct{
        private String name;
        private double price;
        private int amount;
        private Image image;

        public BillProduct(String name, double price, int amount, Image image) {
            this.name = name;
            this.price = price;
            this.amount = amount;
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getAmount() {
            return amount;
        }

        public Image getImage() {
            return image;
        }
    }
}
