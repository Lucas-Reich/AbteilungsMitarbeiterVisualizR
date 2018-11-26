package AbteilungsMitarbeiterVisualizR.Persistence.SQLite;

import java.sql.*;

public class SQLiteDatabaseHandler {
    private Connection connection = null;

    int executeUpdate(String sql) {
        try {
            Statement stmt = connection.createStatement();

            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            return -1;
        }
    }

    ResultSet executeQuery(String sql) {
        try {
            Statement stmt = connection.createStatement();

            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            return null;
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

        }
    }

    private Connection establishConnection() {
        try {
            return DriverManager.getConnection(getUrl());
        } catch (SQLException e) {
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
}
