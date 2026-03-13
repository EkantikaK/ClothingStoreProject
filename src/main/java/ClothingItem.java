public class ClothingItem {

    private String id;
    private String name;
    private String category;
    private double price;

    // Removed stockQuantity from the constructor
    public ClothingItem(String id, String name, String category, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }

    // Removed isAvailable(), reduceStock(), and getStockQuantity()

    @Override
    public String toString() {
        return name + " - ₹" + String.format("%.2f", price);
    }
}