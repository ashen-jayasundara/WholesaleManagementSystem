package edu.fct.wholesalemanagemetsystem.controller;

import edu.fct.wholesalemanagemetsystem.db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class CustomerViewController implements Initializable {

    @FXML
    private TableView<CustomerTableModel> tableCustomerDetails;

    @FXML
    private TableColumn<?, ?> col1CustomerID;

    @FXML
    private TableColumn<?, ?> col2CustomerName;

    @FXML
    private TableColumn<?, ?> col3TeleNumber;

    @FXML
    private TableColumn<?, ?> col4CustomerAddress;

    ObservableList<CustomerTableModel> customerdatalist = FXCollections.observableArrayList();

    @FXML
    void showCustomerDetails(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCustomer();
    }

    public void loadCustomer(){

        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from customer");
            while (rs.next()){
                customerdatalist.add(new CustomerTableModel(rs.getString("customer_id"),rs.getString("customer_name"),rs.getString("telephone_no"),rs.getString("customer_address")));
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
