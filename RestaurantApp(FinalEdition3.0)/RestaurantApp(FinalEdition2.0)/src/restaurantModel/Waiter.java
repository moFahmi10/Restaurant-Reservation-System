package restaurantModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import restaurantGUI.waiterDashBoards.WaiterTable;
import restaurantReservationsXML.Reservation;
import restaurantReservationsXML.Reservations;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

public class Waiter extends Employee {
    public Waiter(String name, String role, String username, String password) {
        super(name, role, username, password);
    }

    public Waiter() {

    }

    public ObservableList<WaiterTable> waiterReadReservations (String date) throws JAXBException {
        List<Reservation> reservationList = Reservations.read();

        ObservableList<WaiterTable> waiterTables = FXCollections.observableArrayList ();

        if(reservationList==null)
            reservationList=new ArrayList<>();



        for (Reservation reservation : reservationList )
            if(reservation.getDate().equals(date))
            waiterTables.add(new WaiterTable(reservation.getTableNo(),reservation.getName(),reservation.getTime()));



        return waiterTables;
    }
}
