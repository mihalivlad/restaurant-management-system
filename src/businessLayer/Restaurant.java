package businessLayer;

import dataLayer.BillFileWriter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class Restaurant extends Observable implements IRestaurantProcessing, Serializable {
    private Map<Order, ArrayList<MenuItem>> orders;
    private ArrayList<MenuItem> menu;

    public Restaurant() {
        orders = new HashMap<>();
        menu = new ArrayList<>();
    }

    private static void checkNull(Object o) {
        assert o != null;
    }

    private static void checkGreter0(int o) {
        assert o > 0;
    }

    private static void checkGreter0(float o) {
        assert o > 0;
    }

    public Map<Order, ArrayList<MenuItem>> getOrders() {
        return orders;
    }

    public void setOrders(Map<Order, ArrayList<MenuItem>> orders) {
        this.orders = orders;
    }

    public ArrayList<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<MenuItem> menu) {
        this.menu = menu;
    }

    @Override
    public void addMenuItem(MenuItem mi) {
        checkNull(mi);
        int oldsize = menu.size();
        menu.add(mi);
        assert menu.size() == oldsize + 1;
        checkNull(mi);
    }

    @Override
    public void editMenuItem(MenuItem miOld ,MenuItem miNew) {
        checkNull(miOld);
        checkNull(miNew);
        assert miOld != miNew;
        menu.set(menu.indexOf(miOld), miNew);
        for (MenuItem mi: menu) {
            mi.update(miNew);
        }
        checkNull(miOld);
        checkNull(miNew);
        assert miOld != miNew;
    }

    @Override
    public void deleteMenuItem(MenuItem mi) {
        checkNull(mi);
        int oldsize = menu.size();
        menu.remove(mi);
        checkNull(mi);
        assert menu.size() == oldsize - 1;
    }

    @Override
    public void addOrder(Order o, ArrayList<MenuItem> mi) {
        checkNull(o);
        checkGreter0(o.getId());
        checkNull(mi);
        int oldsize = orders.size();
        for (MenuItem item: mi) {
            checkNull(item);
            if(item.getClass().getSimpleName().equals("CompositeProduct")){
                //notify chef
                setChanged();
                notifyObservers(item);
            }
        }
        orders.put(o, mi);
        assert oldsize + 1 == orders.size();
        checkNull(o);
        checkGreter0(o.getId());
        checkNull(mi);
        for (MenuItem item: mi) {
            checkNull(item);
        }
    }

    @Override
    public float computePrice(Order o) {
        checkNull(o);
        checkGreter0(o.getId());
        float price=0;
        ArrayList<MenuItem> mi = orders.get(o);
        for (MenuItem item: mi) {
            price += item.computePrice();
        }
        checkGreter0(price);
        checkNull(o);
        checkGreter0(o.getId());
        return price;
    }

    @Override
    public void generateBill(Order o) {
        checkNull(o);
        checkGreter0(o.getId());
        ArrayList<MenuItem> mi = orders.get(o);
        BillFileWriter fw = new BillFileWriter();
        float price = computePrice(o);
        fw.writeBill(o, mi, price);
        checkNull(o);
        checkGreter0(o.getId());
    }

    public int generateOrderID(){
        int max=0;
        for (Order o: orders.keySet()) {
            if(o.getId()>max){
                max = o.getId();
            }
        }
        ++max;
        return max;
    }

    public MenuItem findByName(String str){
        for (MenuItem mi: menu) {
            if(mi.getName().equals(str)){
                return mi;
            }
        }
        return null;
    }

    public Order findByID(int id){
        for (Map.Entry<Order, ArrayList<MenuItem>> o: orders.entrySet()) {
            if(o.getKey().getId() == id){
                return o.getKey();
            }
        }
        return null;
    }
}
