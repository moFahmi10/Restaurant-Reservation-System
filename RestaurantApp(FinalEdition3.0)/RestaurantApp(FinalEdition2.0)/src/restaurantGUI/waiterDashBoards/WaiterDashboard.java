package restaurantGUI.waiterDashBoards;

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

public class WaiterDashboard extends DashBoard {
    /* Stage stage;
        Scene scene;
        LoginForm loginForm;
    */
    public WaiterDashboard(Stage stage) {
        super ( stage );

    }

    public void prepareScene() {
        BackgroundImage myBI = new BackgroundImage ( new Image ( "http://www.mannaliverpool.co.uk/wp-content/uploads/2013/06/201008079.jpg", 700, 600, false, true ),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT );


        Button selectButton = new Button ( "Select" );
        selectButton.setStyle ( "-fx-font: 18 Impact" );
        DatePicker d=new DatePicker (  );
        d.setEditable ( false );
        d.setStyle ( "-fx-font: 18 Impact" );
        Label selectDate = new Label ( "Select Date" );
        selectDate.setStyle ( "-fx-font: 18 Impact" );
        HBox hBox=new HBox ( 20 );
        hBox.setAlignment ( Pos.CENTER );
        hBox.getChildren ().addAll ( selectDate,d,selectButton );
        Label lbl1 = new Label("");
        lbl1.setStyle ( "-fx-font: 18 Impact" );
        Button logOut = new Button ( "LogOut" );
        logOut.setMinSize ( 70, 70 );
        logOut.setStyle ( "-fx-font: 18 Impact" );
        logOut.setOnAction ( e -> {
            getLoginForm().prepareScene();
            getStage ().setScene ( getLoginForm ().getScene () );
        } );

         Label lbl2 = new Label("No reservations for today");
        TableView<WaiterTable> waiter;
        TableColumn<WaiterTable, Integer> numberColumn;
        TableColumn<WaiterTable, String> nameColumn;
        TableColumn<WaiterTable, DateFormat> dateColumn;

        {
            numberColumn = new TableColumn<WaiterTable, Integer> ( "Table Number" );
            numberColumn.setMinWidth ( 150 );
            numberColumn.setCellValueFactory ( new PropertyValueFactory<> ( "tableNumber" ) );
            numberColumn.setStyle ( "-fx-font: 18 Impact" );
            nameColumn = new TableColumn<WaiterTable, String> ( "Customer Name" );
            nameColumn.setMinWidth ( 200 );
            nameColumn.setCellValueFactory ( new PropertyValueFactory<> ( "customerName" ) );
            nameColumn.setStyle ( "-fx-font: 18 Impact" );
            dateColumn = new TableColumn<WaiterTable, DateFormat> ( "Time" );
            dateColumn.setMinWidth ( 200 );
            dateColumn.setCellValueFactory ( new PropertyValueFactory<> ( "time" ) );
            dateColumn.setStyle ( "-fx-font: 18 Impact" );

        }

        waiter = new TableView<> ();
        waiter.getColumns ().addAll ( numberColumn, nameColumn, dateColumn );
        try {
            waiter.setItems(getWaiter().waiterReadReservations(LocalDate.now().toString()));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        lbl1.setText("Today's Scheduled Reservations");
        VBox vBox = new VBox ( 10 );
        vBox.setPadding ( new Insets ( 10,10,10,10 ) );
        try {
            if(getWaiter().waiterReadReservations(LocalDate.now().toString()).isEmpty())
                vBox.getChildren().addAll(hBox,lbl2,logOut);
           else vBox.getChildren ().addAll ( hBox,lbl1,waiter, logOut );
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        selectButton.setOnAction(e->{

            try {
                waiter.setItems(getWaiter().waiterReadReservations(d.getValue().toString()));
            } catch (JAXBException ex) {
                ex.printStackTrace();
            }
            lbl1.setText(d.getValue().getDayOfWeek().toString().toLowerCase()+" "+d.getValue().toString()+" Scheduled Reservations");
            vBox.getChildren().setAll(hBox,lbl1,waiter, logOut );


        });
        vBox.setBackground ( new Background ( myBI ) );
        vBox.setAlignment ( Pos.CENTER );
        vBox.setStyle ( "-fx-font: 18 Impact" );
        setScene ( new Scene ( vBox, 700, 600 ) );


    }

}