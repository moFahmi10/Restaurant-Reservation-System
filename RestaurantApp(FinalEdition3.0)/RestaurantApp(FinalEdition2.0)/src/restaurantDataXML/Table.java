package restaurantDataXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="table")
@XmlAccessorType(XmlAccessType.FIELD)

public class Table {
    //************************************************************************************************

    @XmlElement(name="number")
    private  int number;
    @XmlElement(name="number_of_seats")
    private int numberOfSeats;
    @XmlElement(name="smoking")
    private boolean smoking;
   @XmlElement(name="reserved")
    private boolean reserved ;

    //************************************************************************************************

    public Table(int number, int numberOfSeats, boolean smoking) {
        this.number = number;
        this.numberOfSeats = numberOfSeats;
        this.smoking = smoking;
    }

    public Table() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

   public boolean isNotReserved() {
        return !reserved;
    }

    public void setReserved(boolean reserved) {
       this.reserved = reserved;
   }

}
