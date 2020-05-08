package presentationLayer;

import businessLayer.*;
import dataLayer.RestaurantSerializator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class AdministratorGUI {
    private JTextField name;
    private JTextField price;
    private JComboBox comp;
    private JButton addButton;
    private JButton createButton;
    private JTable tableMenu;
    private JButton editButton;
    private JButton deleteButton;
    public JPanel admin;
    private IRestaurantProcessing restaurant;
    private ArrayList<MenuItem> compList;
    private RestaurantSerializator r = new RestaurantSerializator();

    public AdministratorGUI(IRestaurantProcessing restaurant) {
        this.restaurant = restaurant;
        init();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            compList.add(restaurant.getMenu().get(comp.getSelectedIndex()));
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuItem mi;
                if(compList.isEmpty()){
                    try {
                        mi = new BaseProduct(name.getText(), Float.parseFloat(price.getText()));
                    }catch (NumberFormatException ex){
                        JOptionPane.showMessageDialog(null,"Invalid Price","ERROR", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }else{
                    mi = new CompositeProduct(compList, name.getText());
                }
                if(restaurant.findByName(name.getText()) == null && !name.getText().equals("") && mi.computePrice()>0) {
                    restaurant.addMenuItem(mi);
                }else{
                    JOptionPane.showMessageDialog(null,"Menu Item exist in menu of the restaurant","ERROR", JOptionPane.ERROR_MESSAGE);
                }
                init();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuItem mi;
                if(compList.isEmpty()){
                    try {
                        mi = new BaseProduct(name.getText(), Float.parseFloat(price.getText()));
                    }catch (NumberFormatException ex){
                        JOptionPane.showMessageDialog(null,"Invalid Price","ERROR", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }else{
                    mi = new CompositeProduct(compList, name.getText());
                }
                if(restaurant.findByName(name.getText()) != null && mi.computePrice()>0) {
                    restaurant.editMenuItem(restaurant.findByName(mi.getName()), mi);
                }else{
                    JOptionPane.showMessageDialog(null,"Menu Item not found","ERROR", JOptionPane.ERROR_MESSAGE);
                }
                init();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(restaurant.findByName(name.getText()) != null) {
                    restaurant.deleteMenuItem(restaurant.findByName(name.getText()));
                }else{
                    JOptionPane.showMessageDialog(null,"Menu Item not found","ERROR", JOptionPane.ERROR_MESSAGE);
                }
                init();
            }
        });
    }
    private void init(){
        name.setText("");
        price.setText("");
        DefaultComboBoxModel dml= new DefaultComboBoxModel();
        for (MenuItem item: restaurant.getMenu()) {
            dml.addElement(item.getName());
        }
        comp.setModel(dml);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("name");
        model.addColumn("price");
        model.addColumn("components");
        for (MenuItem mi : restaurant.getMenu()) {
                model.addRow(new String[]{mi.getName(), String.valueOf(mi.computePrice()), mi.getBaseProducts()});
        }
        tableMenu.setModel(model);
        compList = new ArrayList<>();
        r.serialize((Restaurant) restaurant);
    }

}
