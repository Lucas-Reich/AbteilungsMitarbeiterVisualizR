package AbteilungsMitarbeiterVisualizR.Persistence.SQLite;

import java.io.File;
import java.sql.*;

public class SQLiteHelper {
    static final String DATABASE_NAME = "abteilungsMitarbeiterVisualizR.db";

    static final String TABLE_DEPARTMENTS = "departments";
    static final String DEPARTMENT_COL_ID = "id";
    static final String DEPARTMENT_COL_NAME = "name";
    private static final String CREATE_DEPARTMENTS_TABLE = ""
            + "CREATE TABLE IF NOT EXISTS ("
            + TABLE_DEPARTMENTS + " "
            + DEPARTMENT_COL_ID + " INTEGER PRIMARY KEY"
            + DEPARTMENT_COL_NAME + " TEXT NOT NULL"
            + ")";

    static final String TABLE_EMPLOYEES = "employee";
    static final String EMPLOYEE_COL_ID = "id";
    static final String EMPLOYEE_COL_NAME = "name";
    static final String EMPLOYEE_COL_DEPARTMENT_ID = "department_id";
    private static final String CREATE_EMPLOYEE_TABLE = ""
            + "CREATE TABLE IF NOT EXISTS ("
            + TABLE_EMPLOYEES + " "
            + EMPLOYEE_COL_ID + " INTEGER PRIMARY KEY, "
            + EMPLOYEE_COL_NAME + " TEXT NOT NULL, "
            + EMPLOYEE_COL_DEPARTMENT_ID + " INTEGER NOT NULL"
            + ")";

    public static void initializeDatabase() { // TODO sollte ich die connection zurückgeben?
        if (databaseFileExists())
            return;

        String url = String.format("jdbc:sqlite:%s/%s", getDatabaseDir(), DATABASE_NAME);

        try (Connection con = DriverManager.getConnection(url)) {
            if (null != con)
                createTables(con);

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }

    static String getDatabaseLocation() {
        return String.format("%s/%s", getDatabaseDir(), DATABASE_NAME);
    }

    private static boolean createTables(Connection connection) {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(CREATE_DEPARTMENTS_TABLE);
            stmt.execute(CREATE_EMPLOYEE_TABLE);

            return true;
        } catch (SQLException e) {

            return false;
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
