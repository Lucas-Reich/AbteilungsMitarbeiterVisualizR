package AbteilungsMitarbeiterVisualizR.Persistence.SQLite;

import AbteilungsMitarbeiterVisualizR.Log;

import java.io.File;
import java.sql.*;

public class SQLiteHelper {
    static final String DATABASE_NAME = "abteilungsMitarbeiterVisualizR.db";

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

    public static void initializeDatabase(SQLiteDatabaseHandler databaseHandler) {
        if (databaseExists())
            return;

        databaseHandler.connect();
        createTables(databaseHandler);
        databaseHandler.disconnect();
    }

    private static void createTables(SQLiteDatabaseHandler handler) {
        handler.execute(CREATE_DEPARTMENTS_TABLE);
        handler.execute(CREATE_EMPLOYEE_TABLE);
    }

    private static boolean databaseExists() {
        File file = new File(String.format("%s/%s", getDatabaseDir(), DATABASE_NAME));
        return file.exists();
    }

    private static String getDatabaseDir() {
        return System.getProperty("user.dir") + "/assets/SQLite";
    }
}
