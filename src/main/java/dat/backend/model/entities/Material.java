package dat.backend.model.entities;

public class Material {
    private int id;
    private String name;
    private int width;
    private int height;
    private int length;
    private String description;
    private double pricePrMeter;

    public Material(String name, int width, int height, int length, String description) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.length = length;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPricePrMeter() {
        return pricePrMeter;
    }

    public void setPricePrMeter(double pricePrMeter) {
        this.pricePrMeter = pricePrMeter;
    }
}
