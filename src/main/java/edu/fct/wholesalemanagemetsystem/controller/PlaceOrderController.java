package edu.fct.wholesalemanagemetsystem.controller;

import com.jfoenix.controls.JFXButton;
import edu.fct.wholesalemanagemetsystem.Main;
import edu.fct.wholesalemanagemetsystem.db.DBConnection;
import edu.fct.wholesalemanagemetsystem.model.Customer;
import edu.fct.wholesalemanagemetsystem.model.Item;
import edu.fct.wholesalemanagemetsystem.model.PlaceOrderTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PlaceOrderController implements Initializable {
    @FXML
    private AnchorPane placeOrderPane;

    @FXML
    private ComboBox <String> cmdCustomerID;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtOrderID;

    @FXML
    private TextField txtDate;

    @FXML
    private TableView<PlaceOrderTable> tableOrderDesc;

    @FXML
    private TableColumn<PlaceOrderTable, String> col1ItemID;

    @FXML
    private TableColumn<PlaceOrderTable, String> col2ItemDescription;

    @FXML
    private TableColumn<PlaceOrderTable, Integer> col3ItemQty;

    @FXML
    private TableColumn<PlaceOrderTable, Double> col4ItemUnitPrice;

    @FXML
    private TableColumn<PlaceOrderTable, Double> col5Price;

    @FXML
    private ComboBox<String > cmdItemID;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtTotal;

    @FXML
    private JFXButton btnPrint;

    @FXML
    private JFXButton btnRemove;

    @FXML
    private JFXButton btnClear;

    ObservableList customerIdList = FXCollections.observableArrayList();
    ObservableList itemIdList = FXCollections.observableArrayList();
    ObservableList<PlaceOrderTable> placeOrderTableObservableList = FXCollections.observableArrayList();

    @FXML
    void txtAddItemToTableOnAction(ActionEvent event) {
        String itemCode = cmdItemID.getValue();
        String descriton = txtDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        double price = qty * unitPrice;
        PlaceOrderTable placeOrderTable = new PlaceOrderTable(itemCode,descriton,qty,unitPrice,price);

        placeOrderTableObservableList.add(placeOrderTable);
        col1ItemID.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        col2ItemDescription.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        col3ItemQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col4ItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        col5Price.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableOrderDesc.setItems(placeOrderTableObservableList);

        double total = 0 ;
        for (PlaceOrderTable pot : tableOrderDesc.getItems()) {
            total = total + pot.getPrice();
        }
        txtTotal.setText(String.valueOf(total));
        updateQtyOnHand();
    }

    @FXML
    void btnClearAllFieldsOnAction(ActionEvent event) throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("placeOrder.fxml"));
        placeOrderPane.getChildren().add(newLoadedPane);
    }

    @FXML
    void btnPrintAndSaveOnAction(ActionEvent event) {
        String order_id=txtOrderID.getText();
        String customer_id=cmdCustomerID.getValue();
        String date=(String) txtDate.getText();
        String total=(String) txtTotal.getText();

            try {
                Connection con = DBConnection.getInstance().getConnection();
                Statement st = con.createStatement();
                st.executeUpdate("insert into orders values ('" + order_id + "','" + customer_id + "','" + date + "','" + total + "')");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Order Successfully Added!");

                alert.showAndWait();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("");
                alert.setContentText("Order Failed!");

                alert.showAndWait();
            }
    }

    @FXML
    void btnRemoveFieldOnAction(ActionEvent event) {
        tableOrderDesc.getItems().removeAll(tableOrderDesc.getSelectionModel().getSelectedItem());
    }

    @FXML
    void tblShowOrderDescOnAction(ActionEvent event) {

    }
    @FXML
    void cmbCustomerIDOnAction(ActionEvent event) {
        try {
        String id = cmdCustomerID.getValue();
        Customer customer  = SearchCustomerController.searchCustomer(id);
        txtCustomerName.setText(customer.getCustomer_name());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cmbItemIDOnAction(ActionEvent event) {
        try {
            String id = cmdItemID.getValue();
            Item item  = SearchItemController.searchItem(id);
            txtDescription.setText(item.getItem_name());
            txtUnitPrice.setText("" + item.getUnit_prize());
            txtQtyOnHand.setText(item.getAvailable_quantity() + "");
            txtUnitPrice.requestFocus();
            txtUnitPrice.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setOrderDate() {
        LocalDate date = LocalDate.now();
        String sDate = date.toString();
        txtDate.setText(sDate);
    }

    public void loadCustomerIds() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("select customer_id from customer");
            while (rst.next()) {
                customerIdList.add(rst.getString("customer_id"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        cmdCustomerID.setItems(customerIdList);
    }

    public void loadItemIds() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("select item_id from item");
            while (rst.next()) {
                itemIdList.add(rst.getString("item_id"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        cmdItemID.setItems(itemIdList);
    }

    public void updateQtyOnHand(){
        String item_id = cmdItemID.getValue();
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        int qty = Integer.parseInt(txtQty.getText());
        qtyOnHand = qtyOnHand - qty;
        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            st.executeUpdate("update item set available_quantity='" + qtyOnHand + "' where item_id='" + item_id + "'");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void txtItemUnitPriceOnAction(ActionEvent event) {
        txtQty.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setOrderDate();
        loadCustomerIds();
        loadItemIds();
    }
}

