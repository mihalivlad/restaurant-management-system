package dataLayer;

import businessLayer.MenuItem;
import businessLayer.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BillFileWriter {

    public void writeBill(Order o, ArrayList<MenuItem> mi, float price) {
        try {
            DateFormat df = new SimpleDateFormat("dd-MM-YYYY");
            String date = df.format(o.getOrderDate());
            FileWriter writer = new FileWriter("BillNr"+o.getId()+"Date"+date+".txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(o.toString());
            bufferedWriter.newLine();
            for (MenuItem item: mi) {
                bufferedWriter.write(item.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.write("Total Price: "+price);

            bufferedWriter.close();
        } catch ( IOException e) {
            e.printStackTrace();
        }
    }
}
