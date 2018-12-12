package AbteilungsMitarbeiterVisualizR.Fachkonzept;

import AbteilungsMitarbeiterVisualizR.Entities.Department;
import AbteilungsMitarbeiterVisualizR.Entities.Employee;
import AbteilungsMitarbeiterVisualizR.Persistence.IPersistence;

import java.util.Comparator;
import java.util.List;

/**
 * Fachkonzept2 will return data which is reverse alphabetically (Z - A) sorted.
 */
public class Fachkonzept2 implements IFachkonzept {
    private static final String PROJECT_NAME = "AbteilungsMitarbeiterVisualizR";
    private IPersistence mPersistence;

    public Fachkonzept2(IPersistence persistence) {
        mPersistence = persistence;
    }

    @Override
    public String getProjectName() {
        return PROJECT_NAME;
    }

    @Override
    public Department getDepartment(long departmentId) {
        return mPersistence.getDepartment(departmentId);
    }

    @Override
    public List<Department> getDepartments() {
        List<Department> departments = mPersistence.getAllDepartments();

        departments.sort(new Comparator<Department>() {
            @Override
            public int compare(Department o1, Department o2) {
                return o2.getName().compareTo(o1.getName());
            }
        });
        return departments;
    }

    @Override
    public void saveDepartment(String departmentName) {
            Department department = new Department(departmentName);

            mPersistence.saveDepartment(department);
    }

    @Override
    public void deleteDepartment(long departmentId) {
        mPersistence.deleteDepartment(departmentId);
    }

    @Override
    public void updateDepartmentName(String name, long departmentId) {
        Department updatedDepartment = new Department(departmentId, name);

        mPersistence.updateDepartment(updatedDepartment);
    }

    @Override
    public Employee getEmployee(long employeeId) {
        return mPersistence.getEmployee(employeeId);
    }

    @Override
    public List<Employee> getEmployees(long departmentId) {
        List<Employee> employees = mPersistence.getEmployees(departmentId);

        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return Character.compare(o2.getName().charAt(0), o1.getName().charAt(0));
            }
        });
        return employees;
    }

    @Override
    public void saveNewEmployee(String employeeName, long departmentId) {
        Employee employee = new Employee(employeeName);

        mPersistence.saveEmployee(employee, departmentId);
    }

    @Override
    public void deleteEmployee(long employeeId) {
        mPersistence.deleteEmployee(employeeId);
    }

    @Override
    public void updateEmployeeName(String name, long employeeId) {
        Employee updatedEmployee = new Employee(employeeId, name);

        mPersistence.updateEmployee(updatedEmployee);
    }

    @Override
    public void reassingEmployeeDepartment(long employeeId, long departmentId) {
        Employee employee = mPersistence.getEmployee(employeeId);

        mPersistence.deleteEmployee(employeeId);
        mPersistence.saveEmployee(employee, departmentId);
    }
}
