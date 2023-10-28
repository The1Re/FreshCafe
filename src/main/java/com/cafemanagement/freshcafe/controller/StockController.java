package com.cafemanagement.freshcafe.controller;

import com.cafemanagement.freshcafe.model.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.cafemanagement.freshcafe.util.DBConnection;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StockController implements Initializable {
    @FXML
    private TableView<Product> table;
    @FXML
    private TableColumn<Product, String> id;
    @FXML
    private TableColumn<Product, String> name;
    @FXML
    private TableColumn<Product, Double> price;
    @FXML
    private TableColumn<Product, Integer> quantity;
    @FXML
    private TableColumn<Product, String> category;
    @FXML
    private TableColumn<Product, Boolean> status;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            ArrayList<Product> data = DBConnection.getProductData();
            System.out.println("Complete [Get]Product");

            id.setCellValueFactory(new PropertyValueFactory<Product, String>("id"));
            name.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
            price.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
            quantity.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
            category.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
            status.setCellValueFactory(new PropertyValueFactory<Product, Boolean>("status"));

            table.getItems().addAll(data);

        }catch (Exception e){
            System.out.println("Fail [Get]Product");
            e.printStackTrace();
        }
    }

    public void testAddProduct() throws IOException {
            ArrayList<Product> data = new ArrayList<>();
            Product p;
            data.add(p = new Product("AX000", "XXXXX", 69, 100, "Drink", true));
            DBConnection.updateProduct(data, true);
            table.getItems().add(p);
    }
}
