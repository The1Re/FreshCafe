module com.cafemanagement.freshcafe {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.cafemanagement.freshcafe to javafx.fxml;
    exports com.cafemanagement.freshcafe;
    exports com.cafemanagement.freshcafe.controller;
    exports com.cafemanagement.freshcafe.model;
    opens com.cafemanagement.freshcafe.controller to javafx.fxml;
}