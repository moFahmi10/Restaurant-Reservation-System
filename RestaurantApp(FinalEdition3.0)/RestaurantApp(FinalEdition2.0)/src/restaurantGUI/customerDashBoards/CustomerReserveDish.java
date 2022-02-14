package restaurantGUI.customerDashBoards;

import javafx.geometry.Insets;
import restaurantDataXML.Dish;
import restaurantModel.Appetizer;
import restaurantModel.Dessert;
import restaurantModel.MainDish;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import restaurantGUI.DashBoard;
import restaurantReservationsXML.Order;

import java.util.ArrayList;
import java.util.List;

public class CustomerReserveDish extends DashBoard {



    public CustomerReserveDish(Stage stage) {
       super(stage);
        stage.setMinWidth ( 400 );
        stage.setMinHeight ( 500 );

    }
    public void prepareScene() {
        BackgroundImage myBI = new BackgroundImage ( new Image ( "https://i.pinimg.com/originals/5c/f3/dc/5cf3dc9b57ad28bc6434f828e11fd83f.jpg", 700, 600, false, true ),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT );

        Button back = new Button ( "Back" );
        back.setStyle ( "-fx-font: 20 Impact" );
        back.setMinSize ( 100,60 );
        back.setOnAction ( e-> {
            getStage ().setScene (getCustomerDashboard1 ().getScene () );
        } ) ;
        HBox hBox = new HBox (  );
        hBox.setAlignment ( Pos.BOTTOM_LEFT );
        hBox.getChildren ().addAll ( back );
        Label chooseMenu = new Label ( "Choose Menu " );
        chooseMenu.setStyle ( "-fx-font: 20 Impact" );
        chooseMenu.setMinSize ( 70,50 );
        Button mainDish = new Button ( "Main Dishes" );
        mainDish.setStyle ( "-fx-font: 20 Impact" );
        mainDish.setMinSize ( 70,50 );
        Button appetizers = new Button ( "Appetizers" );
        appetizers.setStyle ( "-fx-font: 20 Impact" );
        appetizers.setMinSize ( 70,50 );
        Button desert = new Button ( "Deserts" );
        desert.setStyle ( "-fx-font: 20 Impact" );
        desert.setMinSize ( 70,50 );
        Button checkOut = new Button("CheckOut");
        checkOut.setStyle ( "-fx-font: 20 Impact" );
        checkOut.setMinSize ( 100,100 );
        mainDish.setOnAction ( e->{
            List<Order> orders = new ArrayList<>(  getOrders().getOrders());

            if (!(getOrders().getOrders().isEmpty()))
                for (Order order : orders) {
                    for (Dish dish : getMyRestaurant().getMainDishList()) {
                        if (dish.getName().equals(order.getDishName())) {
                            getOrders().getOrders().remove(order);
                        }

                    }

                }
            getStage().setScene ( getMainDishMenu().getScene ());
        } );

        desert.setOnAction ( e->{
            List<Order> orders = new ArrayList<>(  getOrders().getOrders());

            if (!(getOrders().getOrders().isEmpty()))
                for (Order order : orders) {
                    for (Dish dish : getMyRestaurant().getDessertsList()) {
                        if (dish.getName().equals(order.getDishName())) {
                            getOrders().getOrders().remove(order);
                        }
                    }
                }
            getStage().setScene ( getDessertMenu().getScene ());
        } );

        checkOut.setOnAction ( e->{
            getCheckOut().prepareScene();
            setTextField("Name: "+getCheckOut().getReservation().getName()+"\n"+"\n" + "Table number: " + String.valueOf ( getCheckOut().getReservation().getTableNo() ) + "\n" +"\n"+"Date and time of reservation: "+getCheckOut().getReservation().getDate()+"  "+getCheckOut().getReservation().getTime()+"\n\n");
            setTextField("--------------------------------------------");
            int i=0;
            setTextField("\n\n"+"Dishes:"+"\n");
            for (Order order:getOrders().getOrders()) {
                setTextField( getOrders().getOrders().get(i).getDishName()+"*"+getOrders().getOrders().get(i).getQuantity()+"\n");
                i++;
            }
            getCheckOut().getReservation().setOrders(getOrders());
            getCheckOut().getReservation().setPrice(getOrderPrice(getOrders().getOrders()));
            setTextField("--------------------------------------------");
           setTextField("\n\n"+"Total price: "+String.valueOf(getOrderPrice(getOrders().getOrders())));
            getStage ().setScene ( getCheckOut ().getScene () );
        } );

        appetizers.setOnAction ( e-> {

           List<Order> orders = new ArrayList<>(  getOrders().getOrders());

            if (!(getOrders().getOrders().isEmpty()))
                for (Order order : orders) {
                    for (Dish dish : getMyRestaurant().getAppetizerList()) {
                        if (dish.getName().equals(order.getDishName())) {
                            getOrders().getOrders().remove(order);
                        }

                    }

                }

            getStage().setScene(getAppetizerMenu().getScene());

        });

        VBox vBox = new VBox ( 30 );
        vBox.getChildren ().addAll (chooseMenu,appetizers,mainDish,desert,checkOut );
        vBox.setAlignment ( Pos.BASELINE_CENTER );
        HBox hBox1 = new HBox ( 200);
        hBox1.getChildren ().addAll ( back,vBox );
        hBox1.setPadding ( new Insets ( 10,10,10,10 ) );


        hBox1.setBackground ( new Background ( myBI ) );

        setScene(new Scene(hBox1, 700,600)) ;
    }

    
  public double getOrderPrice(List<Order> orders){
        double price=0;
      for (Order order:orders) {

          for (Appetizer dish:getMyRestaurant().getAppetizerList()) {
              if(dish.getName().equals(order.getDishName()))
                  price+=(dish.getPrice()+dish.getPrice()*dish.getTax())*order.getQuantity();

          }
          for (MainDish dish:getMyRestaurant().getMainDishList()) {
              if(dish.getName().equals(order.getDishName()))
                  price+=(dish.getPrice()+dish.getPrice()*dish.getTax())*order.getQuantity();

          }
          for (Dessert dish:getMyRestaurant().getDessertsList()) {
              if(dish.getName().equals(order.getDishName()))
                  price+=(dish.getPrice()+dish.getPrice()*dish.getTax())*order.getQuantity();

          }
      }
      return price;

  }





}
