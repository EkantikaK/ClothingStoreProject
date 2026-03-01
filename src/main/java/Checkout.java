import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Checkout {

    public boolean processCheckout(ShoppingCart cart, String promoCode) {
        if (cart.getItemCount() == 0) {
            System.out.println("Cart is empty. Cannot checkout.");
            return false;
        }

        double subtotal = cart.calculateTotalCost();
        double discount = applyPromoCode(promoCode, subtotal);
        double total = subtotal - discount;

        for (ClothingItem item : cart.getCartItems()) {
            item.reduceStock(1);
        }

        generateReceipt(cart.getCartItems(), subtotal, discount, total);
        cart.clearCart();
        return true;
    }

    private double applyPromoCode(String promoCode, double subtotal) {
        if (promoCode != null && promoCode.equalsIgnoreCase("SEMESTER10")) {
            return subtotal * 0.10;
        }
        return 0.0;
    }

    private void generateReceipt(List<ClothingItem> items, double subtotal, double discount, double total) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("receipt.txt"))) {

            writer.println("====================================");
            writer.println("        JAVA CLOTHING STORE         ");
            writer.println("====================================");

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            writer.println("Date: " + dtf.format(now));
            writer.println("------------------------------------");

            for (ClothingItem item : items) {
                // Updated to ₹
                writer.println(item.getName() + " - ₹" + String.format("%.2f", item.getPrice()));
            }

            writer.println("------------------------------------");
            // Updated to ₹
            writer.println("Subtotal: ₹" + String.format("%.2f", subtotal));

            if (discount > 0) {
                // Updated to ₹
                writer.println("Discount (SEMESTER10): -₹" + String.format("%.2f", discount));
            }

            // Updated to ₹
            writer.println("Total: ₹" + String.format("%.2f", total));
            writer.println("====================================");
            writer.println("      Thank you for shopping!       ");

            System.out.println("Receipt generated successfully: receipt.txt");

        } catch (IOException e) {
            System.out.println("Error generating receipt: " + e.getMessage());
        }
    }
}
