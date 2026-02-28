package java;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    // 1. The Cart's Internal Storage
    private List<ClothingItem> cartItems;

    // 2. Constructor
    public ShoppingCart() {
        cartItems = new ArrayList<>();
    }

    // 3. Add Item Logic
    public boolean addItem(ClothingItem item) {
        if (item.isAvailable()) {
            cartItems.add(item);
            return true; // Successfully added
        } else {
            return false; // Failed to add (Out of stock)
        }
    }

    // 4. Remove Item Logic
    public void removeItem(ClothingItem item) {
        cartItems.remove(item);
    }

    // 5. Math Calculation Engine
    public double calculateTotalCost() {
        double total = 0.0;
        for (ClothingItem item : cartItems) {
            total += item.getPrice();
        }
        return total;
    }

    // 6. Retrieve Cart Contents (For GUI)
    public List<ClothingItem> getCartItems() {
        return cartItems;
    }

    // 7. Post-Checkout Cleanup
    public void clearCart() {
        cartItems.clear();
    }

    // 8. Get Item Count (For GUI Cart Icon/Label)
    public int getItemCount() {
        return cartItems.size();
    }
}