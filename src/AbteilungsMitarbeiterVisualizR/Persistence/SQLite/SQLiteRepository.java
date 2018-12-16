package AbteilungsMitarbeiterVisualizR.Persistence.SQLite;

import AbteilungsMitarbeiterVisualizR.Entities.Department;
import AbteilungsMitarbeiterVisualizR.Entities.Employee;
import AbteilungsMitarbeiterVisualizR.Log;
import AbteilungsMitarbeiterVisualizR.Persistence.IPersistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteRepository implements IPersistence {
    private SQLiteConnectionHandler connectionHandler;

    public SQLiteRepository(SQLiteConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    @Override
    public Department saveDepartment(Department dep) {
        String insertQuery = "INSERT INTO "
                + SQLiteHelper.TABLE_DEPARTMENTS
                + " (" + SQLiteHelper.DEPARTMENT_COL_NAME + ")"
                + " VALUES "
                + " ('" + dep.getName() + "');";

        connectionHandler.connect();
        int departmentId = connectionHandler.executeUpdate(insertQuery);
        connectionHandler.disconnect();

        return new Department(departmentId, dep.getName());
    }

    @Override
    public Department getDepartment(long id) {
        String getQuery = "SELECT "
                + SQLiteHelper.DEPARTMENT_COL_ID + ", "
                + SQLiteHelper.DEPARTMENT_COL_NAME
                + " FROM " + SQLiteHelper.TABLE_DEPARTMENTS
                + " WHERE " + SQLiteHelper.DEPARTMENT_COL_ID + " = " + id + ";";

        connectionHandler.connect();
        Department department = resultSetToDepartment(connectionHandler.executeQuery(getQuery));
        connectionHandler.disconnect();

        return department;
    }

    @Override
    public List<Department> getAllDepartments() {
        String getAllQuery = "SELECT "
                + SQLiteHelper.DEPARTMENT_COL_ID + ", "
                + SQLiteHelper.DEPARTMENT_COL_NAME
                + " FROM " + SQLiteHelper.TABLE_DEPARTMENTS + ";";

        connectionHandler.connect();
        List<Department> departments = resultSetToDepartments(
                connectionHandler.executeQuery(getAllQuery)
        );
        connectionHandler.disconnect();

        return departments;
    }

    @Override
    public void updateDepartment(Department dep) {
        String updateQuery = "UPDATE "
                + SQLiteHelper.TABLE_DEPARTMENTS
                + " SET " + SQLiteHelper.DEPARTMENT_COL_NAME + " = '" + dep.getName() + "'"
                + " WHERE " + SQLiteHelper.DEPARTMENT_COL_ID + " = " + dep.getId()
                + ";";

        connectionHandler.connect();
        connectionHandler.executeUpdate(updateQuery);
        connectionHandler.disconnect();
    }

    @Override
    public void deleteDepartment(long departmentId) {
        String deleteQuery = "DELETE FROM "
                + SQLiteHelper.TABLE_DEPARTMENTS
                + " WHERE " + SQLiteHelper.DEPARTMENT_COL_ID + " = " + departmentId;

        connectionHandler.connect();
        connectionHandler.executeUpdate(deleteQuery);
        connectionHandler.disconnect();
    }

    @Override
    public Employee saveEmployee(Employee emp, long departmentId) {
        String insertQuery = "INSERT INTO "
                + SQLiteHelper.TABLE_EMPLOYEES
                + "( " + SQLiteHelper.EMPLOYEE_COL_NAME + ", " + SQLiteHelper.EMPLOYEE_COL_DEPARTMENT_ID + ")"
                + " VALUES ('" + emp.getName() + "', " + departmentId + ");";

        connectionHandler.connect();
        int employeeId = connectionHandler.executeUpdate(insertQuery);
        connectionHandler.disconnect();

        return new Employee(employeeId, emp.getName());
    }

    @Override
    public Employee getEmployee(long id) {
        String selectQuery = "SELECT "
                + SQLiteHelper.EMPLOYEE_COL_ID + ", "
                + SQLiteHelper.EMPLOYEE_COL_NAME
                + " FROM " + SQLiteHelper.TABLE_EMPLOYEES
                + " WHERE " + SQLiteHelper.EMPLOYEE_COL_ID + " = " + id + ";";

        connectionHandler.connect();
        Employee employee = resultSetToEmployee(
                connectionHandler.executeQuery(selectQuery)
        );
        connectionHandler.disconnect();

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

        connectionHandler.connect();
        List<Employee> employees = resultSetToEmployees(
                connectionHandler.executeQuery(selectQuery)
        );
        connectionHandler.disconnect();

        return employees;
    }

    @Override
    public void updateEmployee(Employee emp) {
        String updateQuery = "UPDATE "
                + SQLiteHelper.TABLE_EMPLOYEES
                + " SET " + SQLiteHelper.EMPLOYEE_COL_NAME + " = '" + emp.getName() + "'"
                + " WHERE " + SQLiteHelper.EMPLOYEE_COL_ID + " = " + emp.getId()
                + ";";

        connectionHandler.connect();
        connectionHandler.executeUpdate(updateQuery);
        connectionHandler.disconnect();
    }

    @Override
    public void deleteEmployee(long employeeId) {
        String deleteQuery = "DELETE FROM "
                + SQLiteHelper.TABLE_EMPLOYEES
                + " WHERE " + SQLiteHelper.EMPLOYEE_COL_ID + " = " + employeeId
                + ";";

        connectionHandler.connect();
        connectionHandler.executeUpdate(deleteQuery);
        connectionHandler.disconnect();
    }

    private List<Department> resultSetToDepartments(ResultSet resultSet) {
        List<Department> departments = new ArrayList<>();

        try {
            while (resultSet.next()) {

                Department department = new Department(
                        resultSet.getLong(SQLiteHelper.DEPARTMENT_COL_ID),
                        resultSet.getString(SQLiteHelper.DEPARTMENT_COL_NAME)
                );

                departments.add(department);
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        for (Department dep : departments) {
            dep.addEmployees(getEmployees(dep.getId()));
        }

        return departments;
    }

    private List<Employee> resultSetToEmployees(ResultSet resultSet) {
        List<Employee> employees = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Employee emp = resultSetToEmployee(resultSet);
                if (null != emp)
                    employees.add(emp);
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        return employees;
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
