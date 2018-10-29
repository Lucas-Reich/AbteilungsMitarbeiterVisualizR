package tests.Fachkonzept;

import AbteilungsMitarbeiterVisualizR.Fachkonzept.Fachkonzept1;
import AbteilungsMitarbeiterVisualizR.Fachkonzept.IFachkonzept;
import AbteilungsMitarbeiterVisualizR.Persistence.IPersistence;

public class TestFachkonzept1 extends AbstractFachkonzeptTest {
    @Override
    protected IFachkonzept createFachkonzept(IPersistence persistence) {
        return new Fachkonzept1(
                persistence
        );
    }
}
