package presentationLayer;

import businessLayer.MenuItem;
import businessLayer.Restaurant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Observable;
import java.util.Observer;

public class ChefGUI implements Observer {
    private JTable table;
    public JPanel chef;

    public ChefGUI(Restaurant restaurant) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("name");
        model.addColumn("components");
        table.setModel(model);
        restaurant.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        MenuItem mi = (MenuItem) arg;
        JOptionPane.showMessageDialog(null, "Prepare "+mi.getName()+" with "+mi.getBaseProducts(),"INFO", JOptionPane.INFORMATION_MESSAGE);
        model.addRow(new String[]{mi.getName(),mi.getBaseProducts()});

    }
}
