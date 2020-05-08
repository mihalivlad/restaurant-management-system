package businessLayer;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem {

    private ArrayList<MenuItem> composition;

    public CompositeProduct(ArrayList<MenuItem> composition, String name) {
        this.composition = composition;
        setName(name);
        setBase(false);
    }

    @Override
    public float computePrice() {
        float price = 0;
        for (MenuItem mi: composition) {
            price+=mi.computePrice();
        }
        return price;
    }

    @Override
    public String toString() {
        return "Menu item: " +
                "name=" + getName() +
                ", price=" + computePrice();
    }

    public void update(MenuItem item){
        for (MenuItem mi: composition) {
            if(mi.isBase() && item.getName().equals(mi.getName())){
                mi.setPrice(item.getPrice());
            }else if(!mi.isBase()){
                mi.update(item);
            }
        }
    }
    public String getBaseProducts(){
        String str="";
        for (MenuItem mi: composition) {
            str+=mi.getName()+";";
        }
        return str;
    }
}
