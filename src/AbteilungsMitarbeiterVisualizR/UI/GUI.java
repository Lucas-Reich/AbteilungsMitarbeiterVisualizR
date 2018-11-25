package AbteilungsMitarbeiterVisualizR.UI;

import AbteilungsMitarbeiterVisualizR.Fachkonzept.IFachkonzept;
import AbteilungsMitarbeiterVisualizR.Fachkonzept.Fachkonzept1;
import AbteilungsMitarbeiterVisualizR.Persistence.IPersistence;
import tests.Fachkonzept.FoobarGUI;
import tests.Fachkonzept.VisualizR_GUI;

public class GUI {
    private IFachkonzept mFachkonzept;
    // TODO implement me
    public GUI(IPersistence persistence) {
        mFachkonzept = new Fachkonzept1(persistence);
    }

    public void show() {
        FoobarGUI foo = new FoobarGUI();
        foo.setDepartmentsData(mFachkonzept.getDepartments());
        foo.setEmployeesData();
        foo.setListener();
        foo.createAndShowGUI();

        //VisualizR_GUI gui = new VisualizR_GUI();
        //gui.setData(mFachkonzept.getDepartments());
        //gui.createFrame();
    }
}
