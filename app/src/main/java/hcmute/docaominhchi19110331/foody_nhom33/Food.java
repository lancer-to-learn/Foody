package hcmute.docaominhchi19110331.foody_nhom33;

public class Food {
    private int id;
    private int id_res;
    private String name;
    private int price;
    private int image;

    public Food(int id, int id_res, String name, int price, int image) {
        this.id = id;
        this.id_res = id_res;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_res() {
        return id_res;
    }

    public void setId_res(int id_res) {
        this.id_res = id_res;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
