package restaurantGUI.loginScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import restaurantModel.MyRestaurant;

import javax.xml.bind.JAXBException;

public class CreateAccount {
    Stage stage;
    LoginForm loginForm;
    Scene scene;

    MyRestaurant myRestaurant = new MyRestaurant();
    public CreateAccount(Stage stage){this.stage= stage;}



    public void prepareScene (){
        BackgroundImage myBI = new BackgroundImage ( new Image ( "http://www.mannaliverpool.co.uk/wp-content/uploads/2013/06/201008079.jpg", 700, 600, false, true ),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT );

        Label usernameLabel=new Label ( "Username" );
        Label passwordLabel = new Label ( "Password" );
        Label firstNameLabel = new Label ( "First Name" );
        Label lastNameLabel = new Label ( "Last Name" );
        Label confirmPasswordLabel = new Label ("Confirm Password");
        Label emailLabel = new Label ("Email Address");
        Label genderLabel = new Label ("Gender");
        Label validateLabel = new Label ( "Invalid Input" );


        TextField usernameField = new TextField (  );
        TextField passwordField = new PasswordField ();
        TextField firstNameField = new TextField (  );
        TextField lastNameField = new TextField (  );
        TextField confirmPasswordField = new PasswordField ();
        TextField emailField = new TextField (  );
        CheckBox agreeOnConditions = new CheckBox ( "Agree on terms & Conditions" );
        ChoiceBox<String> genderBox= new ChoiceBox< >();
        genderBox.setValue ( "Male" );
        genderBox.getItems ().addAll ( "Male","Female");


        Button submit= new Button ( "Submit" );
        Button back=new Button("Login Screen");
        emailField.setPromptText ( "restaurant@email.com" );


        GridPane grid = new GridPane ();



        grid.add(usernameLabel,1,1);
        grid.add(usernameField,2,1);


        grid.add ( passwordLabel,1,2 );
        grid.add ( passwordField,2,2 );


        grid.add(confirmPasswordLabel,1,3);
        grid.add(confirmPasswordField,2,3);

        grid.add(firstNameLabel,1,4);
        grid.add(firstNameField,2,4);

        grid.add(lastNameLabel,1,5);
        grid.add(lastNameField,2,5);


        grid.add ( emailLabel,1,6 );
        grid.add(emailField,2,6);

        grid.add(genderLabel,1,7);
        grid.add(genderBox,2,7);

        grid.add(agreeOnConditions,1,8);

        grid.add(submit,1,9);
        grid.add(back,2,9);


        grid.setHgap ( 010 );
        grid.setVgap ( 010 );

        submit.setOnAction ( e-> {

            if(usernameField.getText ().isEmpty ()){
             Alert alert = new Alert ( Alert.AlertType.ERROR );
             alert.setTitle ( "Wrong Input" );
             alert.setHeaderText ( "" );
             alert.setContentText ( "Invalid Username  Input" );
             alert.show ();
            }
            else if(passwordField.getText ().isEmpty ()){
                Alert alert = new Alert ( Alert.AlertType.ERROR );
                alert.setTitle ( "Wrong Input" );
                alert.setHeaderText ( "" );
                alert.setContentText ( "Invalid Password Input" );
                alert.show ();

            }
            else if(! confirmPasswordField.getText ().matches (passwordField.getText ())){
                Alert alert = new Alert ( Alert.AlertType.ERROR );
                alert.setTitle ( "Wrong Input" );
                alert.setHeaderText ( "" );
                alert.setContentText ( "Passwords Don't Match" );
                alert.show ();
            }
            else if(firstNameField.getText ().isEmpty ()) {
                Alert alert = new Alert ( Alert.AlertType.ERROR );
                alert.setTitle ( "Wrong Input" );
                alert.setHeaderText ( "" );
                alert.setContentText ( "Invalid First Name Input" );
                alert.show ();
            }
            else if(lastNameField.getText ().isEmpty ()) {
                Alert alert = new Alert ( Alert.AlertType.ERROR );
                alert.setTitle ( "Wrong Input" );
                alert.setHeaderText ( "" );
                alert.setContentText ( "Invalid Last Name Input" );
                alert.show ();
            }

            else if (!emailField.getText ().matches ( "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com" )){
                Alert alert = new Alert ( Alert.AlertType.ERROR );
                alert.setTitle ( "Wrong Input" );
                alert.setHeaderText ( "" );
            alert.setContentText ( "Invalid Email Input" );
            alert.show ();
        }
            else if(!agreeOnConditions.isSelected ()){
                Alert alert = new Alert ( Alert.AlertType.ERROR );
                alert.setTitle ( "Wrong Input" );
                alert.setHeaderText ( "Our Conditions is to save your information for further requests" );
                alert.setContentText ( "You must accept terms&conditions" );
                alert.show ();
            }
            else {
                Alert alert = new Alert ( Alert.AlertType.CONFIRMATION );
                alert.setTitle ( "" );
                alert.setHeaderText ( "" );
                alert.setContentText ( "Your Account Has Been Successfully Created\nPlease restart the program to login into the new account" );
                alert.show ();
                try {
                    myRestaurant.addCustomer ( firstNameField.getText ()+" "+lastNameField.getText(), usernameField.getText (), passwordField.getText () );
                } catch (JAXBException ex) {
                    ex.printStackTrace ();
                }
                loginForm.prepareScene();
                stage.setScene ( loginForm.getScene () );
            }
        } );


        back.setOnAction ( new EventHandler<ActionEvent> () {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene ( loginForm.getScene () );
            }
        } );
        grid.setBackground ( new Background ( myBI ) );
        grid.setStyle ( "-fx-font: 18 Impact" );
        scene = new Scene(grid,700,600);
    }
    public Scene getScene(){return this.scene;}

    public void setLoginForm(LoginForm loginForm) {
        this.loginForm = loginForm;
    }


}
