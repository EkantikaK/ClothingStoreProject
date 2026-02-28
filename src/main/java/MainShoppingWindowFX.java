package java;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainShopWindowFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        // A simple test label
        Label testLabel = new Label("JavaFX is successfully installed and running!");

        // A basic layout pane
        StackPane root = new StackPane();
        root.getChildren().add(testLabel);

        // The Scene (Window content)
        Scene scene = new Scene(root, 400, 300);

        // The Stage (The actual window)
        primaryStage.setTitle("Java Online Clothing Store - Modern UI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // This specific method launches JavaFX
    }
}
