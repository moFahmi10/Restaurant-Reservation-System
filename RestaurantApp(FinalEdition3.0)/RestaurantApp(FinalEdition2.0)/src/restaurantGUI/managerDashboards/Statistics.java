package restaurantGUI.managerDashboards;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import restaurantGUI.DashBoard;
import restaurantReservationsXML.Reservations;

import javax.xml.bind.JAXBException;

public class Statistics extends DashBoard {

    public Statistics(Stage stage) {
        super ( stage );
    }

    public void prepareScene() {
        BackgroundImage myBI = new BackgroundImage ( new Image ( "https://www.printawallpaper.com/upload/designs/White_Crumpled_Paper_dn.jpg", 700, 600, false, true ),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT );

        try {
           getReservations().setReservations(Reservations.read());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        Label moneyToday = new Label ( "Total Earned Money Today" );
        Label moneyQuantityT = new Label ( String.valueOf(getManager().managerGetTotalMoneyDay(getReservations().getReservations())));

        Label moneyMonth = new Label ( "Total Earned Money this Month" );
        Label moneyQuantityM = new Label (  String.valueOf(getManager().managerGetTotalMoneyMonth(getReservations().getReservations())));

        Label avgMoney = new Label ( "Average Money Earned / day" );
        Label avgMoneyQuantity = new Label (  String.valueOf((getManager().managerGetTotalMoneyMonth(getReservations().getReservations()))/30));

        Label tableToday = new Label ( "Total Tables Reserved Today" );
        Label tableQuantityT = new Label ( String.valueOf(getManager().managerGetTableDay(getReservations().getReservations())) );

        Label tableMonth = new Label ( "Total Tables Reserved this Month" );
        Label tableQuantityM = new Label (  String.valueOf(getManager().managerGetTableMonth(getReservations().getReservations())));

        Label avgTable = new Label ( "Average Tables Reserved / Day" );
        Label avgTableQuantity = new Label (  String.valueOf(getManager().managerGetTableMonth(getReservations().getReservations())/30));

        VBox vBox2 = new VBox ( 60 );
        vBox2.getChildren ().addAll ( tableToday,tableQuantityT,tableMonth,tableQuantityM,avgTable,avgTableQuantity );


        Button back = new Button ( "Back" );
        back.setMinSize ( 70, 70 );
        back.setOnAction ( e-> {
            getStage ().setScene ( getManagerDashboard ().getScene () );
        });
        VBox vBox = new VBox ( 60 );
        vBox.getChildren ().addAll ( moneyToday,moneyQuantityT,moneyMonth,moneyQuantityM,avgMoney,avgMoneyQuantity );

        VBox vBox1 = new VBox (  );
        vBox1.getChildren ().add ( back );
        vBox1.setAlignment ( Pos.BOTTOM_CENTER );

        HBox hBox = new HBox ( 40 );
        hBox.getChildren ().addAll ( vBox,vBox1,vBox2 );
        hBox.setAlignment ( Pos.CENTER );
        hBox.setPadding ( new Insets ( 10,10,20,10 ) );
        hBox.setStyle ( "-fx-font: 18 Impact" );
        hBox.setBackground ( new Background ( myBI ) );
        setScene ( new Scene ( hBox, 700, 600 ) );
    }
}
