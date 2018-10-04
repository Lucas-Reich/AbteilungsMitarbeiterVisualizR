import java.util.ArrayList;
import java.util.List;

public class Abteilung {
    private long id;
    private String name;
    private List<Mitarbeiter> mitarbeiters;

    public Abteilung(long id, String name) {
        setId(id);
        setName(name);
        mitarbeiters = new ArrayList<>();
    }

    public Abteilung(String name) {
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

    public void addMitarbeiter(Mitarbeiter mitarbeiter) {
        mitarbeiters.add(mitarbeiter);
    }

    public List<Mitarbeiter> getMitarbeiters() {
        return mitarbeiters;
    }
}
