package dataLayer;

import businessLayer.Restaurant;

import java.io.*;

public class RestaurantSerializator {

    public void serialize(Restaurant r){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("restaurant.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(r);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Restaurant deserialize(String input){
        Restaurant r = null;
        try {
            FileInputStream fileIn = new FileInputStream(input);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            r = (Restaurant) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            r = new Restaurant();
        } catch (ClassNotFoundException e) {
            System.out.println("Restaurant class not found");
            e.printStackTrace();
        }
        return r;
    }

}
