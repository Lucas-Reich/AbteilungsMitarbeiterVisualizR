package AbteilungsMitarbeiterVisualizR.UI;

import AbteilungsMitarbeiterVisualizR.Fachkonzept.Fachkonzept;
import AbteilungsMitarbeiterVisualizR.Fachkonzept.Fachkonzept1;
import AbteilungsMitarbeiterVisualizR.Persistence.IPersistence;

public class TUI {
    private Fachkonzept mFachkonzept;

    // TODO implement me
    public TUI(IPersistence persistence) {
        mFachkonzept = new Fachkonzept1(persistence);
    }

    public void show() {
        // TODO zeige die Terminal UI an
    }
}
