package implementation.Persistence.XML;

import implementation.Entities.Department;
import implementation.Entities.Employee;
import implementation.Persistence.IPersistence;

import java.util.List;

public class XMLHelper implements IPersistence {
    // TODO implement me

    @Override
    public Department saveDepartment(Department department) {
        return null;
    }

    @Override
    public Department getDepartment(long id) {
        return null;
    }

    @Override
    public List<Department> getAllDepartments() {
        return null;
    }

    @Override
    public void updateDepartment(Department department) {

    }

    @Override
    public void deleteDepartment(long departmentId) {

    }

    @Override
    public Employee saveEmployee(Employee employee, long departmentId) {
        return null;
    }

    @Override
    public Employee getEmployee(long id) {
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public void updateEmployee(Employee employee) {

    }

    @Override
    public void deleteEmployee(long employeeId) {

    }
}
