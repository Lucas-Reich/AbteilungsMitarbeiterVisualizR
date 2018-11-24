package AbteilungsMitarbeiterVisualizR.UI;

import AbteilungsMitarbeiterVisualizR.Fachkonzept.IFachkonzept;
import AbteilungsMitarbeiterVisualizR.Fachkonzept.Fachkonzept1;
import AbteilungsMitarbeiterVisualizR.Persistence.IPersistence;
import tests.Fachkonzept.VisualizR_TUI;

public class TUI {
    private IFachkonzept mFachkonzept;

    // TODO implement me
    public TUI(IPersistence persistence) {
        mFachkonzept = new Fachkonzept1(persistence);
    }

    public void show() {
        VisualizR_TUI tui = new VisualizR_TUI();
        tui.tuiShow();
    }
}
