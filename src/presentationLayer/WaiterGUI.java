package presentationLayer;

import businessLayer.IRestaurantProcessing;
import businessLayer.MenuItem;
import businessLayer.Order;
import businessLayer.Restaurant;
import com.sun.org.apache.xpath.internal.operations.Or;
import dataLayer.RestaurantSerializator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

public class WaiterGUI {
    private JTextField id;
    private JComboBox prodCombo;
    private JButton addButton;
    private JButton createButton;
    private JButton priceButton;
    private JButton billButton;
    private JTable tableOrder;
    private JTextField table;
    public JPanel waiter;
    private JLabel Products;
    private IRestaurantProcessing restaurant;
    private RestaurantSerializator r = new RestaurantSerializator();
    private ArrayList<MenuItem> prodList;

    public WaiterGUI(IRestaurantProcessing restaurant) {
        this.restaurant = restaurant;
        init();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prodList.add(restaurant.getMenu().get(prodCombo.getSelectedIndex()));
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(id.getText().equals("")) {
                        Order o = new Order(restaurant.generateOrderID(), Integer.parseUnsignedInt(table.getText()));
                        if(!prodList.isEmpty()){
                            restaurant.addOrder(o, prodList);
                        }else{
                            JOptionPane.showMessageDialog(null,"No products in order","ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Id is auto when creating order","ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"Table incorrect","ERROR", JOptionPane.ERROR_MESSAGE);
                }
                init();
            }
        });
        priceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Order order = restaurant.findByID(Integer.parseUnsignedInt(id.getText()));
                    if (order != null) {
                        JOptionPane.showMessageDialog(null,"Order :"+order.getId()+" Table :"+order.getTable()+" PRICE: "+restaurant.computePrice(order),"PRICE", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null,"This order does not exist","ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"Id incorrect","ERROR", JOptionPane.ERROR_MESSAGE);
                }
                init();
            }
        });
        billButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Order order = restaurant.findByID(Integer.parseUnsignedInt(id.getText()));
                    if (order != null) {
                        restaurant.generateBill(order);
                        JOptionPane.showMessageDialog(null,"Bill created","INFO", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null,"This order does not exist","ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"Id incorrect","ERROR", JOptionPane.ERROR_MESSAGE);

                }
                init();
            }
        });
    }

    private void init(){
        id.setText("");
        table.setText("");
        DefaultComboBoxModel dml= new DefaultComboBoxModel();
        for (MenuItem item: restaurant.getMenu()) {
            dml.addElement(item.getName());
        }
        prodCombo.setModel(dml);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("table");
        model.addColumn("date");
        model.addColumn("products");
        DateFormat df = new SimpleDateFormat("dd/MM/YYYY");
        for (Map.Entry<Order, ArrayList<MenuItem>> o : restaurant.getOrders().entrySet()) {
            String str ="";
            for (MenuItem mi: o.getValue()) {
                str+=mi.getName()+"; ";
            }
            model.addRow(new String[]{String.valueOf(o.getKey().getId()), String.valueOf(o.getKey().getTable()), df.format(o.getKey().getOrderDate()), str});
        }
        tableOrder.setModel(model);
        prodList = new ArrayList<>();
        r.serialize((Restaurant) restaurant);
    }
}
