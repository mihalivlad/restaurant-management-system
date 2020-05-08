import businessLayer.Restaurant;
import dataLayer.RestaurantSerializator;
import presentationLayer.AdministratorGUI;
import presentationLayer.ChefGUI;
import presentationLayer.WaiterGUI;

import javax.swing.*;

public class Main {
    public static void main(String args[]) {
        RestaurantSerializator ser = new RestaurantSerializator();
        Restaurant restaurant = ser.deserialize(args[0]);
        JFrame admFrame = new JFrame("AdministratorGUI");
        AdministratorGUI adm = new AdministratorGUI(restaurant);
        admFrame.setContentPane(adm.admin);
        admFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        admFrame.pack();
        admFrame.setVisible(true);
        JFrame waiterFrame = new JFrame("WaiterGUI");
        WaiterGUI waiter = new WaiterGUI(restaurant);
        waiterFrame.setContentPane(waiter.waiter);
        waiterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        waiterFrame.pack();
        waiterFrame.setVisible(true);
        JFrame chefFrame = new JFrame("ChefGUI");
        ChefGUI chef = new ChefGUI(restaurant);
        chefFrame.setContentPane(chef.chef);
        chefFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chefFrame.pack();
        chefFrame.setVisible(true);
        ser.serialize(restaurant);
    }
}
