package restaurantModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import restaurantGUI.managerDashboards.ManagerTable;
import restaurantReservationsXML.Order;
import restaurantReservationsXML.Reservation;
import restaurantReservationsXML.Reservations;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee {
    public Manager(String name, String role, String username, String password) {
        super(name, role, username, password);
    }

    public Manager() {
    }

    public ObservableList<ManagerTable> managerReadReservations(String date) throws JAXBException {
        List<Reservation> reservationList = Reservations.read();

        ObservableList<ManagerTable> managerTables = FXCollections.observableArrayList();

        if (reservationList == null)
            reservationList = new ArrayList<>();


        for (Reservation reservation : reservationList)
            if (reservation.getDate().equals(date))
                managerTables.add(new ManagerTable(reservation.getTime(), reservation.getTableNo(), reservation.getName(), reservation.getPrice(),reservation.getDate()));


        return managerTables;
    }

    public List<Order> managerViewOrder(int tableNo, String date) throws JAXBException {
        List<Order> order = new ArrayList<>();
        JAXBContext jaxbContext = JAXBContext.newInstance(Reservations.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Reservations reservations = (Reservations) unmarshaller.unmarshal(new File("src\\restaurantReservationsXML\\reservations.xml"));
        if (reservations.getReservations() == null)
            reservations.setReservations(new ArrayList());
        for (Reservation reservation : reservations.getReservations()) {
            if (reservation.getTableNo() == tableNo && reservation.getDate().equals(date))
                return reservation.getOrders().getOrders();

        }
        return order;
    }
    public double managerGetTotalMoneyDay(List<Reservation> reservationList){
        double totalMoney=0;
        if(reservationList==null)
            reservationList = new ArrayList<>();
        for (Reservation reservation:reservationList) {
            if (reservation.getDate().equals(LocalDate.now().toString())){
                totalMoney+=reservation.getPrice();
            }

        }
        return totalMoney;
    }
    public double managerGetTotalMoneyMonth(List<Reservation> reservationList){
        double totalMoney=0;
        if(reservationList==null)
            reservationList = new ArrayList<>();
        for (Reservation reservation:reservationList) {
            if(LocalDate.parse(reservation.getDate()).getMonth().equals((LocalDate.now().getMonth())))
                totalMoney+=reservation.getPrice();


        }
        return totalMoney;
    }
    public int managerGetTableDay(List<Reservation> reservationList){
        int tables=0;
        if(reservationList==null)
            reservationList = new ArrayList<>();
        for (Reservation reservation:reservationList) {
            if (reservation.getDate().equals(LocalDate.now().toString()))
            tables++;
        }
        return tables;
    }
    public int managerGetTableMonth(List<Reservation> reservationList){
        int tables=0;
        if(reservationList==null)
            reservationList = new ArrayList<>();
        for (Reservation reservation:reservationList) {
            if(LocalDate.parse(reservation.getDate()).getMonth().equals((LocalDate.now().getMonth())))
                tables++;
        }
        return tables;
    }
}
