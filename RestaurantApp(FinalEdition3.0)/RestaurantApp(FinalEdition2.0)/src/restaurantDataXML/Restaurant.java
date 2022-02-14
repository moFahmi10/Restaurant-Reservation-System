package restaurantDataXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="restaurant")
@XmlAccessorType(XmlAccessType.FIELD)

public class Restaurant {
    //************************************************************************************************


    @XmlElement(name = "users")
    private Users users = null;
    @XmlElement(name = "tables")
    private Tables tables = null;
    @XmlElement(name = "dishes")
    private Dishes dishes = null;

    //************************************************************************************************
    public Restaurant(Users users) {
        this.users = users;
    }

    public Restaurant()  {

    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Tables getTables() {
        return tables;
    }

    public void setTables(Tables tables) {
        this.tables = tables;
    }

    public Dishes getDishes() {
        return dishes;
    }

    public void setDishes(Dishes dishes) {
        this.dishes = dishes;
    }
}
