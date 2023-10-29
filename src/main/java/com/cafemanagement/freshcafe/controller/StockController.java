package com.cafemanagement.freshcafe.controller;

import com.cafemanagement.freshcafe.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.cafemanagement.freshcafe.util.DBConnection;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class StockController implements Initializable {

    private ObservableList<Product> data;
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

    private void updateStatusAmount(){
        int ready = 0, almost = 0, out = 0;

        for (Product p : data){
            if (p.getStatus()){
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

//    public void savingFile(ActionEvent e){
//        FileChooser chooser = new FileChooser();
//        configFileChooser(chooser);
//        File file = chooser.showOpenDialog(table.getScene().getWindow());
//        if (file != null){
//            Image image = new Image(file.getPath());
//            imageView.setImage(image);
//        }else{
//            System.out.println("Fail Saving File");
//        }
//    }
//
//    public void configFileChooser(final FileChooser fileChooser){
//        fileChooser.setTitle("Saving Image...");
//        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("All Image","*.*"),
//                new FileChooser.ExtensionFilter("jpg", "*.jpg"),
//                new FileChooser.ExtensionFilter("png", "*.png")
//        );
//    }
}
