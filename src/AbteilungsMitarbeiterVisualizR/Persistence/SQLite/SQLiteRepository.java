package AbteilungsMitarbeiterVisualizR.Persistence.SQLite;

import AbteilungsMitarbeiterVisualizR.Entities.Department;
import AbteilungsMitarbeiterVisualizR.Entities.Employee;
import AbteilungsMitarbeiterVisualizR.Log;
import AbteilungsMitarbeiterVisualizR.Persistence.IPersistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteRepository implements IPersistence {
    private SQLiteDatabaseHandler mDatabase;

    public SQLiteRepository(SQLiteDatabaseHandler connectionHandler) {
        mDatabase = connectionHandler;
    }

    @Override
    public Department saveDepartment(Department dep) {
        String insertQuery = "INSERT INTO "
                + SQLiteHelper.TABLE_DEPARTMENTS
                + " (" + SQLiteHelper.DEPARTMENT_COL_NAME + ")"
                + " VALUES "
                + " ('" + dep.getName() + "');";

        mDatabase.connect();
        int departmentId = mDatabase.executeUpdate(insertQuery);
        mDatabase.disconnect();

        return new Department(departmentId, dep.getName());
    }

    @Override
    public Department getDepartment(long id) {
        String getQuery = "SELECT "
                + SQLiteHelper.DEPARTMENT_COL_ID + ", "
                + SQLiteHelper.DEPARTMENT_COL_NAME
                + " FROM " + SQLiteHelper.TABLE_DEPARTMENTS
                + " WHERE " + SQLiteHelper.DEPARTMENT_COL_ID + " = " + id + ";";

        mDatabase.connect();
        Department department = resultSetToDepartment(mDatabase.executeQuery(getQuery));
        mDatabase.disconnect();

        return department;
    }

    @Override
    public List<Department> getAllDepartments() {
        String getAllQuery = "SELECT "
                + SQLiteHelper.DEPARTMENT_COL_ID + ", "
                + SQLiteHelper.DEPARTMENT_COL_NAME
                + " FROM " + SQLiteHelper.TABLE_DEPARTMENTS + ";";


        mDatabase.connect();
        List<Department> departments = resultSetToDepartments(
                mDatabase.executeQuery(getAllQuery)
        );
        mDatabase.disconnect();

        return departments;
    }

    @Override
    public void updateDepartment(Department dep) {
        String updateQuery = "UPDATE "
                + SQLiteHelper.TABLE_DEPARTMENTS
                + " SET " + SQLiteHelper.DEPARTMENT_COL_NAME + " = '" + dep.getName() + "'"
                + " WHERE " + SQLiteHelper.DEPARTMENT_COL_ID + " = " + dep.getId()
                + ";";

        mDatabase.connect();
        mDatabase.executeUpdate(updateQuery);
        mDatabase.disconnect();
    }

    @Override
    public void deleteDepartment(long departmentId) {
        String deleteQuery = "DELETE FROM "
                + SQLiteHelper.TABLE_DEPARTMENTS
                + " WHERE " + SQLiteHelper.DEPARTMENT_COL_ID + " = " + departmentId;

        mDatabase.connect();
        mDatabase.executeUpdate(deleteQuery);
        mDatabase.disconnect();
    }

    @Override
    public Employee saveEmployee(Employee emp, long departmentId) {
        String insertQuery = "INSERT INTO "
                + SQLiteHelper.TABLE_EMPLOYEES
                + "( " + SQLiteHelper.EMPLOYEE_COL_NAME + ", " + SQLiteHelper.EMPLOYEE_COL_DEPARTMENT_ID + ")"
                + " VALUES ('" + emp.getName() + "', " + departmentId + ");";

        mDatabase.connect();
        int employeeId = mDatabase.executeUpdate(insertQuery);
        mDatabase.disconnect();

        return new Employee(employeeId, emp.getName());
    }

    @Override
    public Employee getEmployee(long id) {
        String selectQuery = "SELECT "
                + SQLiteHelper.EMPLOYEE_COL_ID + ", "
                + SQLiteHelper.EMPLOYEE_COL_NAME
                + " FROM " + SQLiteHelper.TABLE_EMPLOYEES
                + " WHERE " + SQLiteHelper.EMPLOYEE_COL_ID + " = " + id + ";";

        mDatabase.connect();
        Employee employee = resultSetToEmployee(
                mDatabase.executeQuery(selectQuery)
        );
        mDatabase.disconnect();

        return employee;
    }

    @Override
    public List<Employee> getEmployees(long departmentId) {
        String selectQuery = "SELECT "
                + SQLiteHelper.EMPLOYEE_COL_ID + ", "
                + SQLiteHelper.EMPLOYEE_COL_NAME
                + " FROM " + SQLiteHelper.TABLE_EMPLOYEES
                + " WHERE " + SQLiteHelper.EMPLOYEE_COL_DEPARTMENT_ID + " = " + departmentId
                + ";";

        mDatabase.connect();
        List<Employee> employees = resultSetToEmployees(
                mDatabase.executeQuery(selectQuery)
        );
        mDatabase.disconnect();

        return employees;
    }

    @Override
    public void updateEmployee(Employee emp) {
        String updateQuery = "UPDATE "
                + SQLiteHelper.TABLE_EMPLOYEES
                + " SET " + SQLiteHelper.EMPLOYEE_COL_NAME + " = '" + emp.getName() + "'"
                + " WHERE " + SQLiteHelper.EMPLOYEE_COL_ID + " = " + emp.getId()
                + ";";

        mDatabase.connect();
        mDatabase.executeUpdate(updateQuery);
        mDatabase.disconnect();
    }

    @Override
    public void deleteEmployee(long employeeId) {
        String deleteQuery = "DELETE FROM "
                + SQLiteHelper.TABLE_EMPLOYEES
                + " WHERE " + SQLiteHelper.EMPLOYEE_COL_ID + " = " + employeeId
                + ";";

        mDatabase.connect();
        mDatabase.executeUpdate(deleteQuery);
        mDatabase.disconnect();
    }

    private List<Department> resultSetToDepartments(ResultSet resultSet) {
        try {
            List<Department> departments = new ArrayList<>();
            while (resultSet.next()) {
                Department dep = resultSetToDepartment(resultSet);
                if (null != dep)
                    departments.add(resultSetToDepartment(resultSet));
            }

            return departments;
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);

            return new ArrayList<>();
        }
    }

    private List<Employee> resultSetToEmployees(ResultSet resultSet) {
        try {
            List<Employee> departments = new ArrayList<>();
            while (resultSet.next()) {
                Employee dep = resultSetToEmployee(resultSet);
                if (null != dep)
                    departments.add(resultSetToEmployee(resultSet));
            }

            return departments;
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);

            return new ArrayList<>();
        }
    }

    private Department resultSetToDepartment(ResultSet resultSet) {
        try {
            Department department = new Department(
                    resultSet.getLong(SQLiteHelper.DEPARTMENT_COL_ID),
                    resultSet.getString(SQLiteHelper.DEPARTMENT_COL_NAME)
            );

            List<Employee> employees = getEmployees(department.getId());
            for (Employee employee : employees) {
                department.addEmployee(employee);
            }
            return department;
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);

            return null;
        }
    }

    private Employee resultSetToEmployee(ResultSet resultSet) {
        try {
            return new Employee(
                    resultSet.getLong(SQLiteHelper.EMPLOYEE_COL_ID),
                    resultSet.getString(SQLiteHelper.EMPLOYEE_COL_NAME)
            );
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);

            return null;
        }
    }
}
