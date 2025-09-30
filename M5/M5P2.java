import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class M5P2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a circle
        Circle circle = new Circle(50); // radius 50
        circle.setFill(Color.WHITE);

        // Change color on mouse pressed
        circle.setOnMousePressed(e -> circle.setFill(Color.BLACK));

        // Change color on mouse released
        circle.setOnMouseReleased(e -> circle.setFill(Color.WHITE));

        // Create layout and set background color
        StackPane root = new StackPane(circle);
        root.setStyle("-fx-background-color: blue;");

        // Create scene and set stage
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("Circle Color Change");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
