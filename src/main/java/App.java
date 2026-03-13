import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class App {

    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public", Location.CLASSPATH);
        }).start(8080);

        InventoryStore inventoryStore = new InventoryStore();
        ShoppingCart shoppingCart = new ShoppingCart();
        Checkout checkout = new Checkout();

        CartController cartController =
                new CartController(inventoryStore, shoppingCart, checkout);

        app.post("/api/cart/add", cartController::addToCart);
        app.get("/api/cart", cartController::getCart);
        app.post("/api/checkout", cartController::processCheckout);

        System.out.println("Torq server running at http://localhost:8080");
    }
}