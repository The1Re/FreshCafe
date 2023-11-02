package com.cafemanagement.freshcafe.controller;

import com.cafemanagement.freshcafe.Main;
import com.cafemanagement.freshcafe.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.cafemanagement.freshcafe.util.DBConnection;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class StockController implements Initializable {

    public static ObservableList<Product> data;

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
    private TableColumn<Product, String> status;
    @FXML
    private TableColumn edit;
    @FXML
    private Label readyAmount, almostAmount, outAmount;
    @FXML
    private TextField searchField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            data = FXCollections.observableArrayList(DBConnection.getProductData());
            System.out.println("Complete [Get]Product");

            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            price.setCellValueFactory(new PropertyValueFactory<>("price"));
            quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            category.setCellValueFactory(new PropertyValueFactory<>("category"));
            status.setCellValueFactory(new PropertyValueFactory<>("status"));

            //Edit Button
            Callback<TableColumn<Product, String>, TableCell<Product, String>> cellFactory = (parem)->{
                final TableCell<Product, String> cell = new TableCell<>(){
                    @Override
                    public void updateItem(String item, boolean empty){
                        super.updateItem(item, empty);

                        if (empty){
                            setGraphic(null);
                        }else{
                            final Button editButton = new Button("EDIT");
                            final Button deleteButton = new Button("DELETE");

                            final FlowPane pane = new FlowPane(editButton, deleteButton);
                            pane.setPrefHeight(10);
                            pane.setHgap(10);
                            pane.setAlignment(Pos.CENTER);

                            Product p = getTableView().getItems().get(getIndex());
                            editButton.setOnAction( (e) ->{ editBtn(p); });
                            deleteButton.setOnAction( (e)-> { deleteBtn(p); });

                            editButton.getStyleClass().addAll("primary-bg", "primary-btn");
                            deleteButton.getStyleClass().addAll("danger-bg", "primary-btn");

                            setGraphic(pane);
                        }
                        setText(null);
                    }
                };


                return cell;
            };
            edit.setCellFactory(cellFactory);


            //Search Bar
            FilteredList<Product> filteredData = new FilteredList<>(data, b -> true);
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(data -> {
                    if (newValue.isEmpty() || newValue.isBlank())
                        return true;
                    String searchkw = newValue.toLowerCase();
                    if (data.getName().toLowerCase().contains(searchkw))
                        return true;
                    else if(data.getCategory().toLowerCase().contains(searchkw))
                        return true;
                    else if(data.getId().toLowerCase().contains(searchkw))
                        return true;
                    else if (data.getStatus().toLowerCase().contains(searchkw))
                        return true;
                    else
                        return false;
                });
            });

            SortedList<Product> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(table.comparatorProperty());

            table.setItems(sortedData);
            updateStatusAmount();

        }catch (Exception e){
            System.out.println("Fail [Get]Product");
            e.printStackTrace();
        }
    }

    public void updateStatusAmount(){
        int ready = 0, almost = 0, out = 0;

        for (Product p : data){
            if (p.getStatus().equals("Available")){
                if (p.getQuantity() > 50)
                    ready++;
                else
                    almost++;
            }else{
                out++;
            }
        }

        readyAmount.setText(Integer.toString(ready));
        almostAmount.setText(Integer.toString(almost));
        outAmount.setText(Integer.toString(out));
    }

    private FXMLLoader loadPopUp(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("pages/StockPopup.fxml"));
        stage.setScene(new Scene(loader.load()));

        stage.initModality(Modality.WINDOW_MODAL);
        stage.getIcons().add(Main.logo);
        stage.initOwner(Main.primaryStage);
        stage.setResizable(false);
        stage.show();
        return loader;
    }
    @FXML
    private void addProductButton() throws IOException {
        try{
            Stage stage = new Stage();
            stage.setTitle("Adding Stock...");
            StockPopupController popup = loadPopUp(stage).getController();
            popup.initData(stage, this);
        }catch (IOException e){
            System.out.println("Cannot load FXML!");
        }
    }


    private void editBtn(Product product) {
        try{
            Stage stage = new Stage();
            stage.setTitle("Edit Stock...");
            StockPopupController popup = loadPopUp(stage).getController();
            popup.initData(product, stage, this);
        }catch (IOException e){
            System.out.println("Cannot load FXML!");
        }
    }
    public void editData(Product oldProduct, Product newProduct){
        data.set(data.indexOf(oldProduct), newProduct);
    }

    private void deleteBtn(Product product) {
        ObservableList<Product> temp = data;
        try{
            temp.remove(product);
            DBConnection.updateProduct(temp);
            data = temp;
        }catch (IOException e){
            System.out.println("Fail to Delete!");
        }
    }

}
