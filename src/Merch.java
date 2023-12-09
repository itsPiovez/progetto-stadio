public class Merch {
    private String type;
    private String size;
    private String color;
    private double price;

    public Merch(String type, String size, String color, double price) {
        this.type = type;
        this.size = size;
        this.color = color;
        this.price = price;
    }

    // Altri metodi getter e setter come nella versione precedente

    @Override
    public String toString() {
        return "Merch{" +
                "type='" + type + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }

    public String getType()
    {
        return type;
    }

    public String getSize()
    {
        return size;
    }

    public String getColor()
    {
        return color;
    }
}
