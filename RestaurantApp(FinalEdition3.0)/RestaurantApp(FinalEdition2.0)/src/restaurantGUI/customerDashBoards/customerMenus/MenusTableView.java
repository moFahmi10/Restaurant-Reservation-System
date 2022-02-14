package restaurantGUI.customerDashBoards.customerMenus;

import restaurantDataXML.Restaurant;
import javafx.scene.control.*;
import restaurantGUI.customerDashBoards.CustomerReserveDish;

public class MenusTableView {
    Restaurant restaurant;
    CustomerReserveDish customerReserveDish;
    MainDishMenu mainDish_menu;
    double totalPrice;
    private String name;
    private double price;
    private String type;
    private RadioButton radioButton;
   // private Button edit;
    private Spinner<Integer>spinner;

    public MenusTableView(){
        this.name="";
        this.price=0;


    }
    public MenusTableView(String name, double price){
        this.name=name;
        this.price= price;

        this.radioButton=new RadioButton ( );
        this.radioButton.setSelected(false);
        this.spinner=new Spinner<> (  );
        //this.edit= new Button ( "Edit" );
        SpinnerValueFactory<Integer> valueFactory0 = new SpinnerValueFactory.IntegerSpinnerValueFactory ( 0,0 );
        spinner.setValueFactory (  valueFactory0);



        radioButton.setOnAction ( e-> {
            if (radioButton.isSelected ())
            {
                SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory ( 1, 15 );
                spinner.setValueFactory ( valueFactory );
            }
            else {
               // SpinnerValueFactory<Integer> valueFactory0 = new SpinnerValueFactory.IntegerSpinnerValueFactory ( 0,0 );
                spinner.setValueFactory (  valueFactory0);
            }

        });
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public RadioButton getRadioButton() {
        return radioButton;
    }

    public void setRadioButton(RadioButton radioButton) {
        this.radioButton = radioButton;
    }

    public Spinner<Integer> getSpinner() {
        return spinner;
    }

    public void setSpinner(Spinner<Integer> spinner) {
        this.spinner = spinner;
    }

    public CustomerReserveDish getCustomerReserveDish() {
        return customerReserveDish;
    }

    public void setCustomerReserveDish(CustomerReserveDish customerReserveDish) {
        this.customerReserveDish = customerReserveDish;
    }

    public double getSinglePrice(){


        return spinner.getValue ()*price;}





    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

}


