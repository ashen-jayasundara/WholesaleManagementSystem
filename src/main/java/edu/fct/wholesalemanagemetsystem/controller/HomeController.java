package edu.fct.wholesalemanagemetsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.*;

public class HomeController {

    @FXML
    private ImageView btnMinimize;

    @FXML
    private ImageView btnclose;
    private Stage stage;
    private Node scenePane;

//    @FXML
//    void exitSystem(MouseEvent event) {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Logout");
//        alert.setHeaderText("Do you want to close");
//        if (alert.showAndWait().get() == ButtonType.OK) {
//            this.stage = (Stage)this.scenePane.getScene().getWindow();
//            this.stage.close();
//        }
//    }
//    @FXML
//    public void minimizeApplication(MouseEvent mouseEvent) {
//        this.stage.toBack();
//    }

}
