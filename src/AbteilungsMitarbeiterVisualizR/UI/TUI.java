package AbteilungsMitarbeiterVisualizR.UI;

import AbteilungsMitarbeiterVisualizR.Entities.Department;
import AbteilungsMitarbeiterVisualizR.Entities.Employee;
import AbteilungsMitarbeiterVisualizR.Fachkonzept.IFachkonzept;
import AbteilungsMitarbeiterVisualizR.Log;

import java.util.List;
import java.util.Scanner;

public class TUI {
    private IFachkonzept mFachkonzept;
    private Scanner mScanner;

    private TUI() { }

    public static TUI init(IFachkonzept fachkonzept) {
        TUI tui = new TUI();
        tui.mFachkonzept = fachkonzept;
        tui.mScanner = new Scanner(System.in);

        return tui;
    }

    public void show() {
        do {
            try{
            Log.console("Eine Abteilung speichern (a)");
            Log.console("Eine Abteilung auslesen (b)");
            Log.console("Eine Abteilung bearbeiten (c)");
            Log.console("Eine Abteilung löschen (d)");
            Log.console("Liste Mitarbeiter aus Abteilung auf (e)");
            Log.console("Einen Mitarbeiter speichern (f)");
            Log.console("Einen Mitarbeiter auslesen (g)");
            Log.console("Einen Mitarbeiter bearbeiten (h)");
            Log.console("Einen Mitarbeiter zu einer neuen Abteilung hinzufügen (i)");
            Log.console("Einen Mitarbeiter löschen (j)");
            Log.console("Liste alle Abteilungen auf (k)");

            Log.console("Bearbeitung abbrechen (x)");

            Log.console("Bitte geben Sie einen Aktionskey ein:");


            String input = mScanner.nextLine();

            if (input.charAt(0) == 'x') {
                return;
            }

            readAction(input.charAt(0));
            }catch(Exception e){
                Log.console("Fehlerhafte Eingabe");
            }
        } while(true);
    }

    private void readAction(char actionKey) {
        switch (actionKey) {
            case 'a':
                Log.console("Bitte geben Sie einen Name ein:");
                String input = mScanner.nextLine();
                mFachkonzept.saveDepartment(input);
                Log.console("Gespeichert");
                break;
            case 'b':
                Log.console("Bitte geben Sie die Id der Abteilung an");
                long id = Long.valueOf(mScanner.nextLine());
                Department dep = mFachkonzept.getDepartment(id);
                departmentToConsole(dep);

                for (Employee emp : dep.getEmployees()) {
                    employeeToConsole(emp);
                }
                break;
            case 'c':
                Log.console("Bitte geben Sie die Id der Abteilung an:");
                long id2 = Long.valueOf(mScanner.nextLine());

                Department dep2 = mFachkonzept.getDepartment(id2);
                departmentToConsole(dep2);

                Log.console("Geben Sie den neuen Namen der Abteilung an:");
                String newName = mScanner.nextLine();
                mFachkonzept.updateDepartmentName(newName, id2);

                Log.console("Abteilung wurde aktualisiert.");
                break;
            case 'd':
                Log.console("Gebe Sie die Id der zu löschenden Abteilung an:");
                long id3 = Long.valueOf(mScanner.nextLine());

                mFachkonzept.deleteDepartment(id3);

                Log.console("Abteilung wurde gelöscht");
                break;
            case 'e':
                Log.console("Bitte geben Sie die ID der Abteilung an:");
                long id4 = Long.valueOf(mScanner.nextLine());

                Department dep3 = mFachkonzept.getDepartment(id4);

                for (Employee emp : dep3.getEmployees()) {
                    employeeToConsole(emp);
                }
                break;
            case 'f':
                Log.console("Bitte geben Sie den Namen des Mitarbeiters an");
                String empName = mScanner.nextLine();

                Log.console("Bitte geben Sie die Id der zugehörigen Abteilung an:");
                long id5 = Long.valueOf(mScanner.nextLine());

                mFachkonzept.saveNewEmployee(empName, id5);

                Log.console("Mitarbeiter wurde gespeichert.");
                break;
            case 'g':
                Log.console("Bitte geben Sie die Id des Mitarbeiters an:");
                long id6 = Long.valueOf(mScanner.nextLine());

                Employee emp = mFachkonzept.getEmployee(id6);
                employeeToConsole(emp);
                break;
            case 'h':
                Log.console("Bitte geben Sie die Id des Mitarbeiters an:");
                long id7 = Long.valueOf(mScanner.nextLine());

                Employee emp2 = mFachkonzept.getEmployee(id7);
                employeeToConsole(emp2);

                Log.console("Bitte geben Sie den neuen Namen an:");
                String newName2 = mScanner.nextLine();

                mFachkonzept.updateEmployeeName(newName2, id7);

                Log.console("Mitarbeiter wurde aktualisiert");
                break;
            case 'i':
                Log.console("Bitte geben Sie die Id des Mitarbeiters an:");
                long id8 = Long.valueOf(mScanner.nextLine());

                Log.console("BItte geben Sie die Id der neuen Abteilung an:");
                long id9 = Long.valueOf(mScanner.nextLine());

                mFachkonzept.reassingEmployeeDepartment(id8, id9);

                Log.console("Mitarbeiter wurde in die neue Abteilung hinzugefügt");
                break;
            case 'j':
                Log.console("Bitte geben Sie die Id des Mitarbeiters an:");
                long id10 = Long.valueOf(mScanner.nextLine());

                mFachkonzept.deleteEmployee(id10);

                Log.console("Mitarbeiter wurde gelöscht");
                break;
            case 'k':
                List<Department> deps = mFachkonzept.getDepartments();

                if(deps.isEmpty()){
                    Log.console("Die Liste der Abteilungen ist leer.");
                }else{
                    for (Department dep4 : deps) {
                        departmentToConsole(dep4);
                    }
                }

                break;
            default:
                Log.console("Aktion ist nicht registriert!");
        }
    }

    private void departmentToConsole(Department dep) {
        Log.console(String.format("%s (%s)", dep.getName(), dep.getId()));
    }

    private void employeeToConsole(Employee emp) {
        Log.console(String.format("%s (%s)", emp.getName(), emp.getId()));
    }
}
