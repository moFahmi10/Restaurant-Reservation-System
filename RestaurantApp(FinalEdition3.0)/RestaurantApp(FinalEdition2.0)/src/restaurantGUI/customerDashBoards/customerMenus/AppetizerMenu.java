package restaurantGUI.customerDashBoards.customerMenus;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import restaurantModel.Appetizer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import restaurantGUI.DashBoard;
import restaurantReservationsXML.Order;

public class AppetizerMenu extends DashBoard {


    public AppetizerMenu(Stage stage){
        super(stage);
    }

    public void prepareScene() {
        BackgroundImage myBI = new BackgroundImage ( new Image ( "http://www.mannaliverpool.co.uk/wp-content/uploads/2013/06/201008079.jpg", 700, 600, false, true ),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT );


        Button submit = new Button ( "Submit" );
        submit.setStyle ( "-fx-font: 20 Impact" );
        submit.setMinSize ( 70,50 );


        TableView<MenusTableView> appetizerTable;

        TableColumn<MenusTableView, String> nameColumn;
        TableColumn<MenusTableView, Double> priceColumn;

        TableColumn<MenusTableView, TextField> spinnerColumn;

        TableColumn<MenusTableView, RadioButton> radioButtonTableColumn;

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
        appetizerTable = new TableView<> ();
        appetizerTable.setItems ( getProducts () );
        appetizerTable.getColumns ().addAll ( nameColumn, priceColumn, spinnerColumn, radioButtonTableColumn );


        VBox vBox = new VBox ( 10 );

        vBox.getChildren ().addAll ( appetizerTable,submit );
        vBox.setAlignment ( Pos.CENTER );
        vBox.setPadding ( new Insets ( 10,10,10,10 ) );
        vBox.setBackground ( new Background ( myBI ) );
        setScene(new Scene ( vBox ,700,600));



        submit.setOnAction ( e-> {
            for (MenusTableView item :appetizerTable.getItems()) {
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


        for (Appetizer appetizer : getMyRestaurant().getAppetizerList() )
            menusTableViews.add(new MenusTableView(appetizer.getName(), appetizer.getPrice()));


        return menusTableViews;
    }



}
