package restaurantGUI.managerDashboards;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import restaurantGUI.DashBoard;

import javax.xml.bind.JAXBException;
import java.text.DateFormat;
import java.time.LocalDate;

public class ManagerDashboard extends DashBoard {


    public ManagerDashboard(Stage stage) {
        super(stage);

    }

    public void prepareScene() {
        BackgroundImage myBI = new BackgroundImage ( new Image ( "http://www.mannaliverpool.co.uk/wp-content/uploads/2013/06/201008079.jpg", 700, 600, false, true ),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT );


        Button selectButton = new Button ( "Select" );
        DatePicker d=new DatePicker (  );
        d.setEditable ( false );
        Label selectDate = new Label ( "Select Date" );
        HBox hBox=new HBox ( 20 );
        hBox.setAlignment ( Pos.CENTER );
        hBox.getChildren ().addAll ( selectDate,d,selectButton );
        Label lbl1 = new Label("Today's Scheduled Reservations");
        Button logOut = new Button ( "LogOut" );
        logOut.setMinSize ( 70, 70 );
        logOut.setOnAction ( e -> {
            getLoginForm().prepareScene();
            getStage ().setScene ( getLoginForm ().getScene () );
        } );
        Label nameLabel = new Label ( "Enter Customer Name" );
        TextField nameField = new TextField (  );
        Button enter = new Button ( "Enter" );
        HBox hBox3 = new HBox ( 20 );
        hBox3.getChildren ().addAll ( nameLabel,nameField,enter );
        Button reserve = new Button("Reserve a table");
        reserve.setMinSize ( 70, 70 );
        Button statistics = new Button ( "Statistics" );
        statistics.setMinSize ( 70, 70 );
        statistics.setOnAction ( e->{
            getStatistics().prepareScene ();
            getStage ().setScene ( getStatistics ().getScene () );
        });
        TableView<ManagerTable> manager;
        TableColumn<ManagerTable, DateFormat> dateColumn;
        TableColumn<ManagerTable, Integer> numberColumn;
        TableColumn<ManagerTable, String> nameColumn;
        TableColumn<ManagerTable,Double> priceColumn;
        TableColumn<ManagerTable,Button> viewButton;


        {
            dateColumn = new TableColumn<ManagerTable, DateFormat> ( "Time" );
            dateColumn.setMinWidth ( 150 );
            dateColumn.setCellValueFactory ( new PropertyValueFactory<> ( "time" ) );
            numberColumn = new TableColumn<ManagerTable, Integer> ( "Table Number" );
            numberColumn.setMinWidth ( 120 );
            numberColumn.setCellValueFactory ( new PropertyValueFactory<> ( "tableNumber" ) );
            nameColumn = new TableColumn<ManagerTable, String> ( "Customer Name" );
            nameColumn.setMinWidth ( 150 );
            nameColumn.setCellValueFactory ( new PropertyValueFactory<> ( "customerName" ) );
            priceColumn = new TableColumn<ManagerTable, Double> ( "Order Price" );
            priceColumn.setMinWidth ( 120 );
            priceColumn.setCellValueFactory ( new PropertyValueFactory<> ( "orderPrice" ) );
            viewButton = new TableColumn<ManagerTable,Button>("View");
            viewButton.setMinWidth ( 100 );
            viewButton.setCellValueFactory ( new PropertyValueFactory<> ( "ViewOrder" ) );

        }
        HBox hBox2 = new HBox ( 20 );
        hBox2.setAlignment ( Pos.CENTER );
        hBox2.getChildren ().addAll ( reserve,logOut,statistics );

        manager = new TableView<> ();
        manager.getColumns ().addAll (dateColumn, numberColumn, nameColumn,priceColumn,viewButton );
        try {
            manager.setItems(getManager().managerReadReservations(LocalDate.now().toString()));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        VBox vBox = new VBox ( 10 );
        vBox.setPadding ( new Insets ( 10,10,10,10 ) );

        vBox.getChildren ().addAll ( hBox,lbl1,manager, hBox2 );
        reserve.setOnAction ( e->{
            reserve.setDisable ( true );
            vBox.getChildren ().addAll ( hBox3 );

        } );
      enter.setOnAction ( e->{
          getCustomerDashboard1().getCustomer().setName(nameField.getText());

          getCustomerDashboard1().prepareScene();
          if(isValidName ( nameField.getText () ))
          {
              getStage ().setScene ( getCustomerDashboard1 ().getScene () );
          }
          else {
              Alert alert = new Alert ( Alert.AlertType.ERROR );
              alert.setContentText ( "Invalid Input" );
              alert.setHeaderText ( "" );
              alert.setTitle ( "Error" );
              alert.show ();
          }
      } );
        vBox.setBackground ( new Background ( myBI ) );
        vBox.setAlignment ( Pos.CENTER );
        vBox.setStyle ( "-fx-font: 18 Impact" );
        setScene ( new Scene ( vBox, 700, 600 ) );

     selectButton.setOnAction(e->{
         try {
             manager.setItems(getManager().managerReadReservations(d.getValue().toString()));
         } catch (JAXBException ex) {
             ex.printStackTrace();
         }
         lbl1.setText(d.getValue().getDayOfWeek().toString().toLowerCase()+" "+d.getValue().toString()+" Scheduled Reservations");
         vBox.getChildren().setAll(hBox,lbl1,manager,hBox2);
     });


    }

    public boolean isValidName(String s){
        String regex="[A-Za-z\\s]+";
        return s.matches(regex);
    }
}
