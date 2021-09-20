package edu.fct.wholesalemanagemetsystem.controller;

import com.jfoenix.controls.JFXButton;
import edu.fct.wholesalemanagemetsystem.Main;
import edu.fct.wholesalemanagemetsystem.db.DBConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    @FXML
    private JFXButton btnNewCustomer;

    @FXML
    private JFXButton btnSearchCustomer;

    @FXML
    private AnchorPane customerPane;

    @FXML
    private TableView<CustomerTableModel> tableCustomerDetails;

    @FXML
    private TableColumn<CustomerTableModel, String> col1CustomerID;

    @FXML
    private TableColumn<CustomerTableModel, String> col2CustomerName;

    @FXML
    private TableColumn<CustomerTableModel, String> col3TeleNumber;

    @FXML
    private TableColumn<CustomerTableModel, String> col4CustomerAddress;
//    private ObservableList<Object> data;

    ObservableList<CustomerTableModel> customerdatalist = FXCollections.observableArrayList();

    public void customerDetails() throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("customerDetails.fxml"));
        customerPane.getChildren().add(newLoadedPane);
    }

    public void newCustomer() throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("newCustomer.fxml"));
        customerPane.getChildren().add(newLoadedPane);
        btnNewCustomer.setDefaultButton(true);
        btnSearchCustomer.setDefaultButton(false);
    }

    public void searchCustomer() throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("searchCustomer.fxml"));
        customerPane.getChildren().add(newLoadedPane);
        btnNewCustomer.setDefaultButton(false);
        btnSearchCustomer.setDefaultButton(true);
    }

    @FXML
    void btnLoadNewCustomer(ActionEvent event) throws IOException {
        newCustomer();
    }

    @FXML
    void btnLordSearchCustomer(ActionEvent event) throws IOException {
        searchCustomer();
    }

    @FXML
    void showCustomerDetails(ActionEvent event) throws SQLException, ClassNotFoundException {
//
//        try {
//            Connection con = DBConnection.getInstance().getConnection();
//            data=FXCollections.observableArrayList();
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery("select * from customer");
//            while (rs.next()) {
//                data.add(new ShowData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
//            }
//        }
//        catch (SQLException ex) {
//            System.err.println("Error"+ex);
//        }
//        col1CustomerID.setCellValueFactory(new PropertyValueFactory("customer_id"));
//        col2CustomerName.setCellValueFactory(new PropertyValueFactory("customer_name"));
//        col3TeleNumber.setCellValueFactory(new PropertyValueFactory("telephone_no"));
//        col4CustomerAddress.setCellValueFactory(new PropertyValueFactory("customer_address"));
//        tableCustomerDetails.setItems(null);
//        tableCustomerDetails.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from customer");
            while (rs.next()){
                customerdatalist.add(new CustomerTableModel(rs.getString("customer_id"),rs.getString("customer_id"),rs.getString("telephone_no"),rs.getString("customer_address")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        col1CustomerID.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        col2CustomerName.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        col3TeleNumber.setCellValueFactory(new PropertyValueFactory<>("telephone_no"));
        col4CustomerAddress.setCellValueFactory(new PropertyValueFactory<>("customer_address"));
        tableCustomerDetails.setItems(customerdatalist);
    }

}

