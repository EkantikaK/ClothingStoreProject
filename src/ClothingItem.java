public class ClothingItem {

    private String id;
    private String name;
    private String category;
    private double price;
    private int stockQuantity;

    public ClothingItem(String id, String name, String category, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }

    public boolean isAvailable() {
        return stockQuantity > 0;
    }

    public void reduceStock(int amount) {
        if (stockQuantity >= amount) {
            this.stockQuantity -= amount;
        } else {
            System.out.println("Error: Insufficient stock for " + name);
        }
    }

    @Override
    public String toString() {
        return name + " - ₹" + String.format("%.2f", price) + " (Stock: " + stockQuantity + ")";
    }
}
