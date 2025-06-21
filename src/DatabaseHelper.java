/**
 * 
Password Manager - DatabaseHelper.java
 * 
This class manages all database operations using SQLite, including initializing the database,
inserting, retrieving, searching, and deleting stored credentials.
 * 
 */

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:sqlite:./passwords.db";

    public static void initDB() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "CREATE TABLE IF NOT EXISTS credentials (" +
                         "id INTEGER PRIMARY KEY," +
                         "site TEXT," +
                         "username TEXT," +
                         "password TEXT)";
            conn.createStatement().execute(sql);
        } 
	catch (SQLException e) 
	{
            e.printStackTrace();
        }
    }

    public static void insertCredential(String site, String username, String encryptedPassword) {
        String sql = "INSERT INTO credentials(site, username, password) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, site);
            pstmt.setString(2, username);
            pstmt.setString(3, encryptedPassword);
            pstmt.executeUpdate();
        } 
	catch (SQLException e) 
	{
            e.printStackTrace();
        }
    }

	public static ArrayList<String[]> getAllCredentials() {
    ArrayList<String[]> credentials = new ArrayList<>();
    String sql = "SELECT site, username, password FROM credentials";

    try (Connection conn = DriverManager.getConnection(DB_URL);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            String site = rs.getString("site");
            String username = rs.getString("username");
            String password = rs.getString("password");
            credentials.add(new String[]{site, username, password});
        }
    } 
	catch (SQLException e) 
	{
        e.printStackTrace();
    }
    return credentials;
}

public static ArrayList<String[]> searchCredentials(String keyword) {
    ArrayList<String[]> results = new ArrayList<>();
    String sql = "SELECT site, username, password FROM credentials WHERE site LIKE ?";

    try (Connection conn = DriverManager.getConnection(DB_URL);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, "%" + keyword + "%");
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            String site = rs.getString("site");
            String username = rs.getString("username");
            String password = rs.getString("password");
            results.add(new String[]{site, username, password});
        }
    } 
	catch (SQLException e) 
	{
        e.printStackTrace();
    }
    return results;
    }

	public static void deleteCredential(String site, String username) {
    String sql = "DELETE FROM credentials WHERE site = ? AND username = ?";
    try (Connection conn = DriverManager.getConnection(DB_URL);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, site);
        pstmt.setString(2, username);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
	}
}