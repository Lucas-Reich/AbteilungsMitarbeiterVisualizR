package implementation.Persistence.SQLite;

import implementation.Entities.Department;
import implementation.Entities.Employee;
import implementation.Persistence.IPersistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteAccessor implements IPersistence {
    private Connection mConn;

    public SQLiteAccessor() {
        String url = "jdbc:sqlite:C://sqlite/db/test.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        mConn = conn;
    }

    private long getIdForDepartment(Department dep) {
        String selectQuery = "SELECT "
                + SQLiteHelper.DEPARTMENT_COL_ID
                + " FROM " + SQLiteHelper.TABLE_DEPARTMENTS
                + " WHERE " + SQLiteHelper.DEPARTMENT_COL_NAME + " = " + dep.getName();

        try {
            Statement stmt = mConn.createStatement();
            ResultSet rs = stmt.executeQuery(selectQuery);

            return rs.getLong(SQLiteHelper.EMPLOYEE_COL_ID);
        } catch (SQLException e) {

            return -1;
        }
    }

    private long getIdForEmployee(Employee emp) {
        String selectQuery = "SELECT "
                + SQLiteHelper.EMPLOYEE_COL_ID
                + " FROM " + SQLiteHelper.TABLE_EMPLOYEES
                + " WHERE " + SQLiteHelper.EMPLOYEE_COL_NAME + " = " + emp.getName();

        try {
            Statement stmt = mConn.createStatement();
            ResultSet rs = stmt.executeQuery(selectQuery);

            return rs.getLong(SQLiteHelper.EMPLOYEE_COL_ID);
        } catch (SQLException e) {

            return -1;
        }
    }

    @Override
    public Department saveDepartment(Department dep) {
        String query = "INSERT INTO "
                + SQLiteHelper.TABLE_DEPARTMENTS
                + " (" + SQLiteHelper.DEPARTMENT_COL_NAME + ")"
                + " VALUES "
                + " (" + dep.getName() + " );";

        executeQuery(query);

        return new Department(
                getIdForDepartment(dep),
                dep.getName()
        );
    }

    private boolean executeQuery(String query) {
        try {
            Statement stmt = mConn.createStatement();
            ResultSet resSet = stmt.executeQuery(query);

            return resSet.getFetchSize() > 0;
        } catch (SQLException e) {

            return false;
        }
    }

    @Override
    public Department getDepartment(long id) {
        String query = "SELECT "
                + SQLiteHelper.DEPARTMENT_COL_ID + ", "
                + SQLiteHelper.DEPARTMENT_COL_NAME
                + " WHERE " + SQLiteHelper.DEPARTMENT_COL_ID + " = " + id + ";";

        try {
            Statement stmt = mConn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            return new Department(
                    resultSet.getLong(SQLiteHelper.DEPARTMENT_COL_ID),
                    resultSet.getString(SQLiteHelper.DEPARTMENT_COL_NAME)
            );
        } catch (SQLException e) {

            return null;
        }
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();

        String query = "SELECT "
                + SQLiteHelper.DEPARTMENT_COL_ID + ", "
                + SQLiteHelper.DEPARTMENT_COL_NAME;

        try {
            Statement stmt = mConn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                departments.add(new Department(
                        resultSet.getLong(SQLiteHelper.DEPARTMENT_COL_ID),
                        resultSet.getString(SQLiteHelper.DEPARTMENT_COL_NAME)
                ));
            }

        } catch (SQLException e) {

            //todo do nothing
        }

        return departments;
    }

    @Override
    public void updateDepartment(Department dep) {
        String updateQuery = "UPDATE "
                + SQLiteHelper.TABLE_DEPARTMENTS
                + " SET " + SQLiteHelper.DEPARTMENT_COL_NAME + " = " + dep.getName()
                + " WHERE " + SQLiteHelper.DEPARTMENT_COL_ID + " = " + dep.getId()
                + ";";

        try {
            Statement stmt = mConn.createStatement();
            stmt.executeQuery(updateQuery);

        } catch (SQLException e) {

            //todo do smth
        }
    }

    @Override
    public void deleteDepartment(long departmentId) {
        String deleteQuery = "DELETE FROM "
                + SQLiteHelper.TABLE_DEPARTMENTS
                + " WHERE " + SQLiteHelper.DEPARTMENT_COL_ID + " = " + departmentId;

        try {
            Statement stmt = mConn.createStatement();
            stmt.executeQuery(deleteQuery);
        } catch (SQLException e) {

            //todo do smth
        }
    }

    @Override
    public Employee saveEmployee(Employee emp, long departmentId) {
        String insertQuery = "INSERT INTO "
                + SQLiteHelper.TABLE_EMPLOYEES
                + "( " + SQLiteHelper.EMPLOYEE_COL_NAME + ", " + SQLiteHelper.EMPLOYEE_COL_DEPARTMENT_ID + ")"
                + " VALUES (" + emp.getName() + " , " + departmentId + " );";

        executeQuery(insertQuery);

        return new Employee(
                getIdForEmployee(emp),
                emp.getName()
        );
    }

    @Override
    public Employee getEmployee(long id) {
        String selectQuery = "SELECT "
                + SQLiteHelper.EMPLOYEE_COL_ID + ", "
                + SQLiteHelper.EMPLOYEE_COL_NAME
                + " FROM " + SQLiteHelper.TABLE_EMPLOYEES
                + " WHERE " + SQLiteHelper.EMPLOYEE_COL_ID + " = " + id + ";";

        try {
            Statement stmt = mConn.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectQuery);

            return new Employee(
                    resultSet.getLong(SQLiteHelper.EMPLOYEE_COL_ID),
                    resultSet.getString(SQLiteHelper.EMPLOYEE_COL_NAME)
            );
        } catch (SQLException e) {

            return null;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        String selectQuery = "SELECT "
                + SQLiteHelper.EMPLOYEE_COL_ID + ", "
                + SQLiteHelper.EMPLOYEE_COL_NAME
                + " FROM " + SQLiteHelper.TABLE_EMPLOYEES + ";";

        try {
            Statement stmt = mConn.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectQuery);

            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getLong(SQLiteHelper.EMPLOYEE_COL_ID),
                        resultSet.getString(SQLiteHelper.EMPLOYEE_COL_NAME)
                ));
            }
        } catch (SQLException e) {

            //todo do smth
        }

        return employees;
    }

    @Override
    public void updateEmployee(Employee emp) {
        String updateQuery = "UPDATE "
                + SQLiteHelper.TABLE_EMPLOYEES
                + " SET " + SQLiteHelper.EMPLOYEE_COL_NAME + " = " + emp.getName()
                + " WHERE " + SQLiteHelper.EMPLOYEE_COL_ID + " = " + emp.getId()
                + ";";

        try {
            Statement stmt = mConn.createStatement();
            stmt.execute(updateQuery);

        } catch (SQLException e) {

            //todo do smth
        }
    }

    @Override
    public void deleteEmployee(long employeeId) {
        String deleteQuery = "DELETE FROM "
                + SQLiteHelper.TABLE_EMPLOYEES
                + " WHERE " + SQLiteHelper.EMPLOYEE_COL_ID + " = " + employeeId
                + ";";

        try {
            Statement stmt = mConn.createStatement();
            stmt.execute(deleteQuery);

        } catch (SQLException e) {

            //todo do smth
        }

    }
}
