public interface IDatenhaltung {
    void save();

    Object get(long id);

    void update(Object object);

    void delete(long id);
}
