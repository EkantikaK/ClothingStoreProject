public class CartItem {

    private ClothingItem item;
    private int quantity;

    public CartItem(ClothingItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public ClothingItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public double getSubtotal() {
        return item.getPrice() * quantity;
    }
}