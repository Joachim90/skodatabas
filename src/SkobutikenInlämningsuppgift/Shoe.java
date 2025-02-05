package SkobutikenInlämningsuppgift;

public class Shoe extends Entity{

    private String brand;
    private int price;
    private String color;
    private int size;
    private int stock;

    public Shoe(int id, int size, String color, String brand, int price, int stock) {
        super(id);
        this.size = size;
        this.color = color;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
    }

    public int getShoeId() {
        return getId();
    }

    public String getBrand() {
        return brand;
    }


    public String getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return brand + ", " + color + ", stl." + size + ", " + price + "kr," + "   Antal i lager: " + stock;
    }
}
