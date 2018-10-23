package AbteilungsMitarbeiterVisualizR.Fachkonzept;

import AbteilungsMitarbeiterVisualizR.Entities.Department;
import AbteilungsMitarbeiterVisualizR.Entities.Employee;
import AbteilungsMitarbeiterVisualizR.Persistence.IPersistence;

import java.util.List;

/**
 * Fachkonzept2 will return data which is reverse alphabetically (Z - A) sorted.
 */
public class Fachkonzept2 implements IFachkonzept {
    private IPersistence mPersistence;
    // TODO implement me

    public Fachkonzept2(IPersistence persistence) {
        mPersistence = persistence;
    }

    @Override
    public String getProjectName() {
        return null;
    }

    @Override
    public Department getDepartment(long departmentId) {
        return null;
    }

    @Override
    public List<Department> getDepartments() {
        return null;
    }

    @Override
    public void saveDepartment(String departmentName) {

    }

    @Override
    public void deleteDepartment(long departmentId) {

    }

    @Override
    public void updateDepartmentName(String name, long departmentId) {

    }

    @Override
    public Employee getEmployee(long employeeId) {
        return null;
    }

    @Override
    public List<Employee> getEmployees(long departmentId) {
        return null;
    }

    @Override
    public void saveNewEmployee(String employeeName, long departmentId) {

    }

    @Override
    public void deleteEmployee(long employeeId) {

    }

    @Override
    public void updateEmployeeName(String name, long employeeId) {

    }

    @Override
    public void reassingEmployeeDepartment(long employeeId, long departmentId) {

    }
}
