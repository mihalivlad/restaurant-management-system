package businessLayer;

public class BaseProduct extends MenuItem {

    public BaseProduct(String name, float price) {
        setName(name);
        setPrice(price);
        setBase(true);
    }

    @Override
    public float computePrice() {
        return getPrice();
    }

    @Override
    public String toString() {
        return "Menu item: " +
                "name=" + getName() +
                ", price=" + getPrice();
    }

    @Override
    public String getBaseProducts(){
        return "base product";
    }

    @Override
    protected void update(MenuItem item) {

    }
}
