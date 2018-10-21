package implementation.Persistence;

import implementation.Entities.Department;
import implementation.Entities.Employee;

import java.util.List;

public interface IDatenhaltung {
    Department saveDepartment(Department department);

    Department getDepartment(long id);

    List<Department> getAllDepartments();

    void updateDepartment(Department department);//TODO: Should we return true or false?

    void deleteDepartment(long abteilungId);//TODO: Should we return true or false?


    Employee saveEmployee(Employee employee, long abteilungId);

    Employee getEmployee(long id);

    List<Employee> getAllEmployees();

    void updateEmployee(Employee employee);

    void deleteEmployee(long mitarbeiterId);
}
