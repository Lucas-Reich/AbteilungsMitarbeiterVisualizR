package AbteilungsMitarbeiterVisualizR.Persistence.SQLite;

import java.io.File;
import java.sql.*;

public class SQLiteHelper {
    private static final String DATABASE_NAME = "abteilungsMitarbeiterVisualizR.db";

    static final String TABLE_DEPARTMENTS = "departments";
    static final String DEPARTMENT_COL_ID = "id";
    static final String DEPARTMENT_COL_NAME = "name";
    private static final String CREATE_DEPARTMENTS_TABLE = ""
            + "CREATE TABLE IF NOT EXISTS "
            + TABLE_DEPARTMENTS + "( "
            + DEPARTMENT_COL_ID + " INTEGER PRIMARY KEY, "
            + DEPARTMENT_COL_NAME + " TEXT NOT NULL"
            + ")";

    static final String TABLE_EMPLOYEES = "employee";
    static final String EMPLOYEE_COL_ID = "id";
    static final String EMPLOYEE_COL_NAME = "name";
    static final String EMPLOYEE_COL_DEPARTMENT_ID = "department_id";
    private static final String CREATE_EMPLOYEE_TABLE = ""
            + "CREATE TABLE IF NOT EXISTS "
            + TABLE_EMPLOYEES + "( "
            + EMPLOYEE_COL_ID + " INTEGER PRIMARY KEY, "
            + EMPLOYEE_COL_NAME + " TEXT NOT NULL, "
            + EMPLOYEE_COL_DEPARTMENT_ID + " INTEGER NOT NULL"
            + ")";

    public static void initializeDatabase() {
        // TODO muss ich wirklich überprüfen ob die datenbank schon erstellt wurde? die tabellen werden ja mit dem Befehl "CREATE TABLE IF NOT EXISTS ..." angelegt
        if (databaseFileExists())
            return;

        Connection con = getConnection();

        if (null != con)
            createTables(con);
    }

    static Connection getConnection() {
        String url = String.format("jdbc:sqlite:%s/%s", getDatabaseDir(), DATABASE_NAME);
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            return null;
        }
    }

    private static void createTables(Connection connection) {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(CREATE_DEPARTMENTS_TABLE);
            stmt.execute(CREATE_EMPLOYEE_TABLE);

        } catch (SQLException e) {

            // TODO what to do on SQLException ?
        }
    }

    private static boolean databaseFileExists() {
        File file = new File(String.format("%s/%s", getDatabaseDir(), DATABASE_NAME));
        return file.exists();
    }

    private static String getDatabaseDir() {
        return System.getProperty("user.dir") + "/assets/SQLite";
    }
}
