package AbteilungsMitarbeiterVisualizR.Persistence;

import AbteilungsMitarbeiterVisualizR.Entities.Department;
import AbteilungsMitarbeiterVisualizR.Entities.Employee;

import java.util.List;

public interface IPersistence {
    Department saveDepartment(Department department);

    Department getDepartment(long id);

    List<Department> getAllDepartments();

    void updateDepartment(Department department);//TODO: Should we return true or false?

    void deleteDepartment(long departmentId);//TODO: Should we return true or false?


    Employee saveEmployee(Employee employee, long departmentId);

    Employee getEmployee(long id);

    List<Employee> getAllEmployees();

    void updateEmployee(Employee employee);

    void deleteEmployee(long employeeId);
}
