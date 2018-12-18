package AbteilungsMitarbeiterVisualizR.UI;

import AbteilungsMitarbeiterVisualizR.Fachkonzept.IFachkonzept;
import AbteilungsMitarbeiterVisualizR.Fachkonzept.Fachkonzept1;
import AbteilungsMitarbeiterVisualizR.Persistence.IPersistence;

public class GUI {
    private IFachkonzept mFachkonzept;

    private GUI() {}

    public static GUI init(IFachkonzept fachkonzept) {
        GUI gui = new GUI();
        gui.mFachkonzept = fachkonzept;

        return gui;
    }
}
