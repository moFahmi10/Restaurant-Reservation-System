package restaurantModel;

import restaurantReservationsXML.Reservation;
import restaurantReservationsXML.Reservations;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

public class Table extends restaurantDataXML.Table {

    private  int number;
    private Boolean smoking;
    private int numberOfSeats;

    public Table(int number, int numberOfSeats) {
        this.number=number;
        this.numberOfSeats=numberOfSeats;

    }

    public Table() {
    }

    public boolean isAvailable(int number, String date)  {
         List<Reservation> reservationList = new ArrayList();
        try {
            reservationList = Reservations.read();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        if (reservationList == null)
            return true;

        else {
            for (Reservation reservation : reservationList) {
                if (reservation.getTableNo() == number &&  date.equals(reservation.getDate()))
                    return false;


            }
            return true;

        }
    }
    public int getNumOfTable(List<Table> tables, int seats)
    {
        for (restaurantDataXML.Table table: tables) {
            if(table.getNumberOfSeats() == seats)
                return  table.getNumber();

        }
        return 0;
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


    public void setSmoking(Boolean smoking) {
        this.smoking = smoking;
    }

    public boolean isSmoking() {
        return smoking;
    }
}
