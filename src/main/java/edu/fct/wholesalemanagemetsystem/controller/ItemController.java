package edu.fct.wholesalemanagemetsystem.controller;

import com.jfoenix.controls.JFXButton;
import edu.fct.wholesalemanagemetsystem.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemController implements Initializable {

    @FXML
    private JFXButton btnNewItem;

    @FXML
    private JFXButton btnSearchItem;

    @FXML
    private AnchorPane itemPane;

//    @FXML
//    private TableView<ItemTableModel> tableItemDetails;
//
//    @FXML
//    private TableColumn<ItemTableModel, String> col1ItemID;
//
//    @FXML
//    private TableColumn<ItemTableModel, String> col2ItemName;
//
//    @FXML
//    private TableColumn<ItemTableModel, String> col3Brand;
//
//    @FXML
//    private TableColumn<ItemTableModel, String> col4Qty;
//
//    @FXML
//    private TableColumn<ItemTableModel, String> col5UnitPrize;
//
//    ObservableList<ItemTableModel> itemdatalist = FXCollections.observableArrayList();

    @FXML
    void btnLoadNewItem(ActionEvent event) throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("newItem.fxml"));
        itemPane.getChildren().add(newLoadedPane);
        btnNewItem.setDefaultButton(true);
        btnSearchItem.setDefaultButton(false);
    }

    @FXML
    void btnLordSearchItem(ActionEvent event) throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(Main.class.getResource("searchItem.fxml"));
        itemPane.getChildren().add(newLoadedPane);
        btnNewItem.setDefaultButton(false);
        btnSearchItem.setDefaultButton(true);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Pane newLoadedPane = null;
        try {
            newLoadedPane = FXMLLoader.load(Main.class.getResource("viewItem.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        itemPane.getChildren().add(newLoadedPane);
    }
//    public void loadItem(){
//        try {
//            Connection con = DBConnection.getInstance().getConnection();
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery("select * from item");
//            while (rs.next()){
//                itemdatalist.add(new ItemTableModel(rs.getString("item_id"),rs.getString("item_name"),rs.getString("brand"),rs.getString("available_quantity"), rs.getString("unit_prize")));
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        col1ItemID.setCellValueFactory(new PropertyValueFactory<>("itemId"));
//        col2ItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
//        col3Brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
//        col4Qty.setCellValueFactory(new PropertyValueFactory<>("availableQty"));
//        col5UnitPrize.setCellValueFactory(new PropertyValueFactory<>("unitPrize"));
//        tableItemDetails.setItems(itemdatalist);
//    }
}