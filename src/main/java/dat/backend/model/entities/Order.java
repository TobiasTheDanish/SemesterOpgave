package dat.backend.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private User user;
    private String status;
    private int width;
    private int height;
    private int length;
    private List<Material> materials;

    public Order(User user, String status, int width, int height, int length) {
        this.id = 0;
        this.user = user;
        this.status = status;
        this.width = width;
        this.height = height;
        this.length = length;
        this.materials = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }
}
