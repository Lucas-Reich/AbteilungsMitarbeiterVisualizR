import java.util.List;

public interface IDatenhaltung {
    void save(Object object);

    Object get(long id);

    List<Object> getAll();

    void update(Object object);

    void delete(long id);
}
