import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 1. Locate the FXML file in your resources folder
        // NOTE: If you named your FXML file something else, change "StoreFront.fxml" here!
        URL fxmlLocation = getClass().getResource("/StoreFront.fxml");

        if (fxmlLocation == null) {
            System.out.println("Error: Could not find the FXML file. Make sure it is in the 'resources' folder!");
            return;
        }

        // 2. Load the FXML file (which holds your WebView container)
        Parent root = FXMLLoader.load(fxmlLocation);

        // 3. Set the window size (1280x800 is a great wide ratio for your grid)
        Scene scene = new Scene(root, 1280, 800);

        // 4. Configure the Desktop Window
        primaryStage.setTitle("Torq | High-End Athleisure");
        primaryStage.setScene(scene);

        // Optional: This line forces the window to open maximized if you prefer
        // primaryStage.setMaximized(true);

        primaryStage.show();
    }

    public static void main(String[] args) {
        // This is the engine that actually boots up the JavaFX application
        launch(args);
    }
}
