import java.util.*;

public class ShoppingCart {

    private Map<String, CartItem> items = new LinkedHashMap<>();

    // Notice we no longer return a boolean, because adding to cart can't fail due to stock!
    public void addItem(ClothingItem item) {

        CartItem existing = items.get(item.getId());

        if (existing == null) {
            items.put(item.getId(), new CartItem(item, 1));
        } else {
            existing.incrementQuantity();
        }

        // Removed the item.reduceStock(1) call
    }

    public Collection<CartItem> getItems() {
        return items.values();
    }

    public double getTotal() {
        return items.values()
                .stream()
                .mapToDouble(CartItem::getSubtotal)
                .sum();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }
}