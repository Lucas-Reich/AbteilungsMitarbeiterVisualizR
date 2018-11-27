package AbteilungsMitarbeiterVisualizR.Entities;

public class Employee {
    private long id;
    private String name;

    public Employee(long id, String name) {
        setId(id);
        setName(name);
    }

    public Employee(String name) {
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Employee))
            return false;

        Employee other = (Employee) obj;

        return this.getId() == other.getId()
                && this.getName().equals(other.getName());
    }
    @Override
    public String toString() {
        return name;
    }
}