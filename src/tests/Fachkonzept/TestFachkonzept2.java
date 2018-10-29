package tests.Fachkonzept;

import AbteilungsMitarbeiterVisualizR.Fachkonzept.Fachkonzept2;
import AbteilungsMitarbeiterVisualizR.Fachkonzept.IFachkonzept;
import AbteilungsMitarbeiterVisualizR.Persistence.IPersistence;

public class TestFachkonzept2 extends AbstractFachkonzeptTest {
    @Override
    protected IFachkonzept createFachkonzept(IPersistence persistence) {
        return new Fachkonzept2(
                persistence
        );
    }
}
