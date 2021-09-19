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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerController {

    @FXML
    private JFXButton btnNewCustomer;

    @FXML
    private JFXButton btnSearchCustomer;

    @FXML
    private AnchorPane customerPane;

    @FXML
    private TableView<Object> tableCustomerDetails;

    @FXML
    private TableColumn<?, ?> col1CustomerID;

    @FXML
    private TableColumn<?, ?> col2CustomerName;

    @FXML
    private TableColumn<?, ?> col3TeleNumber;

    @FXML
    private TableColumn<?, ?> col4CustomerAddress;
    private ObservableList<Object> data;


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

        try {
            Connection con = DBConnection.getInstance().getConnection();
            data=FXCollections.observableArrayList();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from customer");
            while (rs.next()) {
                data.add(new ShowData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        }
        catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        col1CustomerID.setCellValueFactory(new PropertyValueFactory("customer_id"));
        col2CustomerName.setCellValueFactory(new PropertyValueFactory("customer_name"));
        col3TeleNumber.setCellValueFactory(new PropertyValueFactory("telephone_no"));
        col4CustomerAddress.setCellValueFactory(new PropertyValueFactory("customer_address"));
        tableCustomerDetails.setItems(null);
        tableCustomerDetails.setItems(data);
    }

}

    class ShowData {

        private final StringProperty customer_id;
        private final StringProperty customer_name;
        private final StringProperty telephone_no;
        private final StringProperty customer_address;

        public ShowData(String customer_id, String customer_name, String telephone_no, String customer_address) {
            this.customer_id = new SimpleStringProperty(customer_id);
            this.customer_name = new SimpleStringProperty(customer_name);
            this.telephone_no = new SimpleStringProperty(telephone_no);
            this.customer_address = new SimpleStringProperty(customer_address);
        }

        public String getCustomer_id() {
            return customer_id.get();
        }

        public String getCustomer_name() {
            return customer_name.get();
        }

        public String getTelephone_no() {
            return telephone_no.get();
        }

        public String getCustomer_address() {
            return customer_address.get();
        }

        public void setCustomer_id(String value) {
            customer_id.setValue(value);
        }

        public void setCustomer_name(String value) {
            customer_name.setValue(value);
        }

        public void setTelephone_no(String value) {
            telephone_no.setValue(value);
        }

        public void setCustomer_address(String value) {
            customer_address.setValue(value);
        }

        public StringProperty customer_addressProperty() {
            return customer_address;
        }

        public StringProperty customer_idProperty() {
            return customer_id;
        }

        public StringProperty customer_nameProperty() {
            return customer_name;
        }

        public StringProperty telephone_noProperty() {
            return telephone_no;
        }
    }