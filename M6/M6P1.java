//20251013 SDEV200 Java - Module 6, Programming Assignment 1, Chapter 34 - exercise 34.1


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;

public class M6P1 extends Application {
    // MySQL info
    static final String URL = "jdbc:mysql://localhost:3306/";
    static final String USER = "homework";
    static final String PASSWORD = "homework";
    static final String DB_NAME = "StaffDB";

    private Connection conn;

    private TextField idField = new TextField();
    private TextField lastNameField = new TextField();
    private TextField firstNameField = new TextField();
    private TextField miField = new TextField();
    private TextField addressField = new TextField();
    private TextField cityField = new TextField();
    private TextField stateField = new TextField();
    private TextField telephoneField = new TextField();
    private TextField emailField = new TextField();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect and create DB if not exists
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            conn.close();

            // Connect to database
            conn = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);

            // Create Staff table if not exists
            String createTableSQL = """
                    CREATE TABLE IF NOT EXISTS Staff (
                        id CHAR(9) NOT NULL,
                        lastName VARCHAR(15),
                        firstName VARCHAR(15),
                        mi CHAR(1),
                        address VARCHAR(20),
                        city VARCHAR(20),
                        state CHAR(2),
                        telephone CHAR(10),
                        email VARCHAR(40),
                        PRIMARY KEY (id)
                    )
                    """;
            stmt = conn.createStatement();
            stmt.executeUpdate(createTableSQL);

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Database Error", e.getMessage());
        }

        // Create JavaFX GUI
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        // Labels and fields
        grid.add(new Label("ID:"), 0, 0); grid.add(idField, 1, 0);
        grid.add(new Label("Last Name:"), 0, 1); grid.add(lastNameField, 1, 1);
        grid.add(new Label("First Name:"), 0, 2); grid.add(firstNameField, 1, 2);
        grid.add(new Label("MI:"), 0, 3); grid.add(miField, 1, 3);
        grid.add(new Label("Address:"), 0, 4); grid.add(addressField, 1, 4);
        grid.add(new Label("City:"), 0, 5); grid.add(cityField, 1, 5);
        grid.add(new Label("State:"), 0, 6); grid.add(stateField, 1, 6);
        grid.add(new Label("Telephone:"), 0, 7); grid.add(telephoneField, 1, 7);
        grid.add(new Label("Email:"), 0, 8); grid.add(emailField, 1, 8);

        // Buttons
        Button viewButton = new Button("View");
        Button insertButton = new Button("Insert");
        Button updateButton = new Button("Update");

        grid.add(viewButton, 0, 9);
        grid.add(insertButton, 1, 9);
        grid.add(updateButton, 2, 9);

        // Button actions
        viewButton.setOnAction(e -> viewStaff());
        insertButton.setOnAction(e -> insertStaff());
        updateButton.setOnAction(e -> updateStaff());

        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setTitle("Staff Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void viewStaff() {
        String sql = "SELECT * FROM Staff WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idField.getText().trim());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lastNameField.setText(rs.getString("lastName"));
                firstNameField.setText(rs.getString("firstName"));
                miField.setText(rs.getString("mi"));
                addressField.setText(rs.getString("address"));
                cityField.setText(rs.getString("city"));
                stateField.setText(rs.getString("state"));
                telephoneField.setText(rs.getString("telephone"));
                emailField.setText(rs.getString("email"));
            } else {
                showAlert("Not Found", "Staff ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", e.getMessage());
        }
    }

    private void insertStaff() {
        String sql = """
                INSERT INTO Staff (id,lastName,firstName,mi,address,city,state,telephone,email)
                VALUES (?,?,?,?,?,?,?,?,?)
                """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idField.getText().trim());
            ps.setString(2, lastNameField.getText().trim());
            ps.setString(3, firstNameField.getText().trim());
            ps.setString(4, miField.getText().trim());
            ps.setString(5, addressField.getText().trim());
            ps.setString(6, cityField.getText().trim());
            ps.setString(7, stateField.getText().trim());
            ps.setString(8, telephoneField.getText().trim());
            ps.setString(9, emailField.getText().trim());
            ps.executeUpdate();
            showAlert("Success", "Inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", e.getMessage());
        }
    }

    private void updateStaff() {
        String sql = """
                UPDATE Staff SET lastName=?, firstName=?, mi=?, address=?, city=?, state=?, telephone=?, email=?
                WHERE id=?
                """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, lastNameField.getText().trim());
            ps.setString(2, firstNameField.getText().trim());
            ps.setString(3, miField.getText().trim());
            ps.setString(4, addressField.getText().trim());
            ps.setString(5, cityField.getText().trim());
            ps.setString(6, stateField.getText().trim());
            ps.setString(7, telephoneField.getText().trim());
            ps.setString(8, emailField.getText().trim());
            ps.setString(9, idField.getText().trim());
            int rows = ps.executeUpdate();
            if (rows > 0) showAlert("Success", "Updated successfully.");
            else showAlert("Not Found", "Staff ID not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
