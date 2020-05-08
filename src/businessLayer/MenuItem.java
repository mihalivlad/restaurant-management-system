package businessLayer;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
    private String name;
    private float price;
    private boolean isBase;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isBase() {
        return isBase;
    }

    public void setBase(boolean base) {
        isBase = base;
    }

    public abstract float computePrice();
    public abstract String toString();
    public abstract String getBaseProducts();

    protected abstract void update(MenuItem item);
}
