package restaurantReservationsXML;

import javax.xml.bind.annotation.*;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@XmlRootElement(name="reservation")
@XmlAccessorType(XmlAccessType.FIELD)
public class Reservation {
    @XmlElement(name="name")
    private String name;
    @XmlElement(name="tableNo")
    private int tableNo;
    @XmlElement(name="date")
    private String date;
    @XmlElement(name="time")
    private String time;
    @XmlElement(name="orders")
    private Orders orders;
    @XmlElement(name="comment")
    private String comment;
    @XmlElement(name="price")
    private double price;


    public Reservation(String name, int tableNo, Orders orders) {
        this.name = name;
        this.tableNo = tableNo;
        this.orders = orders;
    }

    public Reservation() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTableNo() {
        return tableNo;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
