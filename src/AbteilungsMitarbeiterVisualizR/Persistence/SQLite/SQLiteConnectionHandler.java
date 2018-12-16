package AbteilungsMitarbeiterVisualizR.Persistence.SQLite;

import AbteilungsMitarbeiterVisualizR.Log;

import java.sql.*;

public class SQLiteConnectionHandler {
    private Connection connection = null;

    int executeUpdate(String sql) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);

            return extractFirstId(stmt);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
            return -1;
        }
    }

    ResultSet executeQuery(String sql) {
        try {
            return connection
                    .createStatement()
                    .executeQuery(sql);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
            return null;
        }
    }

    boolean execute(String sql) {
        try {
            return connection
                    .createStatement()
                    .execute(sql);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
            return false;
        }
    }

    void connect() {
        if (!isConnectionActive())
            connection = establishConnection();
    }

    void disconnect() {
        try {
            if (isConnectionActive())
                connection.close();

        } catch (SQLException e) {

            Log.error("Failed to disconnect from the database.", e);
        }
    }

    private Connection establishConnection() {
        try {
            return DriverManager.getConnection(getUrl());
        } catch (SQLException e) {
            Log.error("Could not establish database connection", e);

            return null;
        }
    }

    private boolean isConnectionActive() {
        try {
            return null != connection && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    private String getUrl() {
        return String.format("jdbc:sqlite:%s/%s", getDatabaseDir(), SQLiteHelper.DATABASE_NAME);
    }

    private String getDatabaseDir() {
        return System.getProperty("user.dir") + "/assets/SQLite";
    }

    private int extractFirstId(Statement stmt) {
        try {

            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();

            return (int) keys.getLong(1);
        } catch (SQLException e) {

            return -1;
        }
    }
}
