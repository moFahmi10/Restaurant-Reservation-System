package restaurantGUI.customerDashBoards;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.converter.LocalTimeStringConverter;
import restaurantGUI.DashBoard;
import restaurantReservationsXML.Reservation;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CustomerReserveTable extends DashBoard {
    ComboBox<String> comboBox;


    public CustomerReserveTable(Stage stage) {
        super(stage);
    }

    public void prepareScene(){
        BackgroundImage myBI = new BackgroundImage ( new Image ( "https://cdn.hipwallpaper.com/i/54/87/xgAU37.jpg", 700, 600, false, true ),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT );


        Alert alert = new Alert(Alert.AlertType.ERROR);
        Label Hello = new Label ( "Hello "+ getCustomer().getName() );
        Hello.setStyle ( "-fx-font: 20 Impact" );
        String newLine=System.getProperty ( "line.separator" );
        Label Welcome = new Label("                                    Welcome to our Restaurant"+newLine+
                "Your Feedback is always important for us to become better");
        Welcome.setStyle ( "-fx-font: 20 Impact" );
        Button reserve = new Button ( "Reserve a table" );
        reserve.setStyle ( "-fx-font: 20 Impact" );
        Button viewOrder = new Button ( "View Reservations" );
        viewOrder.setStyle ( "-fx-font: 20 Impact" );
        Button logOut = new Button ( "LogOut" );
        logOut.setMinSize ( 80,60 );
        logOut.setStyle ( "-fx-font: 20 Impact" );
        logOut.setOnAction ( e-> {
            getLoginForm().prepareScene();
            getStage().setScene(getLoginForm().getScene());
        } );
        VBox vBox1 = new VBox (  );
        vBox1.setAlignment ( Pos.TOP_CENTER );
        vBox1.setPadding ( new Insets (10,10,0, 10) );
        vBox1.getChildren ().addAll ( logOut );

        comboBox= new ComboBox<> (  );
        comboBox.getItems ().addAll ("Non-Smoking","Smoking"  );
        comboBox.setStyle ( "-fx-font: 18 Impact" );

        comboBox.setValue("Non-Smoking");
        Label tableLabel = new Label ( "How many seats do you want ? " );
        tableLabel.setStyle ( "-fx-font: 20 Impact" );
        Spinner<Integer> spinner=new Spinner<> ( );
        SpinnerValueFactory<Integer>valueFactory= new SpinnerValueFactory.IntegerSpinnerValueFactory ( 1,15 );
        spinner.setValueFactory ( valueFactory );
        spinner.setStyle ( "-fx-font: 18 Impact" );
        DatePicker d=new DatePicker (  );
        d.setEditable ( false );
        d.setValue ( LocalDate.now () );
        d.setStyle ( "-fx-font: 18 Impact" );

        Label selectTime = new Label ( "Select Time" );
        selectTime.setStyle ( "-fx-font: 18 Impact" );
        Label hourLabel = new Label ( "Hours:" );
        hourLabel.setStyle ( "-fx-font: 18 Impact" );
        Label minuteLabel = new Label ( "Minutes:" );
        minuteLabel.setStyle ( "-fx-font: 18 Impact" );
        Spinner<Integer>hour = new Spinner<> (  );
        SpinnerValueFactory<Integer>hourFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory (1,23 );
        hour.setValueFactory ( hourFactory1 );
        hour.setEditable ( false );
        hour.setMaxWidth ( 100 );
        hour.setStyle ( "-fx-font: 18 Impact" );
        Spinner<Integer>minute = new Spinner<> (  );
        DecimalFormat dm = new DecimalFormat("00");
        SpinnerValueFactory<Integer>minuteFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory ( 0,59 );

        minute.setValueFactory ( minuteFactory );
        minute.setEditable ( false );
        minute.setMaxWidth ( 100 );
        minute.setStyle ( "-fx-font: 18 Impact" );


        HBox hBox1 = new HBox ( 10 );
        hBox1.getChildren ().addAll (hourLabel ,hour,minuteLabel,minute );
        hBox1.setAlignment ( Pos.CENTER );

        Button submit= new Button ( "Submit" );
        submit.setStyle ( "-fx-font: 20 Impact" );
        submit.setMinSize ( 70,50 );
        HBox hBox = new HBox ( 20 );
        hBox.setAlignment ( Pos.CENTER );
        hBox.getChildren ().addAll ( reserve,viewOrder );


        VBox vBox=new VBox ( 15 );
        vBox.getChildren ().addAll ( vBox1,Hello,Welcome,hBox );
        vBox.setAlignment ( Pos.CENTER );
        reserve.setOnAction ( e->{
            reserve.setDisable ( true );
            vBox.getChildren ().add(comboBox);
            vBox.getChildren ().add(tableLabel);
            vBox.getChildren ().add ( spinner );
            vBox.getChildren ().add ( d );
            vBox.getChildren ().add ( selectTime );
            vBox.getChildren ().add ( hBox1 );
            vBox.getChildren ().add(submit);
            vBox.setAlignment ( Pos.CENTER );
        } );
       submit.setOnAction ( e->{
           int x = LocalTime.of ( (hour.getValue ()-1),minute.getValue () ).compareTo ( LocalTime.now () );

           boolean  smoking;
           int numOfTable;
           int z=  d.getValue ().compareTo ( LocalDate.now () );
           getCustomerDashboard2().prepareScene ();
           getMainDishMenu().prepareScene ();
           getAppetizerMenu().prepareScene ();
           getDessertMenu().prepareScene();
           getCustomerDashboard2().getOrders().getOrders().clear();

           if(comboBox.getValue().equals("Smoking"))
               numOfTable =getTable().getNumOfTable(getMyRestaurant().getSmokingTableList(), spinner.getValue());
               else
               numOfTable=  getTable().getNumOfTable(getMyRestaurant().getNonSmokingTableList(),spinner.getValue());

               if (z<0) {

                   alert.setTitle ( "Error" );
                   alert.setHeaderText ( "" );
                   alert.setContentText ( "Invalid Date" );
                   alert.showAndWait ();
               }
               else if(z==0&&x<1){
                   alert.setTitle ( "Error" );
                   alert.setHeaderText ( "" );
                   alert.setContentText ( "Invalid Time \n"+"You Can't Reserve A Table Before an Hour From Now." );
                   alert.showAndWait ();
               }

          else if (numOfTable==0){
               alert.setTitle ( "We are sorry" );
               alert.setHeaderText ( "" );
               alert.setContentText ( "We don't have such a table" );
               alert.showAndWait ();
           }

           else {

                   if (getTable().isAvailable ( numOfTable, d.getValue().toString()) && z >= 0) {

                       getCheckOut ().getReservation ().setTableNo ( numOfTable );
                       getCheckOut().getReservation().setName(getCustomer().getName());
                       getStage ().setScene ( getCustomerDashboard2 ().getScene () );
                   } else {
                       alert.setTitle ( "We are sorry" );
                       alert.setHeaderText ( "" );
                       alert.setContentText ( "Table is reserved" );
                       alert.showAndWait ();

                   }
               }
             getCheckOut().getReservation().setDate(d.getValue().toString());
           getCheckOut().getReservation().setTime(dm.format(hour.getValue())+":"+dm.format(minute.getValue()));

               } );
       viewOrder.setOnAction( e-> {
           getCustomerViewOrder().prepareScene();
           getStage().setScene(getCustomerViewOrder().getScene());

       } );

        vBox.setBackground ( new Background ( myBI ) );

        setScene(new Scene(vBox, 700,600)) ;


    }



}
