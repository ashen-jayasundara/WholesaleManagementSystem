package edu.fct.wholesalemanagemetsystem.controller;

import edu.fct.wholesalemanagemetsystem.Main;
import edu.fct.wholesalemanagemetsystem.db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    private AnchorPane scenePane;

    @FXML
    private ImageView btnClose;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private TextField tfUsername;

    @FXML
    private Button btnLogin;
    private Stage stage;
    private Scene scene;

    @FXML
    void closeApplication(MouseEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Do you want to close.");
        if (alert.showAndWait().get() == ButtonType.OK) {
            this.stage = (Stage)this.scenePane.getScene().getWindow();
            this.stage.close();
        }
    }


    @FXML
    void logIntoSystem(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        String username = this.tfUsername.getText();
        String password = this.pfPassword.getText();
        Connection con = DBConnection.getInstance().getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from users where userName='" + username + "'");
        if (rs.next()) {
            if (rs.getString(2).equals(password)) {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home.fxml"));
                scene = new Scene(fxmlLoader.load());
//                HomeController home = fxmlLoader.getController();
//                home.displayUserName(username);
                this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                this.stage.setScene(this.scene);
                this.stage.setTitle("Home");
                //stage.setScene(scene);
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                stage.show();

                stage.setX((dimension.width/2) - (stage.getWidth()/2));
                stage.setY((dimension.height/2) - (stage.getHeight()/2));
            } else {
                JOptionPane.showMessageDialog((Component) null, "invalid password");
            }
        } else {
            JOptionPane.showMessageDialog((Component) null, "invalid username");
        }
    }

//    @FXML
//    void showPassword(ActionEvent event) {
//        if(cbShowPassword.isSelected()){
//            pfPassword.setEchoChar((char)0);
//        }else{
//            pfPassword.setEchoChar('*');
//        }
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}