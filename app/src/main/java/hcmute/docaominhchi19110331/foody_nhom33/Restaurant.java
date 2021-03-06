package hcmute.docaominhchi19110331.foody_nhom33;

public class Restaurant {
    private String name;
    private String address;
    private int image;
    private int id;

    public Restaurant(int id, String name, String address, int image) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.image = image;
    }
    public int getId() {
        return id;
    }

    public void setId() { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
