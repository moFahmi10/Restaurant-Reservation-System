package restaurantGUI.managerDashboards;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import restaurantModel.Manager;
import restaurantReservationsXML.Order;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAnyAttribute;
import java.util.ArrayList;
import java.util.List;

public class ManagerViewOrder {


    public static void display(int tableNo,String date) throws JAXBException {
        BackgroundImage myBI = new BackgroundImage ( new Image ( "http://www.mannaliverpool.co.uk/wp-content/uploads/2013/06/201008079.jpg", 700, 600, false, true ),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT );

        Stage window = new Stage ();
        window.initModality ( Modality.APPLICATION_MODAL );
        window.setTitle ("Costumer Order" );

        Label label = new Label ( "Customer Order" );
       TextArea textArea = new TextArea ();
        textArea.setEditable(false);
        Manager manager = new Manager();

        for (Order order: manager.managerViewOrder(tableNo,date)){
            textArea.appendText(order.getDishName()+"*"+order.getQuantity()+"\n");

        }
        Button back = new Button ( "Back" );
        back.setOnAction ( e-> window.close () );
        VBox vBox = new VBox ( 20 );
        vBox.getChildren ().addAll ( label,textArea,back );
        vBox.setPadding ( new Insets ( 10,10,10,10 ) );
        vBox.setBackground ( new Background ( myBI ) );
        vBox.setStyle ( "-fx-font: 18 Impact" );
        Scene scene = new Scene ( vBox,400,300 );
        window.setScene ( scene );
        window.show ();

    }

}
