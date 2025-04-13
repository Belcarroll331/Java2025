package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MetricConverterGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Metric Converter");

        // UI Elements
        Label inputLabel = new Label("Enter value:");
        TextField inputField = new TextField();

        Label fromLabel = new Label("From:");
        ComboBox<String> fromUnit = new ComboBox<>();
        fromUnit.getItems().addAll("Meters", "Kilometers", "Centimeters");
        fromUnit.getSelectionModel().selectFirst();

        Label toLabel = new Label("To:");
        ComboBox<String> toUnit = new ComboBox<>();
        toUnit.getItems().addAll("Meters", "Kilometers", "Centimeters");
        toUnit.getSelectionModel().selectFirst();

        Button convertButton = new Button("Convert");
        Label resultLabel = new Label("Result: ");

        convertButton.setOnAction(e -> {
            try {
                double inputValue = Double.parseDouble(inputField.getText());
                String from = fromUnit.getValue();
                String to = toUnit.getValue();
                double result = convert(inputValue, from, to);
                resultLabel.setText("Result: " + result + " " + to);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter a valid number.");
            }
        });

        // Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(inputLabel, 0, 0);
        grid.add(inputField, 1, 0);
        grid.add(fromLabel, 0, 1);
        grid.add(fromUnit, 1, 1);
        grid.add(toLabel, 0, 2);
        grid.add(toUnit, 1, 2);
        grid.add(convertButton, 0, 3);
        grid.add(resultLabel, 1, 3);

        Scene scene = new Scene(grid, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private double convert(double value, String from, String to) {
        double inMeters = switch (from) {
            case "Kilometers" -> value * 1000;
            case "Centimeters" -> value / 100;
            default -> value;
        };

        return switch (to) {
            case "Kilometers" -> inMeters / 1000;
            case "Centimeters" -> inMeters * 100;
            default -> inMeters;
        };
    }

    public static void main(String[] args) {
        launch(args);
    }
}
