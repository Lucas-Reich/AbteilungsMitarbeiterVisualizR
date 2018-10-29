package AbteilungsMitarbeiterVisualizR.UI;

import AbteilungsMitarbeiterVisualizR.Fachkonzept.IFachkonzept;
import AbteilungsMitarbeiterVisualizR.Fachkonzept.Fachkonzept1;
import AbteilungsMitarbeiterVisualizR.Persistence.IPersistence;

public class GUI {
    private IFachkonzept mFachkonzept;
    // TODO implement me
    public GUI(IPersistence persistence) {
        mFachkonzept = new Fachkonzept1(persistence);
    }

    public void show() {
        // TODO zeige die Programm UI an
    }
}
