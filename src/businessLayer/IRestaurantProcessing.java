package businessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public interface IRestaurantProcessing {
    //Admin

    /**
     * @pre menu item != null
     * @post new size menu = old size menu + 1
     * @invariant for checking null objects
     * @param mi the menu item you want to add
     */
    void addMenuItem(MenuItem mi);

    /**
     * @pre (old menu item != new menu item) != null
     * @post the item is edited
     * @invariant for checking null objects
     * @param miOld old menu item will be replace
     * @param miNew new menu item will be add instead of old one
     */
    void editMenuItem(MenuItem miOld, MenuItem miNew);

    /**
     * @pre menu item != null
     * @post new size menu = old size menu - 1
     * @invariant for checking null objects
     * @param mi the menu item you want to delete
     */
    void deleteMenuItem(MenuItem mi);

    /**
     * @return the menu of the restaurant
     */
    ArrayList<MenuItem> getMenu();
    //Waiter

    /**
     * @pre order != null and menu items != null and order id greater than 0
     * @post new size orders = old size orders + 1
     * @invariant for checking null objects, int greater than 0
     * @param o is the specs of the order you want to add
     * @param mi is the list of the products connected to the order
     */
    void addOrder(Order o, ArrayList<MenuItem> mi);

    /**
     * @pre order != null and order id greater than 0
     * @post price greater than 0
     * @invariant for checking null objects, int greater than 0
     * @param o is the order to compute the price of
     * @return a float value that represents the price of the order
     */
    float computePrice(Order o);

    /**
     * @pre order != null and order id greater than 0
     * @post a txt file is generated
     * @invariant for checking null objects, int greater than 0
     * @param o is the order to print the bill as .txt
     */
    void generateBill(Order o);

    /**
     *
     * @return the orders of the restaurant
     */
    Map<Order, ArrayList<MenuItem>> getOrders();
    //Others
    MenuItem findByName(String str);
    Order findByID(int id);
    int generateOrderID();

}
