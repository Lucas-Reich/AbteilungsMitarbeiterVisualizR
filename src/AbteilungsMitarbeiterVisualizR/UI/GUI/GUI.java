package AbteilungsMitarbeiterVisualizR.UI.GUI;

import AbteilungsMitarbeiterVisualizR.Fachkonzept.IFachkonzept;

public class GUI {
    private IFachkonzept mFachkonzept;

    private GUI() {}

    public static GUI init(IFachkonzept fachkonzept) {
        GUI gui = new GUI();
        gui.mFachkonzept = fachkonzept;

        return gui;
    }
}
