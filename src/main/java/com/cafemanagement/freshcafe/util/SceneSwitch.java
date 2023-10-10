package com.cafemanagement.freshcafe.util;

import com.cafemanagement.freshcafe.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitch {

    public static void change(Stage stage, String new_page_path) throws IOException {
        stage.setMaximized(false);
        Parent root = FXMLLoader.load(Main.class.getResource(new_page_path));
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();
    }

    public static void home(Stage stage) throws IOException {
        change(stage, "pages/DashBoardPage.fxml");
    }
}
