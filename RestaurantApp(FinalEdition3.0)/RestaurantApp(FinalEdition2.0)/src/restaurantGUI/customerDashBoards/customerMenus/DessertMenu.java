package restaurantGUI.customerDashBoards.customerMenus;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import restaurantModel.Dessert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import restaurantGUI.DashBoard;
import restaurantReservationsXML.Order;

public class DessertMenu extends DashBoard {


    public DessertMenu(Stage stage) {
        super(stage);
    }

    public void prepareScene() {
        BackgroundImage myBI = new BackgroundImage ( new Image ( "http://www.mannaliverpool.co.uk/wp-content/uploads/2013/06/201008079.jpg", 700, 600, false, true ),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT );


        // Button back = new Button ( "Back" );
       Button submit = new Button ( "Submit" );
        submit.setStyle ( "-fx-font: 20 Impact" );
       submit.setMinSize ( 70,50 );
        //Button edit = new Button ( "Edit" );
        //back.setOnAction ( e-> {
          //  getStage().setScene(getCustomerReserveDish().getScene());
        //});
        TableView<MenusTableView> dessertTable;

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
        dessertTable = new TableView<> ();
        dessertTable.setItems ( getProducts () );
        dessertTable.getColumns ().addAll ( nameColumn, priceColumn,  spinnerColumn, radioButtonTableColumn );


        VBox vBox = new VBox (10);
        vBox.getChildren ().addAll ( dessertTable,submit );
        vBox.setAlignment ( Pos.CENTER );
        vBox.setPadding ( new Insets ( 10,10,10,10 ) );
        vBox.setBackground ( new Background ( myBI ) );
        setScene(new Scene(vBox, 700,600)) ;


        submit.setOnAction ( e-> {
            for (MenusTableView item :dessertTable.getItems()) {
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


    public ObservableList<MenusTableView> getProducts () {

        ObservableList<MenusTableView> menusTableViews = FXCollections.observableArrayList ();

        for (Dessert dessert: getMyRestaurant().getDessertsList()) {

                menusTableViews.add(new MenusTableView(dessert.getName(), dessert.getPrice()));
        }

       /* menusTableViews.add ( new MenusTableView ( , 30 ,"Salad") );
        menusTableViews.add ( new MenusTableView ( "Chicken Salad", 40 ,"Salad") );
        menusTableViews.add ( new MenusTableView ( "Fries", 15.0,"Fries" ) );
        menusTableViews.add ( new MenusTableView ( "Cheesy Fries", 30.0,"Fries" ) );
        menusTableViews.add ( new MenusTableView ( "Cheese Sambousak", 20,"Sambousak" ) );
        menusTableViews.add ( new MenusTableView ( "Meat Sambousak", 30,"Sambousak" ) );
        menusTableViews.add ( new MenusTableView ( "Mombar", 30,"Mombar" ) );
*/
        return menusTableViews;
    }




}
