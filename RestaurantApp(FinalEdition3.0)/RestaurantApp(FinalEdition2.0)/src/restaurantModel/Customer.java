package restaurantModel;

import restaurantDataXML.User;
import restaurantReservationsXML.Reservation;
import restaurantReservationsXML.Reservations;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    public Customer(String name , String username, String password) {
        super(name, username, password);
        setRole("Client");
    }

    public Customer() {
    }

    public void customerAddReservation(Reservation reservation) throws JAXBException{
        List<Reservation> reservationsList = Reservations.read(); 
        JAXBContext jaxbContext = JAXBContext.newInstance(Reservations.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT,true);
        if (reservationsList==null)
        {
            List<Reservation> reservationsNewList = new ArrayList<>();
            reservationsList=reservationsNewList;
        }
        reservationsList.add(reservation);
        Reservations reservations1 = new Reservations(reservationsList);


        marshaller.marshal(reservations1, new File("src\\restaurantReservationsXML\\reservations.xml"));

    }
    public List<Reservation> customerReadReservations(String name) throws JAXBException {
        List<Reservation> customerReservations = new ArrayList();
        JAXBContext jaxbContext = JAXBContext.newInstance(Reservations.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Reservations reservations = (Reservations) unmarshaller.unmarshal(new File("src\\restaurantReservationsXML\\reservations.xml"));
        if (reservations.getReservations()==null)
            reservations.setReservations(new ArrayList());
        for (Reservation reservation: reservations.getReservations()) {
            if(reservation.getName().equals(name))
                customerReservations.add(reservation);

        }

        return customerReservations;

    }
}
