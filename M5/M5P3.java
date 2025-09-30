//202509029 SDEV200 Java - Module 5, Programming Assignment 3, Chapter 16 - exercise 16.17

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class M5P3 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a text label
        Text sampleText = new Text("Sample Text");
        sampleText.setStyle("-fx-font-size: 30;");

        // Create sliders
        Slider redSlider = createSlider();
        Slider greenSlider = createSlider();
        Slider blueSlider = createSlider();
        Slider opacitySlider = createSlider();
        opacitySlider.setValue(1); // default opacity to 100%

        // Update text color when sliders change
        ChangeListener<Number> colorChangeListener = (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            sampleText.setFill(new Color(
                    redSlider.getValue(),
                    greenSlider.getValue(),
                    blueSlider.getValue(),
                    opacitySlider.getValue()
            ));
        };

        redSlider.valueProperty().addListener(colorChangeListener);
        greenSlider.valueProperty().addListener(colorChangeListener);
        blueSlider.valueProperty().addListener(colorChangeListener);
        opacitySlider.valueProperty().addListener(colorChangeListener);

        // Layout
        VBox root = new VBox(10);
        root.getChildren().addAll(sampleText, redSlider, greenSlider, blueSlider, opacitySlider);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Color Selector");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Slider createSlider() {
        Slider slider = new Slider(0, 1, 0); // min=0, max=1, initial=0
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(0.25);
        slider.setBlockIncrement(0.01);
        return slider;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
