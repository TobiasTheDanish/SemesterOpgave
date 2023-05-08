package dat.backend.model.entities;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private User user;
    private int status;
    private int width;
    private int height;
    private int length;
    private List<Pair<Material, Integer>> materials;

    public Order(User user, int status, int width, int height, int length) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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

    public List<Pair<Material, Integer>> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Pair<Material, Integer>> materials) {
        this.materials = materials;
    }
}
