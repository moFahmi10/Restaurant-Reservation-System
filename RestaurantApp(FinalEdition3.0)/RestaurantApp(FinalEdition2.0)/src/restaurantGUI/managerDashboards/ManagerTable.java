package restaurantGUI.managerDashboards;

import javafx.scene.control.Button;
import restaurantGUI.customerDashBoards.CustomerViewOrder;
import restaurantModel.Manager;

import javax.xml.bind.JAXBException;

public class ManagerTable {
    CustomerViewOrder customerViewOrder;
    ManagerViewOrder managerViewOrder;
    private Manager manager= new Manager();
    private int tableNumber;
    private String customerName;
    private String time;
    private double orderPrice;
    private Button viewOrder;
    private String date;
    public ManagerTable(String time, int tableNumber, String customerName, double orderPrice,String date){
        this.tableNumber=tableNumber;
        this.customerName= customerName;
        this.time = time;
        this.orderPrice = orderPrice;
        this.date=date;
        this.viewOrder = new Button ( "View\nOrder" );
        viewOrder.setOnAction ( e-> {


            try {
                managerViewOrder.display (tableNumber, date);
            } catch (JAXBException ex) {
                ex.printStackTrace();
            }
            /*List<Order> orders = new ArrayList<>();
            try {
                orders = manager.managerViewOrder(tableNumber,date);
            } catch (JAXBException ex) {
                ex.printStackTrace();
            }
            System.out.println(orders.get(0).getDishName());
*/

        });

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

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Button getViewOrder() {
        return viewOrder;
    }

    public void setViewOrder(Button viewOrder) {
        this.viewOrder = viewOrder;
    }
}
