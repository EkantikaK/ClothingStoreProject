import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class App {
    public static void main(String[] args) {

        // Initialize the Web Server
        Javalin app = Javalin.create(config -> {

            // 1. CORS Configuration (Allows frontend fetch() calls)
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(it -> {
                    it.anyHost();
                });
            });

            // 2. Host the Frontend Files
            // Tells Javalin: "When a user goes to http://localhost:8080, serve
            // the static files located in the 'public' folder inside 'src/main/resources'."
            config.staticFiles.add("/public", Location.CLASSPATH);

        }).start(8080);

        // Initialize Core Logic and Controllers
        CartController cartController = new CartController();

        // Map REST API Endpoints
        app.post("/api/cart/add", cartController::addToCart);
        app.post("/api/checkout", cartController::processCheckout);

        System.out.println("Java E-Commerce Server Started!");
        System.out.println("Open your browser and navigate to: http://localhost:8080");
    }
}