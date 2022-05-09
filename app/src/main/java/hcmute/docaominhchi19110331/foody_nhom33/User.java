package hcmute.docaominhchi19110331.foody_nhom33;

public class User {
    private int id;
    private String email;
    private String password;
    private String address;
    private String name;
    private String sdt;

    public User(int id, String email, String password, String address, String name, String sdt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.address = address;
        this.name = name;
        this.sdt = sdt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
