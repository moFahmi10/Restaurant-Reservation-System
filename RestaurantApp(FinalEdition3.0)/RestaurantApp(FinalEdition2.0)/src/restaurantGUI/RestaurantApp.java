package restaurantGUI;

import restaurantDataXML.Restaurant;
import restaurantGUI.cookDashBoards.CookersDashboard;
import restaurantGUI.loginScreen.CreateAccount;
import restaurantGUI.loginScreen.LoginForm;
import restaurantGUI.customerDashBoards.CustomerViewOrder;
import restaurantGUI.managerDashboards.ManagerDashboard;
import restaurantGUI.managerDashboards.Statistics;
import restaurantGUI.waiterDashBoards.WaiterDashboard;
import restaurantModel.*;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import restaurantGUI.customerDashBoards.CustomerCheckOut;
import restaurantGUI.customerDashBoards.CustomerReserveDish;
import restaurantGUI.customerDashBoards.CustomerReserveTable;
import restaurantGUI.customerDashBoards.customerMenus.AppetizerMenu;
import restaurantGUI.customerDashBoards.customerMenus.DessertMenu;
import restaurantGUI.customerDashBoards.customerMenus.MainDishMenu;


import java.util.Optional;

import static javafx.application.Platform.exit;

public class RestaurantApp extends Application {
    public static void main(String[] args) {
        launch ( args );
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setOnCloseRequest ( e -> {
            e.consume ();
            Alert alert = new Alert ( Alert.AlertType.CONFIRMATION );
            alert.setTitle ( "" );
            alert.setHeaderText ( "" );
            alert.setContentText ( "Are You Sure You Want To Exit? " );
            Optional<ButtonType> result = alert.showAndWait ();
            if (result.isPresent () && result.get () == ButtonType.OK)
                exit ();
        } );
        //***************************************************************************************************************
        // TRANSFER DATA FROM FILE TO CLASSES
        Restaurant restaurant=  MyRestaurant.loadRestaurantData();
        //***************************************************************************************************************
        primaryStage.setTitle ( "Restaurant Application" );

        LoginForm loginForm = new LoginForm ( primaryStage );
        CreateAccount createAccount = new CreateAccount ( primaryStage );

        DashBoard customerDashboard1 = new CustomerReserveTable( primaryStage );
        DashBoard customerDashboard2 = new CustomerReserveDish( primaryStage );
        DashBoard managerDashboard = new ManagerDashboard( primaryStage );
        DashBoard waiterDashboard = new WaiterDashboard( primaryStage );
        DashBoard cookersDashboard = new CookersDashboard( primaryStage);
        DashBoard mainDishMenu = new MainDishMenu( primaryStage );
        DashBoard appetizerMenu = new AppetizerMenu( primaryStage );
        DashBoard dessertMenu = new DessertMenu(primaryStage);
        DashBoard customerCheckOut = new CustomerCheckOut( primaryStage );
        DashBoard customerViewOrder = new CustomerViewOrder(primaryStage);
        DashBoard statistics = new Statistics( primaryStage );

        MyRestaurant myRestaurant = new MyRestaurant(restaurant);






        loginForm.setCustomerDashboard1(customerDashboard1);
        customerDashboard1.setLoginForm( loginForm );

        loginForm.setManagerDashboard(managerDashboard);
        managerDashboard.setLoginForm(loginForm);

        loginForm.setWaiterDashboard(waiterDashboard);
        waiterDashboard.setLoginForm(loginForm);

        loginForm.setCookDashboard(cookersDashboard);
        cookersDashboard.setLoginForm(loginForm);

        customerDashboard1.setCustomerDashboard2 ( customerDashboard2 );
        customerDashboard2.setCustomerDashboard1 (customerDashboard1);

        customerDashboard2.setMainDishMenu(mainDishMenu);
        mainDishMenu.setCustomerDashboard2 ( customerDashboard2 );

        customerDashboard2.setAppetizerMenu( appetizerMenu );
        appetizerMenu.setCustomerDashboard2 ( customerDashboard2 );

        customerDashboard2.setDessertMenu(dessertMenu);
        dessertMenu.setCustomerDashboard2(customerDashboard2);

        customerCheckOut.setLoginForm ( loginForm );
        customerCheckOut.setCustomerDashboard1 ( customerDashboard1 );
        loginForm.setCreateAccount(createAccount);
        createAccount.setLoginForm(loginForm);

        customerViewOrder.setCustomerDashboard1(customerDashboard1);
        customerDashboard1.setCustomerViewOrder(customerViewOrder);
        customerViewOrder.setLoginForm(loginForm);

        managerDashboard.setCustomerDashboard1 ( customerDashboard1 );
        managerDashboard.setStatistics ( statistics );
        statistics.setManagerDashboard ( managerDashboard );


        customerDashboard2.setCheckOut(customerCheckOut);
        customerCheckOut.setCustomerDashboard2 ( customerDashboard2 );
        customerDashboard1.setCheckOut(customerCheckOut);
        customerCheckOut.setCustomerDashboard1(customerDashboard1);
        customerDashboard1.setAppetizerMenu(appetizerMenu);
        customerDashboard1.setMainDishMenu(mainDishMenu);
        customerDashboard1.setDessertMenu(dessertMenu);




        customerViewOrder.prepareScene ();
        loginForm.setMyRestaurant(myRestaurant);
        customerDashboard1.setMyRestaurant(myRestaurant);
        customerDashboard2.setMyRestaurant(myRestaurant);
        appetizerMenu.setMyRestaurant(myRestaurant);
      mainDishMenu.setMyRestaurant(myRestaurant);
      dessertMenu.setMyRestaurant(myRestaurant);





        //*******************************
        customerDashboard1.setCheckOut(customerCheckOut);
        loginForm.setCheckOut(customerCheckOut);



        loginForm.prepareScene ();
        createAccount.prepareScene ();
        customerDashboard1.prepareScene ();
        //customerDashboard2.prepareScene ();




       // cookersDashboard.prepareScene();
      //  mainDishMenu.prepareScene ();
       // appetizerMenu.prepareScene ();
     //   dessertMenu.prepareScene();
       // customerCheckOut.prepareScene();

        primaryStage.setScene ( loginForm.getScene () );
        primaryStage.show ();

    }


}