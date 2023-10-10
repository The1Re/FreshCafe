package com.cafemanagement.freshcafe.controller;

import com.cafemanagement.freshcafe.Main;
import com.cafemanagement.freshcafe.model.User;
import com.cafemanagement.freshcafe.util.DBConnection;
import com.cafemanagement.freshcafe.util.SceneSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    List<User> data = new ArrayList<>();
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button signInBtn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            data = DBConnection.getUserData();
            System.out.println("Data Connected!");
        }catch (IOException e){
            System.out.println("Fail Data Connected!");
            e.printStackTrace();
        }
    }
    @FXML
    protected void signInBtn(ActionEvent event) throws IOException {
        for (User user : data){
            if (userField.getText().equals(user.getUsername()) && passwordField.getText().equals(user.getPassword())){
                SceneSwitch.change((Stage)signInBtn.getScene().getWindow(), "pages/RegisterPage.fxml");
                return;
            }
        }
        JOptionPane.showConfirmDialog(null, "Login Fail TryAgain!", "Status", JOptionPane.OK_CANCEL_OPTION);
    }

    @FXML
    protected void signUpBtn(ActionEvent event) throws IOException {
        SceneSwitch.change((Stage)signInBtn.getScene().getWindow(), "pages/RegisterPage.fxml");
    }

}