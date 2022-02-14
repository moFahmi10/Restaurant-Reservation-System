package restaurantModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import restaurantGUI.cookDashBoards.CookerTable;
import restaurantReservationsXML.Reservation;
import restaurantReservationsXML.Reservations;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

public class Cook extends Employee {

    public Cook(String name, String role, String username, String password) {
        super(name, role, username, password);
    }

    public Cook() {
    }
    public ObservableList<CookerTable> cookReadReservations (String date) throws JAXBException {
        List<Reservation> reservationList = Reservations.read();

        ObservableList<CookerTable> cookerTables = FXCollections.observableArrayList ();

        if(reservationList==null)
            reservationList=new ArrayList<>();



        for (Reservation reservation : reservationList )
            if(reservation.getDate().equals(date))
                cookerTables.add(new CookerTable(reservation.getTableNo(),reservation.getOrders().getOrders(),reservation.getTime(),reservation.getComment()));


        return cookerTables;
    }
}
