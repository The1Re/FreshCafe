package com.cafemanagement.freshcafe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("pages/LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("FreshCafe");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("assets/logo.png")));
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}