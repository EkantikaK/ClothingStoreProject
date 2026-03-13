import java.util.Collection;

public class Checkout {

    // Removed promoCode parameter
    public boolean processCheckout(ShoppingCart cart) {

        if (cart.isEmpty()) {
            return false;
        }

        double total = cart.getTotal();

        // Removed all discount logic

        // Pass only what is needed to the receipt
        generateReceipt(cart.getItems(), total);

        cart.clear();

        return true;
    }

    // ================= RECEIPT GENERATION =================

    // Removed subtotal and discount parameters
    private void generateReceipt(Collection<CartItem> items, double total) {

        System.out.println("\n=========== TORQ RECEIPT ===========");

        for (CartItem cartItem : items) {
            System.out.println(
                    cartItem.getItem().getName() +
                            "  x" + cartItem.getQuantity() +
                            "  = ₹" + String.format("%.2f", cartItem.getSubtotal())
            );
        }

        System.out.println("-------------------------------------");
        // Removed subtotal and discount print lines
        System.out.println("Total:    ₹" + String.format("%.2f", total));
        System.out.println("=====================================\n");
    }
}