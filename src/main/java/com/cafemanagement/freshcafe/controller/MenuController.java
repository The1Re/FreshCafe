package com.cafemanagement.freshcafe.controller;

import com.cafemanagement.freshcafe.Main;
import com.cafemanagement.freshcafe.model.BillProduct;
import com.cafemanagement.freshcafe.model.Product;
import com.cafemanagement.freshcafe.util.DBConnection;
import com.cafemanagement.freshcafe.util.PDFReceiptGenarator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

public class MenuController implements Initializable{
    private final double VAT = 0.07;
    private ObservableList<Product> data;
    Map<BillProduct, BillCardController> controller = new HashMap<>();
    Map<BillProduct, Node> node = new HashMap<>();
    private VBox billList;
    private Pane selectedCatagory;
    private GridPane listProduct;
    private double total, subtotal, vat;
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
    @FXML
    private Label subtotalBill;
    @FXML
    private Label totalBill;
    @FXML
    private Label vatBill;


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
            if (product.getStatus().equals("Unavailable"))
                continue;

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
            Node n = loader.load();
            BillCardController con = loader.getController();
            con.setData(p);

            node.put(p, n);
            controller.put(p, con);

            billList.getChildren().add(n);
            billScrollpane.setContent(billList);
        }catch (IOException e){
            System.out.println("Fail load FXML!");
            e.printStackTrace();
        }
    }
    public void editBills(BillProduct p){
        controller.get(p).setData(p);
    }
    public void removeBills(BillProduct p){
        billList.getChildren().remove(node.get(p));
        controller.remove(p);
        node.remove(p);
    }

    public void updateBills(){
        subtotal = 0;
        for (BillProduct bill : controller.keySet()){
            subtotal += bill.getPrice()*bill.getAmount();
        }
        DecimalFormat format = new DecimalFormat("0.##");
        vat = subtotal*VAT;
        total = subtotal+vat;
        subtotalBill.setText(format.format(subtotal) + " THB");
        vatBill.setText(format.format(vat) + " THB");
        totalBill.setText(format.format(total) + " THB");
    }

    @FXML
    public void checkBill(){
        if (total == 0)
            return;
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("pages/PaymentPage.fxml"));
            stage.setScene(new Scene(loader.load()));
            PaymentController con = loader.getController();
            con.setData(total, stage, this);

            stage.setTitle("Thank you...");
            stage.initOwner(Main.primaryStage);
            stage.getIcons().add(Main.logo);
            stage.initModality(Modality.NONE);
            stage.setResizable(false);
            stage.show();

            for (BillProduct billData: controller.keySet())
                changeData(billData);
            DBConnection.updateProduct(data);
        }catch (IOException e){
            System.out.println("Cannot load FXML!");
            e.printStackTrace();
        }
    }
    public void changeData(BillProduct billData){
        for (int i=0; i<data.size(); i++){
            if (billData.getName().equals(data.get(i).getName())){
                data.get(i).setQuantity( data.get(i).getQuantity()-billData.getAmount() );
                break;
            }
        }
    }

    public void resetBill(){
        controller.clear();
        node.clear();
        billList.getChildren().clear();
        updateBills();
        updateList();
    }

    public Set<BillProduct> getBillProduct(){
        return controller.keySet();
    }
}
