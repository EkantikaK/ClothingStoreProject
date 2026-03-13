import io.javalin.http.Context;
import java.util.Map;
import java.util.stream.Collectors;

public class CartController {

    private final InventoryStore inventoryStore;
    private final ShoppingCart shoppingCart;
    private final Checkout checkout;

    public CartController(InventoryStore inventoryStore,
                          ShoppingCart shoppingCart,
                          Checkout checkout) {
        this.inventoryStore = inventoryStore;
        this.shoppingCart = shoppingCart;
        this.checkout = checkout;
    }

    public static class AddItemRequest {
        public String itemId;
    }

    public static class CheckoutRequest {
        public String promoCode;
    }

    // ================= ADD TO CART =================

    public void addToCart(Context ctx) {

        AddItemRequest request = ctx.bodyAsClass(AddItemRequest.class);

        if (request == null || request.itemId == null) {
            ctx.status(400).json(Map.of("error", "Item ID required"));
            return;
        }

        ClothingItem item = inventoryStore.getItemById(request.itemId);

        if (item == null) {
            ctx.status(404).json(Map.of("error", "Item not found"));
            return;
        }

        // We simply add the item now! No need to check for a boolean response.
        shoppingCart.addItem(item);

        ctx.json(Map.of("message", "Item added successfully"));
    }

    // ================= GET CART =================

    public void getCart(Context ctx) {

        var cartItems = shoppingCart.getItems()
                .stream()
                .map(ci -> Map.of(
                        "id", ci.getItem().getId(),
                        "name", ci.getItem().getName(),
                        "price", ci.getItem().getPrice(),
                        "quantity", ci.getQuantity(),
                        "subtotal", ci.getSubtotal()
                ))
                .collect(Collectors.toList());

        ctx.json(Map.of(
                "items", cartItems,
                "total", shoppingCart.getTotal()
        ));
    }
// ================= CHECKOUT =================

    public void processCheckout(Context ctx) {

        // Removed the "" argument
        boolean success = checkout.processCheckout(shoppingCart);

        if (!success) {
            ctx.status(400).json(Map.of("error", "Cart is empty"));
            return;
        }

        shoppingCart.clear();

        ctx.json(Map.of("message", "Checkout successful"));
    }
}