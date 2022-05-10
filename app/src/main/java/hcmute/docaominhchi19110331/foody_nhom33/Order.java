package hcmute.docaominhchi19110331.foody_nhom33;

public class Order {
    private int id;
    private int id_food;
    private int quantity;
    private int price_total;
    private int id_user;

    public Order(int id, int id_food, int quantity, int price_total, int id_user) {
        this.id = id;
        this.id_food = id_food;
        this.quantity = quantity;
        this.price_total = price_total;
        this.id_user = id_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_food() {
        return id_food;
    }

    public void setId_food(int id_food) {
        this.id_food = id_food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice_total() {
        return price_total;
    }

    public void setPrice_total(int price_total) {
        this.price_total = price_total;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
