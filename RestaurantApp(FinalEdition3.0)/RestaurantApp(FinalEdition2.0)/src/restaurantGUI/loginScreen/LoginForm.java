package restaurantGUI.loginScreen;

import restaurantGUI.DashBoard;
import restaurantModel.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import restaurantReservationsXML.Reservation;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

public class LoginForm extends DashBoard {

    List<Reservation> reservationList = new ArrayList<>();

    public LoginForm(Stage stage) {
        super(stage);
        stage.setMinWidth ( 400 );
        stage.setMinHeight ( 500 );

    }
    public void prepareScene (){
        BackgroundImage myBI = new BackgroundImage ( new Image ( "https://i.ytimg.com/vi/y3oUCUtagMg/maxresdefault.jpg", 700, 600, false, true ),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT );

        Label usernameLabel = new Label("Username: ");
        usernameLabel.setStyle ( "-fx-font: 24 Impact"  );
        Label passwordLabel = new Label("Password: ");
        passwordLabel.setStyle ( "-fx-font: 24 Impact"  );



        TextField usernameField = new TextField();
        usernameField.setStyle ( "-fx-font: 24 Impact" );

        TextField passwordField = new PasswordField ();
        passwordField.setStyle ( "-fx-font: 24 Impact" );

        Button submit = new Button("Submit");
        submit.setStyle ( "-fx-font: 20 Impact" );
        submit.setMinSize ( 70,50 );
        Button term = new Button("Terms&Conditions");
        term.setStyle ( "-fx-font: 20 Impact" );
        term.setMinSize ( 70,50 );
        term.setOnAction ( e-> TermsConditions.display () );
        Button register = new Button ( "Create an Account" );
        register.setStyle ( "-fx-font: 20 Impact" );
        register.setMinSize ( 70,50 );



        GridPane grid = new GridPane();
        grid.add(usernameLabel, 1,  1 );
        grid.add(passwordLabel, 1,  2 );
        grid.add(usernameField,2,  1);
        grid.add(passwordField, 2,  2 );
        grid.add(submit, 2,  3 );
        grid.add(register,2,4);
        grid.add ( term,15,4);
        grid.setVgap ( 010 );
        grid.setHgap ( 010 );
        grid.setBackground ( new Background ( myBI ) );

        setScene(new Scene(grid, 700,600));
//****************************************************************************************************************
       register.setOnAction ( new EventHandler<ActionEvent> () {
            @Override
            public void handle(ActionEvent event) {
                getStage().setScene ( getCreateAccount().getScene () );
            }
        } );
//****************************************************************************************************************
        submit.setOnAction ( new EventHandler<ActionEvent> () {
            @Override
            public void handle(ActionEvent event) {
                try {
                    reservationList = getReservations().read();
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                String username=usernameField.getText();
                String password=passwordField.getText();
                String role = null;
                boolean checkPassword = false;

                loginValidate(username,password);

            }
        } );
//****************************************************************************************************************
    }
    public void loginValidate(String username,String password){
        boolean flag=false;
        for (Customer customer:getMyRestaurant().getCustomerList()) {
            if(username.equals(customer.getUsername()) && password.equals(customer.getPassword())) {
                flag=true;
                getCustomer().setName(customer.getName());
                getCustomerDashboard1().setCustomer(customer);
                getCheckOut().getReservation().setName(customer.getName());
                getCustomerDashboard1().prepareScene();
                getStage().setScene(getCustomerDashboard1().getScene());
                break;
            }
        }
        for (Manager manager: getMyRestaurant().getManagerList()) {
            if(username.equals(manager.getUsername()) && password.equals(manager.getPassword())) {
                flag=true;
                getManagerDashboard().prepareScene();
                getStage().setScene(getManagerDashboard().getScene());
                break;
            }
        }
        for (Waiter waiter:getMyRestaurant().getWaiterList()) {
            if (username.equals(waiter.getUsername()) && password.equals(waiter.getPassword())) {
                flag = true;
                getWaiterDashboard().prepareScene();
                getStage().setScene(getWaiterDashboard().getScene());
                break;
            }
        }
            for (Cook cook: getMyRestaurant().getCookList()) {
                if(username.equals(cook.getUsername()) && password.equals(cook.getPassword())) {
                    flag=true;
                    getCookDashboard().prepareScene();
                    getStage().setScene(getCookDashboard().getScene());
                    break;
                }
            }
            if (flag==false)
            {
                Alert alert = new Alert ( Alert.AlertType.ERROR );
                alert.setTitle ( "Error" );
                alert.setHeaderText ( "" );
                alert.setContentText ( "Wrong Username or Password" );
                alert.showAndWait ();
            }

        }




    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
}