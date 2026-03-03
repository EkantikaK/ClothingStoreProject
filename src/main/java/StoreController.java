import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import java.util.List;

public class StoreController {

    @FXML
    private WebView myBrowser; // This links to the FXML container

    // Bring in your existing backend engines!
    private InventoryStore store = new InventoryStore();
    private ShoppingCart cart = new ShoppingCart();
    private Checkout checkout = new Checkout();

    @FXML
    public void initialize() {
        WebEngine engine = myBrowser.getEngine();
        // 1. Load the HTML file (we will put index.html in the resources folder)
        String htmlPath = getClass().getResource("/public/index.html").toExternalForm();
        engine.load(htmlPath);

        // 2. Inject the Java logic into the Web interface
        engine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == javafx.concurrent.Worker.State.SUCCEEDED) {
                JSObject window = (JSObject) engine.executeScript("window");
                // This creates "window.javaBackend" for the JavaScript to talk to!
                window.setMember("javaBackend", new JavaBridge());
            }
        });
    }

    // --- THE BRIDGE ---
    // Any method inside this public class can be clicked/triggered by the HTML webpage!
    public class JavaBridge {

        // Triggered when user clicks "Add to Cart" on the website
        public void addItemToCart(String itemId) {
            System.out.println("Web button clicked! Adding item: " + itemId);
            List<ClothingItem> allItems = store.getAllItems();
            for (ClothingItem item : allItems) {
                if (item.getId().equals(itemId)) {
                    if (cart.addItem(item)) {
                        System.out.println("Success! Cart total is now: " + cart.calculateTotalCost());
                    } else {
                        System.out.println("Failed: Item out of stock.");
                    }
                    break;
                }
            }
        }

        // Triggered when user clicks "Checkout" on the website
        public void processWebCheckout(String promoCode) {
            System.out.println("Web checkout triggered!");
            boolean success = checkout.processCheckout(cart, promoCode);
            if (success) {
                System.out.println("Checkout complete. Receipt generated!");
            }
        }
    }
}