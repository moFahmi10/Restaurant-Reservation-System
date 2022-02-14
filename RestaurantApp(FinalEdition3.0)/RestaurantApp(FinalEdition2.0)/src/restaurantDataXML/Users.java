package restaurantDataXML;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name="users")
@XmlAccessorType(XmlAccessType.FIELD)


public class Users {
    //************************************************************************************************
    @XmlElement(name="user")
    private List<User> users;



    //************************************************************************************************

    public Users(List<User> users) {
        this.users = users;
    }
    public Users() {

    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


}
