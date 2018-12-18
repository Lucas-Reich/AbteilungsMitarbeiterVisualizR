package AbteilungsMitarbeiterVisualizR.Entities;

public class Employee {
    private long id;
    private String name;

    private Employee() {}

    public static Employee init(Long id, String name) {
        Employee employee = new Employee();
        employee.setId(null != id ? id : -1);
        employee.setName(name);

        return employee;
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Employee))
            return false;

        Employee other = (Employee) obj;

        return this.getId() == other.getId()
                && this.getName().equals(other.getName());
    }
}