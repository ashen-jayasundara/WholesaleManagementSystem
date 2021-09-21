package edu.fct.wholesalemanagemetsystem.model;

public class Item {
    String itemId;
    String itemName;
    String brand;
    String availableQty;
    String unitPrize;

    public Item() {
    }

    public Item(String itemId, String itemName, String brand, String availableQty, String unitPrize) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.brand = brand;
        this.availableQty = availableQty;
        this.unitPrize = unitPrize;
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

    public void setUnitPrize(String unitPrize) {
        this.unitPrize = unitPrize;
    }
}
