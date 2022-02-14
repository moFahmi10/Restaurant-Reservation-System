package restaurantGUI.customerDashBoards;

import restaurantModel.Customer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import restaurantGUI.DashBoard;
import restaurantReservationsXML.Reservation;

import javax.xml.bind.JAXBException;
import java.util.Optional;

public class CustomerCheckOut extends DashBoard {

    private Customer customer = new Customer();
    private Reservation reservation = new Reservation();
public CustomerCheckOut(Stage stage){
    super(stage);
}


public void prepareScene(){
    BackgroundImage myBI = new BackgroundImage ( new Image ( "http://www.mannaliverpool.co.uk/wp-content/uploads/2013/06/201008079.jpg", 700, 600, false, true ),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT );

    Label orderDetails=new Label ( "Order Details:" );
    orderDetails.setStyle ( "-fx-font: 20 Impact" );
    getOrderText().setMinSize(50,150);
    getOrderText ().setStyle ( "-fx-font: 18 Impact" );
    getOrderText().setEditable(false);
    Label comments = new Label ( "Comments" );
    comments.setStyle ( "-fx-font: 20 Impact" );
    TextArea commentArea = new TextArea (  );
    commentArea.setPromptText ( "Enter any comment on order here..." );
    commentArea.setStyle ( "-fx-font: 20 Impact ");
    commentArea.setMaxSize ( 580,80 );
    Button confirm = new Button ( "Confirm" );
    confirm.setStyle ( "-fx-font: 20 Impact" );
    confirm.setMinSize ( 70,50 );
    Button back = new Button ("Back");
    back.setStyle ( "-fx-font: 20 Impact" );
    back.setMinSize ( 70,50 );
    back.setOnAction (e->{
         getOrderText().setText("");
        getStage().setScene(getCustomerDashboard2().getScene());

    } );

    confirm.setOnAction ( e->{
        Alert alert = new Alert ( Alert.AlertType.CONFIRMATION );
        alert.setTitle ( "Confirmation" );
        alert.setHeaderText ( "" );
        alert.setContentText ( "Are You Sure You Want To Submit Your Order ?" );
        Optional<ButtonType> result = alert.showAndWait ();
        if (result.isPresent () && result.get () == ButtonType.OK){
            reservation.setComment(commentArea.getText());

            try {
                customer.customerAddReservation(reservation);
            } catch (JAXBException ex) {
                ex.printStackTrace();
            }
            reservation = new Reservation();
            getOrderText().setText("");


            Alert alert1 = new Alert ( Alert.AlertType.NONE );
            alert1.setTitle ( "" );
            alert1.setHeaderText ( "Your Order has been successfully submitted" );
            ButtonType buttonTypeLogOut= new ButtonType ( "LogOut" );
            ButtonType buttonTypeNewOrder= new ButtonType ( "New Order" );
            alert1.getButtonTypes ().setAll ( buttonTypeNewOrder,buttonTypeLogOut );
            Optional<ButtonType> result1 = alert1.showAndWait ();
            if (result1.get ()==buttonTypeLogOut) {
                getLoginForm().prepareScene();
                getStage().setScene(getLoginForm().getScene());
            }
            if (result1.get ()==buttonTypeNewOrder) {
                reservation.setName(getCustomerDashboard1().getCustomer().getName());
                getStage().setScene(getCustomerDashboard1().getScene());
            }
        }

    } );

    HBox hbox = new HBox ( 20 );
    hbox.setPadding ( new Insets ( 10,10,10,10 ) );
    hbox.getChildren ().addAll ( confirm,back );
    VBox vBox = new VBox ( 20 );
    vBox.getChildren ().addAll ( orderDetails,getOrderText(),comments,commentArea ,hbox);
    vBox.setPadding ( new Insets ( 10,10,10,10 ) );

    vBox.setBackground ( new Background ( myBI ) );
    setScene(new Scene(vBox, 700,600)) ;


}



    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
