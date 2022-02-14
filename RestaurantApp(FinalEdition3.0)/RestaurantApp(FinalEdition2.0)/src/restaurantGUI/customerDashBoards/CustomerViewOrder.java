package restaurantGUI.customerDashBoards;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import restaurantGUI.DashBoard;
import restaurantModel.Customer;
import restaurantReservationsXML.Order;
import restaurantReservationsXML.Reservation;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

public class CustomerViewOrder extends DashBoard {
    public CustomerViewOrder(Stage stage) {
        super ( stage );
    }
    private Customer customer = new Customer();
    private Reservation reservation = new Reservation();
    List<Reservation> reservationsList = new ArrayList();





    public void prepareScene(){
        BackgroundImage myBI = new BackgroundImage ( new Image ( "http://www.mannaliverpool.co.uk/wp-content/uploads/2013/06/201008079.jpg", 700, 600, false, true ),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT );

        Label orderDetails=new Label ( "Reservations Made:" );
        orderDetails.setStyle ( "-fx-font: 20 Impact" );
        getOrderText().setMinSize(50,150);
        getOrderText ().setStyle ( "-fx-font: 18 Impact" );
        getOrderText().setEditable(false);

        Button cancelOrder = new Button ( "Cancel Order" );
        cancelOrder.setStyle ( "-fx-font: 20 Impact" );
        cancelOrder.setMinSize ( 70,50 );
        Button back = new Button ("Back");
        back.setStyle ( "-fx-font: 20 Impact" );
        back.setMinSize ( 70,50 );
        back.setOnAction (e->{
            getOrderText().setText("");
            getStage().setScene(getCustomerDashboard1 ().getScene());

        } );



        HBox hbox = new HBox ( 20 );
        hbox.setPadding ( new Insets ( 10,10,10,10 ) );
        hbox.getChildren ().addAll ( back );
        VBox vBox = new VBox ( 20 );
        vBox.getChildren ().addAll ( orderDetails,getOrderText() ,hbox);

        vBox.setBackground ( new Background ( myBI ) );
        setScene(new Scene (vBox, 700,600)) ;
        int i = 1;
        try {
            reservationsList= getCustomer().customerReadReservations(getLoginForm().getCustomer().getName());
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        for (Reservation reservation:reservationsList) {
            getOrderText().appendText("Order number"+(i++)+":\n\n");
            getOrderText().appendText("Name: "+reservation.getName()+"\n"+"Table number: "+reservation.getTableNo()+"\n"+"Date: "+reservation.getDate()+"\n");
            for (Order order:reservation.getOrders().getOrders()) {
                getOrderText().appendText(order.getDishName() + "*" + order.getQuantity() + "\n");
            }
            getOrderText().appendText("Total price: "+reservation.getPrice());
            getOrderText().appendText("\n\n\n");
        }

    }



    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}

