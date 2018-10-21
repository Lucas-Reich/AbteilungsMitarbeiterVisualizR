import java.util.List;

public interface IDatenhaltung {
    Department saveDepartment(Department department);

    Department getDepartment(long id);

    List<Department> getAllDepartments();

    void updateDepartment(Department department);//sollten wir ein true oder false zurückgeben?

    void deleteDepartment(long abteilungId);//sollten wir ein true oder false zurückgeben?


    Employee saveEmployee(Employee employee, long abteilungId);

    Employee getEmployee(long id);

    List<Employee> getAllEmployees();

    void updateEmployee(Employee employee);

    void deleteEmployee(long mitarbeiterId);
}
