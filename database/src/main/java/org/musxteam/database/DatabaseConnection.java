package org.musxteam.database;

import java.sql.*;

public class DatabaseConnection {
    private Statement statement;
    private Connection connection;

    private static final String DB_URL = "jdbc:sqlite:db.sqlite3";
    private static final DatabaseConnection instance = new DatabaseConnection();

    public DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL);
            statement = this.connection.createStatement();
        }
        catch (SQLException ex){
            ex.printStackTrace(System.out);
        }
    }

    public void executeUpdate(String update) {
        try { statement.executeUpdate(update); }
        catch (SQLException ex) { ex.printStackTrace(System.out); }
    }
    public ResultSet executeQuery(String query) throws SQLException {
        return statement.executeQuery(query);
    }

    public static DatabaseConnection getInstance() {
        return instance;
    }
}
