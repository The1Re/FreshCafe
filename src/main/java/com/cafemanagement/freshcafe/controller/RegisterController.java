package com.cafemanagement.freshcafe.controller;

import com.cafemanagement.freshcafe.model.User;
import com.cafemanagement.freshcafe.util.DBConnection;
import com.cafemanagement.freshcafe.util.SceneSwitch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    private ObservableList<User> data;
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField emailField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            data = FXCollections.observableList(DBConnection.getUserData());
            System.out.println("Data Connected!");
        }catch (IOException e){
            System.out.println("Fail Data Connected!");
        }
    }

    @FXML
    protected void signUpBtn(ActionEvent event){
        Stage stage = (Stage)((Parent)event.getSource()).getScene().getWindow();
        String user = userField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        if (user.isEmpty())
            System.out.println("Type User!");
        else if (password.isEmpty())
            System.out.println("Type Password!");
        else if (email.isEmpty())
            System.out.println("Type Email!");
        else{
            data.add(new User(user, password, email));
            try {
                DBConnection.updateUser(data);
                System.out.println("Add data Complete!");
                LoginController.member = user;
                SceneSwitch.change(stage);
            }catch (IOException e) {
                System.out.println("Cannot Add new Data!");
            }
        }
    }

    @FXML
    protected void signInBtn(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Parent)event.getSource()).getScene().getWindow();
        SceneSwitch.change(stage, "pages/LoginPage.fxml");
    }
}
