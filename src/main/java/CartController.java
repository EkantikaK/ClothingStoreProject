import io.javalin.http.Context;

public class CartController {

    // Initialize your existing core classes
    private InventoryStore inventoryStore = new InventoryStore();
    private ShoppingCart shoppingCart = new ShoppingCart();
    private Checkout checkout = new Checkout();

    // Data Transfer Objects (DTOs) for Jackson to parse incoming JSON requests
    public static class AddItemRequest {
        public String itemId;
    }

    public static class CheckoutRequest {
        public String promoCode;
    }

    /**
     * POST /api/cart/add
     * Expected JSON Body: { "itemId": "S01" }
     */
    public void addToCart(Context ctx) {
        AddItemRequest request = ctx.bodyAsClass(AddItemRequest.class);
        String requestedId = request.itemId;

        // Search for the item in the inventory
        ClothingItem itemToAdd = null;
        for (ClothingItem item : inventoryStore.getAllItems()) {
            if (item.getId().equalsIgnoreCase(requestedId)) {
                itemToAdd = item;
                break;
            }
        }

        if (itemToAdd == null) {
            ctx.status(404).json("Error: Item code '" + requestedId + "' not found in inventory.");
            return;
        }

        // Attempt to add the item to the cart
        boolean isAdded = shoppingCart.addItem(itemToAdd);
        if (isAdded) {
            ctx.status(200).json("Success: " + itemToAdd.getName() + " added to the cart.");
        } else {
            ctx.status(400).json("Error: " + itemToAdd.getName() + " is currently out of stock.");
        }
    }

    /**
     * POST /api/checkout
     * Expected JSON Body (Optional): { "promoCode": "SEMESTER10" }
     */
    public void processCheckout(Context ctx) {
        CheckoutRequest request = null;
        try {
            // Attempt to parse a promo code if the frontend sent one
            request = ctx.bodyAsClass(CheckoutRequest.class);
        } catch (Exception e) {
            // Proceed without promo code if body is empty or invalid
        }

        String promo = (request != null) ? request.promoCode : "";

        boolean isSuccessful = checkout.processCheckout(shoppingCart, promo);
        if (isSuccessful) {
            ctx.status(200).json("Success: Checkout complete. Receipt generated locally.");
        } else {
            ctx.status(400).json("Error: Checkout failed. The shopping cart is empty.");
        }
    }
}