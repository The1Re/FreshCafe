package com.cafemanagement.freshcafe.controller;

import com.cafemanagement.freshcafe.model.User;
import com.cafemanagement.freshcafe.util.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void signInBtn(ActionEvent event) {
        for (User user : data){
            if (userField.getText().equals(user.getUsername()) && passwordField.getText().equals(user.getPassword())){
                JOptionPane.showConfirmDialog(null, "Login Success!", "Status", JOptionPane.OK_CANCEL_OPTION);
                return;
            }
        }
        JOptionPane.showConfirmDialog(null, "Login Fail TryAgain!", "Status", JOptionPane.OK_CANCEL_OPTION);
    }

}