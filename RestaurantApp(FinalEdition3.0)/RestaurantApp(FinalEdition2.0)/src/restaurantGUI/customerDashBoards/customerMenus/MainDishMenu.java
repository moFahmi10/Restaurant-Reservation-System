package restaurantGUI.customerDashBoards.customerMenus;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import restaurantModel.MainDish;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import restaurantGUI.DashBoard;
import restaurantReservationsXML.Order;

public class MainDishMenu extends DashBoard {


        public MainDishMenu(Stage stage){
            super(stage);
    }



       public void prepareScene() {
           BackgroundImage myBI = new BackgroundImage ( new Image ( "http://www.mannaliverpool.co.uk/wp-content/uploads/2013/06/201008079.jpg", 700, 600, false, true ),
                   BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                   BackgroundSize.DEFAULT );


           //Button back = new Button ( "Back" );
           Button submit = new Button ( "Submit" );
           submit.setStyle ( "-fx-font: 20 Impact" );
           submit.setMinSize ( 70,50 );
           /*Button edit = new Button ( "Edit" );
           back.setOnAction ( e-> {
               getStage().setScene(getCustomerReserveDish().getScene());
           });*/
           TableView<MenusTableView> mainDishTable;

           TableColumn<MenusTableView, String> nameColumn;
           TableColumn<MenusTableView, Double> priceColumn;
           TableColumn<MenusTableView, TextField> spinnerColumn;

           TableColumn<MenusTableView, Button> radioButtonTableColumn;

           {
               nameColumn = new TableColumn<> ( "Name" );
               nameColumn.setMinWidth ( 220 );
               nameColumn.setCellValueFactory ( new PropertyValueFactory<>( "name" ) );
               nameColumn.setStyle ( "-fx-font: 18 Impact" );

               priceColumn = new TableColumn<> ( "Price" );
               priceColumn.setMinWidth ( 120 );
               priceColumn.setCellValueFactory ( new PropertyValueFactory<> ( "price" ) );
               priceColumn.setStyle ( "-fx-font: 18 Impact" );

               spinnerColumn = new TableColumn<> ( "Quantity" );
               spinnerColumn.setStyle ( "-fx-font: 18 Impact" );
               spinnerColumn.setMinWidth ( 170 );
               spinnerColumn.setCellValueFactory ( new PropertyValueFactory<> ( "spinner" ) );

               radioButtonTableColumn = new TableColumn<> ("Select");
               radioButtonTableColumn.setMinWidth ( 80 );
               radioButtonTableColumn.setCellValueFactory ( new PropertyValueFactory<> ( "radioButton" ) );
               radioButtonTableColumn.setStyle ( "-fx-font: 18 Impact" );

           }
           mainDishTable = new TableView<> ();
           mainDishTable.setItems ( getProducts () );
           mainDishTable.getColumns ().addAll ( nameColumn, priceColumn,  spinnerColumn, radioButtonTableColumn );


           VBox vBox = new VBox (10);
           vBox.getChildren ().addAll ( mainDishTable,submit );
           vBox.setAlignment ( Pos.CENTER );
           vBox.setBackground ( new Background ( myBI ) );
           vBox.setPadding ( new Insets ( 10,10,10,10 ) );
           setScene(new Scene(vBox, 700,600 )) ;

           submit.setOnAction ( e-> {
               for (MenusTableView item :mainDishTable.getItems()) {
                   if(item.getSpinner().getValue()!= 0)
                       setOrderDetails(item.getName(),item.getSpinner().getValue());
               }

               for (Order order2 :getCustomerDashboard2().getOrders().getOrders()) {


                   System.out.println(order2.getDishName() + order2.getQuantity());
               }
               System.out.println("\n\n");
               getStage().setScene(getCustomerDashboard2().getScene());

           });
       }


    public  ObservableList<MenusTableView> getProducts () {
        ObservableList<MenusTableView> menusTableViews = FXCollections.observableArrayList ();
        for (MainDish mainDish : getMyRestaurant().getMainDishList()) {
                menusTableViews.add(new MenusTableView(mainDish.getName(), mainDish.getPrice()));
        }

        /*
        menusTableViews.add ( new MenusTableView ( "Grilled Chicken", 80.0 ,"Chicken") );
        menusTableViews.add ( new MenusTableView ( " Fried Chicken", 80.0 ,"Chicken") );
        menusTableViews.add ( new MenusTableView ( "Margarita Pizza", 50.0,"Pizza" ) );
        menusTableViews.add ( new MenusTableView ( "Vegetables Pizza", 60.0,"Pizza" ) );
        menusTableViews.add ( new MenusTableView ( "Fish", 70,"SeaFood" ) );
        menusTableViews.add ( new MenusTableView ( "Sushi", 120,"SeaFood" ) );
        menusTableViews.add ( new MenusTableView ( "Alfredo", 65,"Pasta" ) );
        menusTableViews.add ( new MenusTableView ( "Lasagna", 65,"Pasta" ) );
        menusTableViews.add ( new MenusTableView ( "Fettuccine", 70,"Pasta" ) );
        menusTableViews.add ( new MenusTableView ( "Penne", 55,"Pasta" ) );
        menusTableViews.add ( new MenusTableView ( "Steak", 90,"Beef" ) );
        menusTableViews.add ( new MenusTableView ( "American Burger", 50,"Burger" ) );
        menusTableViews.add ( new MenusTableView ( "Chicken Burger", 50,"Burger" ) );
*/

        return menusTableViews;
    }



}
