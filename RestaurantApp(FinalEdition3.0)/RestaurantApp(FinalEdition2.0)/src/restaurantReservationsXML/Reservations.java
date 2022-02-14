package restaurantReservationsXML;

import com.sun.xml.internal.txw2.Document;

import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement(name="reservations")
@XmlAccessorType(XmlAccessType.FIELD)
public class Reservations {
    @XmlElement(name="reservation")
    private List<Reservation> reservations;

    public Reservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Reservations() {
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }



    public static List<Reservation> read() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Reservations.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Reservations reservations = (Reservations) unmarshaller.unmarshal(new File("src\\restaurantReservationsXML\\reservations.xml"));


    return reservations.getReservations();
    }
}
