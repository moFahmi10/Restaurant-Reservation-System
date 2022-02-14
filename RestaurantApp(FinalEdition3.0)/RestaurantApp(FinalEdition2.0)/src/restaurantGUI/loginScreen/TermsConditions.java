package restaurantGUI.loginScreen;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TermsConditions {
    public static void display(){
        BackgroundImage myBI = new BackgroundImage ( new Image ( "http://www.mannaliverpool.co.uk/wp-content/uploads/2013/06/201008079.jpg", 700, 600, false, true ),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT );

        Stage window = new Stage (  );
        window.initModality ( Modality.APPLICATION_MODAL );
        window.setTitle ( "Terms&Conditions" );
        Label terms = new Label ( "" +
                "If you wish to create a booking history, favourites list or rate a restaurant you\n" +
                " will need to register an account with us and provide the information we require.\n" +
                "It is your responsibility to keep your login information and your password safe \n" +
                "and secure. We suggest you choose a strong password and keep that confidential at\n " +
                "all times. If there is any activity undertaken with your login details then you are\n" +
                " responsible. Your account is for your sole, personal use. You may not allow others\n" +
                " to use your account and you may not assign or otherwise transfer your account to any\n " +
                "other person or entity. If you think that your details may have been used by someone\n" +
                " else then you must notify us as soon as possible. You must also change your password \n" +
                "immediately. You agree to indemnify Restaurant Hub against any and all liability we\n " +
                "suffer arising out of your failure to maintain the confidentiality of your username\n " +
                "and password.\n" +
                "You confirm that the information you provided to us on registering as a registered user\n" +
                " was at the time you provided it current, complete and accurate, and you agree to \n" +
                "maintain and update the information as required to keep it so.\n" +
                "You will own all the content and information that you submit to your account. You are granting \n" +
                "Restaurant Hub and our affiliates a non-exclusive, worldwide, transferable, and sublicensable\n" +
                " right to use, copy, modify, distribute, publish, and process, information and content that\n" +
                " you provide, without any further compensation to you or others." );
        Button back = new Button ( "Back" );
        back.setOnAction ( e-> window.close () );
        VBox vBox = new VBox ( 30 );
        vBox.getChildren ().addAll ( terms,back );
        vBox.setPadding ( new Insets ( 10,10,10,10 ) );
        vBox.setBackground ( new Background ( myBI ) );
        vBox.setStyle ( "-fx-font: 16 Impact" );
        Scene scene = new Scene ( vBox,600,400 );
        window.setScene ( scene );
        window.show ();
    }
}
