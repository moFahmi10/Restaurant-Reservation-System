package restaurantGUI.cookDashBoards;

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

public class CookersDashboard extends DashBoard {

    public CookersDashboard(Stage stage) {
        super(stage);

    }

    public void prepareScene(){
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
        Button logOut = new Button ( "LogOut" );
        logOut.setStyle ( "-fx-font: 18 Impact" );
        logOut.setMinSize ( 70, 70 );
        logOut.setOnAction ( e -> {
            getLoginForm().prepareScene();
            getStage ().setScene ( getLoginForm ().getScene () );
        } );


        TableView<CookerTable> cooker;
        TableColumn<CookerTable, Integer> numberColumn;
        TableColumn<CookerTable, String> orderColumn;
        TableColumn<CookerTable, DateFormat> dateColumn;

        {
            numberColumn = new TableColumn<CookerTable, Integer> ( "Table Number" );
            numberColumn.setMinWidth ( 130 );
            numberColumn.setCellValueFactory ( new PropertyValueFactory<> ( "tableNumber" ) );
            numberColumn.setStyle ( "-fx-font: 18 Impact" );

            dateColumn = new TableColumn<CookerTable, DateFormat> ( "Time" );
            dateColumn.setMinWidth ( 150 );
            dateColumn.setCellValueFactory ( new PropertyValueFactory<> ( "time" ) );
            dateColumn.setStyle ( "-fx-font: 18 Impact" );

            orderColumn = new TableColumn<CookerTable, String> ( "Customer Order" );
            orderColumn.setMinWidth ( 380 );
            orderColumn.setCellValueFactory ( new PropertyValueFactory<> ( "orders" ) );
            orderColumn.setStyle ( "-fx-font: 18 Impact" );

        }

        cooker = new TableView<> ();
        cooker.getColumns ().addAll ( numberColumn,  dateColumn,orderColumn );
        try {
            cooker.setItems(getCook().cookReadReservations(LocalDate.now().toString()));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        VBox vBox = new VBox ( 10 );
        vBox.getChildren ().addAll ( hBox,cooker, logOut );
        vBox.setPadding ( new Insets ( 10,10,10,10 ) );
        vBox.setAlignment ( Pos.CENTER );
        vBox.setBackground ( new Background ( myBI ) );
        vBox.setStyle ( "-fx-font: 18 Impact" );

        setScene(new Scene(vBox, 700,600));
        selectButton.setOnAction(e->{
            try {
                cooker.setItems(getCook().cookReadReservations(d.getValue().toString()));
            } catch (JAXBException ex) {
                ex.printStackTrace();
            }
        });


    }


}
