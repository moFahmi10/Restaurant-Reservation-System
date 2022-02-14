package restaurantDataXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="tables")
@XmlAccessorType(XmlAccessType.FIELD)

public class Tables {
    //************************************************************************************************
    @XmlElement(name="table")
    private List<Table> tables;
    //************************************************************************************************
    public Tables(List<Table> tables) {
        this.tables = tables;
    }

    public Tables() {
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
    public static boolean isAvailable(List<Table> tables,boolean isSmoking,int numberSeats)
    {
        boolean isAvailable = false;
        for (Table table:tables) {
            if (table.isSmoking() == isSmoking && table.isNotReserved() && table.getNumberOfSeats() == numberSeats) {
                isAvailable = true;
                break;

            }
        }
        return isAvailable;
    }

}
