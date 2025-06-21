/*
 * 
Password Manager - PasswordManagerGUI.java
 * 
This class builds the graphical user interface using JavaFX. It handles user input, UI controls,
and connects to the backend functions like viewing, adding, and removing credentials.
 * 
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PasswordManagerGUI extends Application {

    private ListView<String> credentialsList = new ListView<>();

    @Override
    public void start(Stage primaryStage) {
        DatabaseHelper.initDB();

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        TextField siteField = new TextField();
        siteField.setPromptText("Site");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button saveBtn = new Button("Add Credential");

        saveBtn.setOnAction(e -> {
            try {
                String site = siteField.getText();
                String username = usernameField.getText();
                String password = EncryptionUtil.encrypt(passwordField.getText());

                DatabaseHelper.insertCredential(site, username, password);
                siteField.clear();
                usernameField.clear();
                passwordField.clear();
                viewAllCredentials();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button viewBtn = new Button("View All Credentials");
        viewBtn.setOnAction(e -> viewAllCredentials());

        Button removeBtn = new Button("Remove Selected");
        removeBtn.setOnAction(e -> {
            String selected = credentialsList.getSelectionModel().getSelectedItem();
            if (selected != null && selected.split("\\|").length >= 2) {
                String[] parts = selected.split("\\|");
                String site = parts[0].trim();
                String username = parts[1].trim();
                DatabaseHelper.deleteCredential(site, username);
                viewAllCredentials(); // Refresh the list
            }
        });

        layout.getChildren().addAll(siteField, usernameField, passwordField, saveBtn, viewBtn, removeBtn, credentialsList);

        Scene scene = new Scene(layout, 400, 450);
        primaryStage.setTitle("Password Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void viewAllCredentials() {
        credentialsList.getItems().clear();
        try {
            var creds = DatabaseHelper.getAllCredentials();
            for (String[] c : creds) {
                String decrypted = EncryptionUtil.decrypt(c[2]);
                String entry = c[0] + " | " + c[1] + " | " + decrypted;
                credentialsList.getItems().add(entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
