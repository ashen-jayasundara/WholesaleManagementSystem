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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;

public class HomeController {

    @FXML
    private AnchorPane scenePane;

    @FXML
    private AnchorPane showPane;

    @FXML
    private Label lUsername;

    @FXML
    private JFXButton btnDashbod;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private JFXButton btnClose;

    @FXML
    private JFXButton btnMinimize;
    private Stage stage;
    private Scene scene;


    @FXML
    void closeApplication(ActionEvent event)  {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close");
        alert.setHeaderText("Do you want to close.");
        if (alert.showAndWait().get() == ButtonType.OK) {
            this.stage = (Stage)this.scenePane.getScene().getWindow();
            this.stage.close();
        }
    }

    @FXML
    void loginOut(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        scene = new Scene(fxmlLoader.load());
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.stage.setScene(this.scene);
        this.stage.setTitle("Login");
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

    @FXML
    void btnLoadDashboard(ActionEvent event) throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("dashboard.fxml"));
        showPane.getChildren().add(newLoadedPane);
        btnDashbod.setDefaultButton(true);
        btnPlaceOrder.setDefaultButton(false);

    }

    @FXML
    void btnLordPlaceOrder(ActionEvent event) throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("placeOrder.fxml"));
        showPane.getChildren().add(newLoadedPane);
        btnDashbod.setDefaultButton(false);
        btnPlaceOrder.setDefaultButton(true);
    }

    public void displayUserName(String username) {
        lUsername.setText(username);
    }

    public void btnLoadDashboard1() throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("dashboard.fxml"));
        showPane.getChildren().add(newLoadedPane);
        btnDashbod.setDefaultButton(true);
    }
}

//test change 2