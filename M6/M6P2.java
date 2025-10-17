//20251013 SDEV200 Java - Module 6, Programming Assignment 2, Chapter 35 - exercise 35.1
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class M6P2 extends Application {

    private Connection connection;
    private TextArea taResults = new TextArea();

    @Override
    public void start(Stage primaryStage) {

        Button btnConnect = new Button("Connect to Database");
        Button btnRunTest = new Button("Run Batch Insert Test");
        btnRunTest.setDisable(true);

        // Connect button opens DB panel dialog
        btnConnect.setOnAction(e -> showDBConnectionDialog(primaryStage, btnRunTest));

        VBox root = new VBox(15, btnConnect, btnRunTest, taResults);
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.TOP_CENTER);

        // Run batch insert test
        btnRunTest.setOnAction(e -> runBatchTest());

        Scene scene = new Scene(root, 500, 400);
        primaryStage.setTitle("Exercise 35.1 - Batch Update Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showDBConnectionDialog(Stage owner, Button btnRunTest) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(owner);
        dialog.setTitle("Connect to Database");

        // Input fields
        TextField tfHost = new TextField("localhost");
        TextField tfPort = new TextField("3306");
        TextField tfDatabase = new TextField("test");
        TextField tfUsername = new TextField("homework");

        PasswordField pfPassword = new PasswordField();
        pfPassword.setPromptText("Enter password");

        TextField tfPasswordVisible = new TextField();
        tfPasswordVisible.setPromptText("Enter password");
        tfPasswordVisible.setVisible(false);
        tfPasswordVisible.setManaged(false);
        tfPasswordVisible.textProperty().bindBidirectional(pfPassword.textProperty());

        Button btnTogglePassword = new Button("Show");
        btnTogglePassword.setOnAction(e -> {
            boolean showing = tfPasswordVisible.isVisible();
            tfPasswordVisible.setVisible(!showing);
            tfPasswordVisible.setManaged(!showing);
            pfPassword.setVisible(showing);
            pfPassword.setManaged(showing);
            btnTogglePassword.setText(showing ? "Show" : "Hide");
        });

        HBox passwordBox = new HBox(5, pfPassword, tfPasswordVisible, btnTogglePassword);
        passwordBox.setAlignment(Pos.CENTER_LEFT);

        Button btnConnectDB = new Button("Connect");
        Label lblStatus = new Label();

        btnConnectDB.setOnAction(ev -> {
            String url = "jdbc:mysql://" + tfHost.getText() + ":" + tfPort.getText() + "/" + tfDatabase.getText();
            String user = tfUsername.getText();
            String pass = pfPassword.isVisible() ? pfPassword.getText() : tfPasswordVisible.getText();
            try {
                connection = DriverManager.getConnection(url, user, pass);
                lblStatus.setText("✅ Connected successfully!");
                btnRunTest.setDisable(false);
                dialog.close(); // close the dialog when successful
            } catch (Exception ex) {
                lblStatus.setText("❌ Connection failed: " + ex.getMessage());
            }
        });

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        grid.add(new Label("Host:"), 0, 0);       grid.add(tfHost, 1, 0);
        grid.add(new Label("Port:"), 0, 1);       grid.add(tfPort, 1, 1);
        grid.add(new Label("Database:"), 0, 2);   grid.add(tfDatabase, 1, 2);
        grid.add(new Label("Username:"), 0, 3);   grid.add(tfUsername, 1, 3);
        grid.add(new Label("Password:"), 0, 4);   grid.add(passwordBox, 1, 4);
        grid.add(btnConnectDB, 1, 5);             grid.add(lblStatus, 1, 6);

        VBox dialogRoot = new VBox(10, grid);
        dialogRoot.setPadding(new Insets(10));

        dialog.setScene(new Scene(dialogRoot, 400, 300));
        dialog.showAndWait();
    }

    private void runBatchTest() {
        if (connection == null) {
            taResults.appendText("Not connected to database.\n");
            return;
        }

        taResults.appendText("\n--- Running Batch Insert Test ---\n");

        try (PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO Temp (num1, num2, num3) VALUES (?, ?, ?)")) {

            connection.createStatement().executeUpdate("DELETE FROM Temp");

            // Test without batch
            long start = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                stmt.setDouble(1, Math.random());
                stmt.setDouble(2, Math.random());
                stmt.setDouble(3, Math.random());
                stmt.executeUpdate();
            }
            long end = System.currentTimeMillis();
            taResults.appendText("Without batch: " + (end - start) + " ms\n");

            // Clear again
            connection.createStatement().executeUpdate("DELETE FROM Temp");

            // Test with batch
            start = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                stmt.setDouble(1, Math.random());
                stmt.setDouble(2, Math.random());
                stmt.setDouble(3, Math.random());
                stmt.addBatch();
            }
            stmt.executeBatch();
            end = System.currentTimeMillis();
            taResults.appendText("With batch: " + (end - start) + " ms\n");

        } catch (Exception ex) {
            taResults.appendText("Error: " + ex.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
