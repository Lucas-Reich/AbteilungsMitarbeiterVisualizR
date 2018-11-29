package tests.Fachkonzept.Persistence;

import AbteilungsMitarbeiterVisualizR.Persistence.IPersistence;
import AbteilungsMitarbeiterVisualizR.Persistence.SQLite.SQLiteDatabaseHandler;
import AbteilungsMitarbeiterVisualizR.Persistence.SQLite.SQLiteRepository;

public class TestSQLite extends AbstractPersistenceTest {
    @Override
    IPersistence createPersistence() {
        return new SQLiteRepository(new SQLiteDatabaseHandler());
    }
}
