package AbteilungsMitarbeiterVisualizR.Entities;

import org.w3c.dom.events.Event;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private long id;
    private String name;
    private List<Employee> employees;

    public Department(long id, String name) {
        setId(id);
        setName(name);
        employees = new ArrayList<>();
    }

    @Override
    public String toString() {
        return name;
    }

    public Department(String name) {
        this(-1, name);
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

    public void addMitarbeiter(Employee employee) {
        employees.add(employee);
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
