import java.util.Collection;

public class Checkout {

    public boolean processCheckout(ShoppingCart cart, String promoCode) {

        // If cart is empty, fail checkout
        if (cart.isEmpty()) {
            return false;
        }

        double subtotal = cart.getTotal();
        double discount = 0.0;

        // Simple promo code logic (optional)
        if (promoCode != null && promoCode.equalsIgnoreCase("TORQ2026")) {
            discount = subtotal * 0.10; // 10% discount
        }

        double total = subtotal - discount;

        // Generate receipt in console
        generateReceipt(cart.getItems(), subtotal, discount, total);

        // Clear cart after successful checkout
        cart.clear();

        return true;
    }

    // ================= RECEIPT GENERATION =================

    private void generateReceipt(Collection<CartItem> items,
                                 double subtotal,
                                 double discount,
                                 double total) {

        System.out.println("\n=========== TORQ RECEIPT ===========");

        for (CartItem cartItem : items) {
            System.out.println(
                    cartItem.getItem().getName() +
                            "  x" + cartItem.getQuantity() +
                            "  = ₹" + String.format("%.2f", cartItem.getSubtotal())
            );
        }

        System.out.println("-------------------------------------");
        System.out.println("Subtotal: ₹" + String.format("%.2f", subtotal));
        System.out.println("Discount: ₹" + String.format("%.2f", discount));
        System.out.println("Total:    ₹" + String.format("%.2f", total));
        System.out.println("=====================================\n");
    }
}
