package restaurantReservationsXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {
    @XmlElement(name = "dish")
    private String dishName;
    @XmlElement(name="quantity")
    private int quantity;

    public Order() {
    }

    public Order(String dishName, int quantity) {
        this.dishName = dishName;
        this.quantity = quantity;
    }



    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
