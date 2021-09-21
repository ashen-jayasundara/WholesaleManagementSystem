package edu.fct.wholesalemanagemetsystem.model;

public class Purchase {
    String purchase_id;
    String date;
    String total_cost;

    public Purchase() {
    }

    public Purchase(String purchase_id, String date, String total_cost) {
        this.purchase_id = purchase_id;
        this.date = date;
        this.total_cost = total_cost;
    }

    public String getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(String purchase_id) {
        this.purchase_id = purchase_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(String total_cost) {
        this.total_cost = total_cost;
    }
}
