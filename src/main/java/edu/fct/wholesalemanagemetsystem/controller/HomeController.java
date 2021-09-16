package edu.fct.wholesalemanagemetsystem.controller;

import com.jfoenix.controls.JFXButton;

import edu.fct.wholesalemanagemetsystem.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;

public class HomeController {

    @FXML
    private AnchorPane scenePane;

    @FXML
    private Label lUsername;

    @FXML
    private JFXButton btnDashbod;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private JFXButton btnClose;

    @FXML
    private JFXButton btnMinimize;
    private Stage stage;


    @FXML
    void closeApplication(ActionEvent event)  {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Do you want to close.");
        if (alert.showAndWait().get() == ButtonType.OK) {
            this.stage = (Stage)this.scenePane.getScene().getWindow();
            this.stage.close();
        }
    }

    @FXML
    void loginOut(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Login");
        stage.setScene(scene);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        stage.show();

        stage.setX((dimension.width/2) - (stage.getWidth()/2));
        stage.setY((dimension.height/2) - (stage.getHeight()/2));
    }

    @FXML
    void minimizeApplication(ActionEvent event) {
        stage = (Stage)this.scenePane.getScene().getWindow();
        stage.setIconified(true);
    }

}