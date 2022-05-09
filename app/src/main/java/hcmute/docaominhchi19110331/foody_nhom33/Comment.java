package hcmute.docaominhchi19110331.foody_nhom33;

public class Comment {
    private int id;
    private int id_food;
    private int id_user;
    private String content;

    public Comment(int id, int id_food, int id_user, String content) {
        this.id = id;
        this.id_food = id_food;
        this.id_user = id_user;
        this.content = content;
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

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
