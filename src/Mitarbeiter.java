public class Mitarbeiter {
    private long id;
    private String name;

    public Mitarbeiter(long id, String name) {
        setId(id);
        setName(name);
    }

    public Mitarbeiter(String name) {
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
}