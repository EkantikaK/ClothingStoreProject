import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.net.URL;

public class StoreController {

    // This perfectly matches the fx:id you set in Scene Builder!
    @FXML
    private WebView myBrowser;

    @FXML
    public void initialize() {
        // 1. Get the "engine" that runs the browser
        WebEngine engine = myBrowser.getEngine();

        // 2. Find your HTML file inside the resources folder
        URL targetUrl = getClass().getResource("/index.html");

        // 3. Tell the browser to load it!
        if (targetUrl != null) {
            engine.load(targetUrl.toExternalForm());
        } else {
            System.out.println("Error: Could not find index.html. Make sure it is in the resources folder!");
        }
    }
}