package edu.fct.wholesalemanagemetsystem.controller;

public class ItemTableModel {
    String itemId;
    String itemName;
    String brand;
    String availableQty;
    String unitPrize;


    public ItemTableModel(String itemId, String itemName, String brand, String availableQty, String uPrize) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.brand = brand;
        this.availableQty = availableQty;
        this.unitPrize = uPrize;
//        this.uPrize = String.valueOf(uPrize);
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(String availableQty) {
        this.availableQty = availableQty;
    }

    public String getUnitPrize() {
        return unitPrize;
    }

    public void setUnitPrize(String uPrize) {
        this.unitPrize = uPrize;
    }
}
