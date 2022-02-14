package restaurantGUI;

import restaurantGUI.loginScreen.CreateAccount;
import restaurantGUI.loginScreen.LoginForm;
import restaurantModel.*;
import restaurantModel.Table;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import restaurantGUI.customerDashBoards.customerMenus.MenusTableView;
import restaurantReservationsXML.Order;
import restaurantReservationsXML.Orders;
import restaurantReservationsXML.Reservation;
import restaurantReservationsXML.Reservations;

import java.util.ArrayList;
import java.util.List;

public  class DashBoard {
   private Stage stage;
   private  Scene scene;


   private MyRestaurant myRestaurant;




   private  Customer customer = new Customer();
   private Waiter waiter = new Waiter();
   private Manager manager = new Manager();
   private Cook cook = new Cook();
  private Table table = new Table();



   private Reservations reservations= new Reservations();
   private Reservation reservation;



  private CreateAccount createAccount;
   private DashBoard appetizerMenu;
   private DashBoard customerDashboard2;
   private DashBoard customerDashboard1;
   private DashBoard managerDashboard;
   private DashBoard waiterDashboard;
   private DashBoard cookDashboard;
   private DashBoard customerViewOrder;
   private DashBoard statistics;
   private DashBoard customerCheckOut;

   private DashBoard mainDishMenu;
   private DashBoard dessertMenu;
   private LoginForm loginForm;
   private MenusTableView menusTableView;
   private List<Order> orderList = new ArrayList<>();
   private Orders orders = new Orders(orderList);
   private DashBoard checkOut;
   private TextArea orderText = new TextArea() ;





    public DashBoard(Stage stage) {
        this.stage = stage;
    }

    public void prepareScene(){


   }


    public DashBoard getAppetizerMenu() {
        return appetizerMenu;
    }

    public void setAppetizerMenu(DashBoard appetizerMenu) {
        this.appetizerMenu = appetizerMenu;
    }

    public DashBoard getCustomerDashboard2() {
        return customerDashboard2;
    }

    public void setCustomerDashboard2(DashBoard customerDashboard2) {
        this.customerDashboard2 = customerDashboard2;
    }

    public DashBoard getCustomerDashboard1() {
        return customerDashboard1;
    }

    public void setCustomerDashboard1(DashBoard customerDashboard1) {
        this.customerDashboard1 = customerDashboard1;
    }


    public DashBoard getManagerDashboard() {
        return managerDashboard;
    }

    public void setManagerDashboard(DashBoard managerDashboard) {
        this.managerDashboard = managerDashboard;
    }

    public DashBoard getWaiterDashboard() {
        return waiterDashboard;
    }

    public void setWaiterDashboard(DashBoard waiterDashboard) {
        this.waiterDashboard = waiterDashboard;
    }

    public DashBoard getCookDashboard() {
        return cookDashboard;
    }

    public void setCookDashboard(DashBoard cookDashboard) {
        this.cookDashboard = cookDashboard;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public DashBoard getMainDishMenu() {
        return mainDishMenu;
    }

    public void setMainDishMenu(DashBoard mainDishMenu) {
        this.mainDishMenu = mainDishMenu;
    }

    public LoginForm getLoginForm() {
        return loginForm;
    }

    public void setLoginForm(LoginForm loginForm) {
        this.loginForm = loginForm;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public DashBoard getDessertMenu() {
        return dessertMenu;
    }

    public void setDessertMenu(DashBoard dessertMenu) {
        this.dessertMenu = dessertMenu;
    }








    public Reservations getReservations() {
        return reservations;
    }

    public void setReservations(Reservations reservations) {
        this.reservations = reservations;
    }

    public MenusTableView getMenusTableView() {
        return menusTableView;
    }

    public DashBoard getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(DashBoard checkOut) {
        this.checkOut=checkOut;
    }

    public TextArea getOrderText() {
        return orderText;
    }

    public void setOrderText(TextArea orderText) {
        this.orderText = orderText;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setMenusTableView(MenusTableView menusTableView) {
        this.menusTableView = menusTableView;
    }

    public void setOrderDetails(String dishName,int quantity) {
        Order order = new Order(dishName,quantity);

        customerDashboard2.getOrders().getOrders().add(order);


    }

    public void setTextField(String string){
     checkOut.orderText.appendText(string);
    }
    public void setName(String name){

    }
    public void setTableNo(int tableNo){

    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }



    public MyRestaurant getMyRestaurant() {
        return myRestaurant;
    }

    public void setMyRestaurant(MyRestaurant myRestaurant) {
        this.myRestaurant = myRestaurant;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public DashBoard getCustomerViewOrder() {
        return customerViewOrder;
    }

    public void setCustomerViewOrder(DashBoard customerViewOrder) {
        this.customerViewOrder = customerViewOrder;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    public DashBoard getStatistics() {
        return statistics;
    }

    public void setStatistics(DashBoard statistics) {
        this.statistics = statistics;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public DashBoard getCustomerCheckOut() {
        return customerCheckOut;
    }

    public void setCustomerCheckOut(DashBoard customerCheckOut) {
        this.customerCheckOut = customerCheckOut;
    }

    public Cook getCook() {
        return cook;
    }

    public void setCook(Cook cook) {
        this.cook = cook;
    }

    public CreateAccount getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(CreateAccount createAccount) {
        this.createAccount = createAccount;
    }
}

