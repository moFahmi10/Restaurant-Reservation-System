package restaurantModel;

import restaurantDataXML.Dish;

public class MainDish extends Dish {

    private double tax = 0.15;

    public MainDish(String name, int price, String type) {
        super(name, price, type);
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }
}
