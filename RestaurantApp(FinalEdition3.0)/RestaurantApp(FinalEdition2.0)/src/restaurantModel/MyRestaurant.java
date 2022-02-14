package restaurantModel;

import restaurantDataXML.Dish;
import restaurantDataXML.Restaurant;
import restaurantDataXML.User;
import restaurantDataXML.Users;
import restaurantReservationsXML.Reservation;
import restaurantReservationsXML.Reservations;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyRestaurant {
    List<Customer> customerList= new ArrayList();
    List<Waiter> waiterList = new ArrayList();
    List<Cook> cookList = new ArrayList();
    List<Manager> managerList = new ArrayList();
    List<restaurantModel.Table> nonSmokingTableList = new ArrayList();
    List<restaurantModel.Table> smokingTableList = new ArrayList();
    List<Appetizer> appetizerList = new ArrayList<>();
    List<Dessert> dessertsList = new ArrayList<>();
    List<MainDish> mainDishList = new ArrayList<>();

    public MyRestaurant() {
    }

    public MyRestaurant(Restaurant restaurant) {


        //*********************************USER CLASSIFICATION**********************************
        for (User user : restaurant.getUsers().getUsers()) {
            if (restaurant.getUsers().getUsers()==null) {
                Users users = new Users();
                restaurant.setUsers(users);
            }
            if (user.getRole().equals("Client")) {
                Customer temp = new Customer(user.getName(), user.getUsername(), user.getPassword());
                user.setRole("Client");
                customerList.add(temp);
            } else if (user.getRole().equals("Waiter")) {
                Waiter temp = new Waiter(user.getName(), user.getRole(), user.getUsername(), user.getPassword());
                waiterList.add(temp);
            } else if (user.getRole().equals("Cook")) {
                Cook temp = new Cook(user.getName(), user.getRole(), user.getUsername(), user.getPassword());
                cookList.add(temp);
            } else if (user.getRole().equals("Manager")) {
                Manager temp = new Manager(user.getName(), user.getRole(), user.getUsername(), user.getPassword());
                managerList.add(temp);
            }
        }
        //*********************************TABLE CLASSIFICATION**********************************
        for (restaurantDataXML.Table table : restaurant.getTables().getTables()) {
            Table temp = new Table(table.getNumber(), table.getNumberOfSeats());
            if (table.isSmoking()) {

                smokingTableList.add(temp);
            } else nonSmokingTableList.add(temp);
        }
        //*********************************DISHES CLASSIFICATION**********************************
        for (Dish dish : restaurant.getDishes().getDishes()) {
            if (dish.getType().equals("appetizers")) {
                Appetizer temp = new Appetizer(dish.getName(), dish.getPrice(), dish.getType());
                appetizerList.add(temp);
            }
            if (dish.getType().equals("desserts")) {
                Dessert temp = new Dessert(dish.getName(), dish.getPrice(), dish.getType());
                dessertsList.add(temp);
            }
            if(dish.getType().equals("main_course")) {
                MainDish temp= new MainDish(dish.getName(),dish.getPrice(),dish.getType());
                mainDishList.add(temp);
            }
        }

    }



    public static Restaurant loadRestaurantData() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(restaurantDataXML.Restaurant.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        restaurantDataXML.Restaurant restaurant = (restaurantDataXML.Restaurant) unmarshaller.unmarshal(new File("src\\RestaurantDataXML\\input.xml"));

             return  restaurant;
    }
    public void addCustomer(String name,String username,String password) throws JAXBException {
        User customer = new Customer(name,username,password);
        Restaurant restaurant = new Restaurant();
        restaurant = loadRestaurantData();
        restaurant.getUsers().getUsers().add(customer);

        JAXBContext jaxbContext = JAXBContext.newInstance(Restaurant.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT,true);
        marshaller.marshal(restaurant, new File("src\\RestaurantDataXML\\input.xml"));


    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Waiter> getWaiterList() {
        return waiterList;
    }

    public void setWaiterList(List<Waiter> waiterList) {
        this.waiterList = waiterList;
    }

    public List<Cook> getCookList() {
        return cookList;
    }

    public void setCookList(List<Cook> cookList) {
        this.cookList = cookList;
    }

    public List<Manager> getManagerList() {
        return managerList;
    }

    public void setManagerList(List<Manager> managerList) {
        this.managerList = managerList;
    }

    public List<Table> getNonSmokingTableList() {
        return nonSmokingTableList;
    }

    public void setNonSmokingTableList(List<Table> nonSmokingTableList) {
        this.nonSmokingTableList = nonSmokingTableList;
    }

    public List<Table> getSmokingTableList() {
        return smokingTableList;
    }

    public void setSmokingTableList(List<Table> smokingTableList) {
        this.smokingTableList = smokingTableList;
    }

    public List<Appetizer> getAppetizerList() {
        return appetizerList;
    }

    public void setAppetizerList(List<Appetizer> appetizerList) {
        this.appetizerList = appetizerList;
    }

    public List<Dessert> getDessertsList() {
        return dessertsList;
    }

    public void setDessertsList(List<Dessert> dessertsList) {
        this.dessertsList = dessertsList;
    }

    public List<MainDish> getMainDishList() {
        return mainDishList;
    }

    public void setMainDishList(List<MainDish> mainDishList) {
        this.mainDishList = mainDishList;
    }
}
