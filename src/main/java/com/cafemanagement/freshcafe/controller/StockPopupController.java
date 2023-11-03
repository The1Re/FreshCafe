package com.cafemanagement.freshcafe.controller;

import com.cafemanagement.freshcafe.Main;
import com.cafemanagement.freshcafe.model.Product;
import com.cafemanagement.freshcafe.util.DBConnection;
import com.cafemanagement.freshcafe.util.GlobalVar;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;

public class StockPopupController implements Initializable {
    private Stage primaryStage;
    private StockController parentController;
    @FXML
    private ImageView pdImage;

    @FXML
    private TextField pdId;

    @FXML
    private TextField pdName;

    @FXML
    private TextField pdPrice;

    @FXML
    private ComboBox<String> pdStatus;

    @FXML
    private TextField pdQuantity;

    @FXML
    private ComboBox<String> pdCategory;
    @FXML
    private Label errorMessage;
    @FXML
    private Button mainBtn;
    private Product product;
    public void initData(Stage primaryStage, StockController parentController){
        initData(null, primaryStage, parentController);
    }
    public void initData(Product product, Stage primaryStage, StockController parentController){
        this.primaryStage = primaryStage;
        this.parentController = parentController;
        this.product = product;

        if (product != null){
            editPopUp(product);
        }
    }

    private void editPopUp(Product p){
        mainBtn.setText("Edit");
        pdId.setText(p.getId());
        pdId.setDisable(true);
        pdName.setText(p.getName());
        pdName.setDisable(true);
        pdPrice.setText(Double.toString(p.getPrice()));
        pdQuantity.setText(Integer.toString(p.getQuantity()));
        pdCategory.setValue(p.getCategory());
        pdStatus.setValue(p.getStatus());

        File file = new File(GlobalVar.RESOURCE_PATH + "/database/images/" + p.getId()+p.getName() + ".jpg");
        if (file.exists()){
            pdImage.setImage(new Image(file.getAbsolutePath()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pdStatus.getItems().add("Available");
        pdStatus.getItems().add("Unavailable");

        pdCategory.getItems().add("Coffee");
        pdCategory.getItems().add("Milk");
        pdCategory.getItems().add("Tea");
    }

    @FXML
    private void importImage(){
        FileChooser chooser = new FileChooser();
        configFileChooser(chooser);
        File file = chooser.showOpenDialog(pdName.getScene().getWindow());
        if (file != null){
            Image image = new Image(file.getAbsolutePath());
            pdImage.setImage(image);
        }else{
            System.out.println("Fail Saving File");
        }
    }

    private void configFileChooser(final FileChooser fileChooser){
        fileChooser.setTitle("Saving Image...");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Image","*.*"),
                new FileChooser.ExtensionFilter("jpg", "*.jpg"),
                new FileChooser.ExtensionFilter("png", "*.png")
        );
    }

    @FXML
    public void close(){
        primaryStage.close();
    }

    @FXML
    public void getData() throws IOException {
        int stock; double price;
        String id = pdId.getText();
        String name = pdName.getText();
        String type = pdCategory.getValue();
        String status = pdStatus.getValue();
        if (id.isEmpty() || name.isEmpty() || type == null || status == null || pdQuantity.getText().isEmpty() || pdPrice.getText().isEmpty()){
            errorMessage.setText("Error: Please fill in all the fields!");
            return;
        }

        try{
            stock = Integer.parseInt(pdQuantity.getText());
        }catch (NumberFormatException e){
            errorMessage.setText("Error: Number in stock is Incorrect!");
            return;
        }

        try {
            price = Double.parseDouble(pdPrice.getText());
        }catch (NumberFormatException e){
            errorMessage.setText("Error: Number in price is Incorrect!");
            return;
        }

        Product p = new Product(id, name, price, stock, type, status);
        if (product == null){ //Add Product
            add(p);
        }else{ //Edit Product
            parentController.editData(product, p);
            parentController.updateStatusAmount();
            close();
        }
    }
    private void add(Product p) throws IOException {
        StockController.data.add(p);
        DBConnection.updateProduct(p);
        DBConnection.saveImage(pdImage, p);

        parentController.updateStatusAmount();
        close();
    }

}
