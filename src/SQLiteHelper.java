import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteHelper {
    public static final String TABLE_DEPARTMENTS = "departments";
    public static final String DEPARTMENT_COL_ID = "id";
    public static final String DEPARTMENT_COL_NAME = "name";
    private static final String CREATE_DEPARTMENTS_TABLE = ""
            + "CREATE TABLE IF NOT EXISTS ("
            + TABLE_DEPARTMENTS + " "
            + DEPARTMENT_COL_ID + " INTEGER PRIMARY KEY"
            + DEPARTMENT_COL_NAME + " TEXT NOT NULL"
            + ")";

    public static final String TABLE_EMPLOYEES = "employee";
    public static final String EMPLOYEE_COL_ID = "id";
    public static final String EMPLOYEE_COL_NAME = "name";
    public static final String EMPLOYEE_COL_DEPARTMENT_ID = "department_id";
    private static final String CREATE_EMPLOYEE_TABLE = ""
            + "CREATE TABLE IF NOT EXISTS ("
            + TABLE_EMPLOYEES + " "
            + EMPLOYEE_COL_ID + " INTEGER PRIMARY KEY, "
            + EMPLOYEE_COL_NAME + " TEXT NOT NULL, "
            + EMPLOYEE_COL_DEPARTMENT_ID + " INTEGER NOT NULL"
            + ")";


    public void createDatabase(Connection connection) {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(CREATE_DEPARTMENTS_TABLE);
            stmt.execute(CREATE_EMPLOYEE_TABLE);
        } catch (SQLException e) {

            //todo do smth
        }
    }
}
