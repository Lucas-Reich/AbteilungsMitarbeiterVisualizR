package AbteilungsMitarbeiterVisualizR.Entities;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private long id;
    private String name;
    private List<Employee> employees;

    private Department() { }

    public static Department init(Long id, String name) {
        Department department = new Department();
        department.setId(null != id ? id : -1);
        department.setName(name);
        department.employees = new ArrayList<>();

        return department;
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addEmployees(List<Employee> employees) {
        this.employees.addAll(employees);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Department))
            return false;

        Department other = (Department) obj;

        return this.getId() == other.getId()
                && this.getName().equals(other.getName())
                && this.getEmployees().equals(other.getEmployees());
    }
}
