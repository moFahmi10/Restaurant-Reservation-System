package restaurantModel;

import restaurantDataXML.Dish;

public class Appetizer extends Dish {

    private double tax = 0.1;

    public Appetizer(String name, int price, String type) {
        super(name, price, type);
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }
}
