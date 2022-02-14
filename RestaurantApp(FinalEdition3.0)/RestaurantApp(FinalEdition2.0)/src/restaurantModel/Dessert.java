package restaurantModel;

import restaurantDataXML.Dish;

public class Dessert extends Dish {
    private double tax = 0.2;

    public Dessert(String name, int price, String type) {
        super(name, price, type);
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }
}
