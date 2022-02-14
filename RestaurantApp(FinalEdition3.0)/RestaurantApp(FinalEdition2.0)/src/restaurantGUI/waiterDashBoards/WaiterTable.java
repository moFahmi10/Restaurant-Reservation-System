package restaurantGUI.waiterDashBoards;

import restaurantDataXML.Restaurant;

public class WaiterTable {
    Restaurant restaurant;
    private int tableNumber;
    private String customerName;
    private String time;
    public WaiterTable(int tableNumber, String customerName, String time){
        this.tableNumber=tableNumber;
        this.customerName= customerName;
        this.time = time;


    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
