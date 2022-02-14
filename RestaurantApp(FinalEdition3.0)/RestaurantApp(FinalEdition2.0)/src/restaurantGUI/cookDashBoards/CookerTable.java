package restaurantGUI.cookDashBoards;

import restaurantReservationsXML.Order;

import java.util.List;

public class CookerTable {

    private int tableNumber;

    private String orders ="";

    private String time;
    public CookerTable(int tableNumber, List<Order> orders, String time,String comment){
        this.tableNumber=tableNumber;

        for (Order order:orders) {
       //     System.out.println(order.getDishName());
          this.orders= this.orders.concat(order.getDishName()+"*"+order.getQuantity()+"\n");

        }
        if(!comment.isEmpty())
       this.orders= this.orders.concat("\ncomments:\n"+comment);

        this.time = time;


    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }


    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
