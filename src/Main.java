/*
 *
Password Manager - Main.java
 *
This is the entry point of the application. It launches the JavaFX GUI for the password manager. 
 */

import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        DatabaseHelper.initDB();
        Application.launch(PasswordManagerGUI.class, args);
    }
}
