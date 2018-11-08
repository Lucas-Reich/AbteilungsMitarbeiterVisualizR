package AbteilungsMitarbeiterVisualizR.Persistence.XML;

import AbteilungsMitarbeiterVisualizR.Entities.Department;
import AbteilungsMitarbeiterVisualizR.Entities.Employee;
import AbteilungsMitarbeiterVisualizR.Persistence.IPersistence;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.Console;
import java.io.File;
import java.util.List;

public class XMLHelper implements IPersistence {
    // TODO implement me

    static final String XML_FILE_NAME  = "abteilungsMitarbeiterVisualizR.xml";

    private DocumentBuilderFactory docFactory = null;
    private DocumentBuilder docBuilder = null;
    private Document doc = null;
    private File XMLFile = null;

    public XMLHelper() {
        if(!checkXMLFileExists()){
            this.XMLFile = new File(String.format("%s/%s", getXMLDir(), XML_FILE_NAME);
        }


    }

    @Override
    public Department saveDepartment(Department department) {
        try {
            docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();


        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Department getDepartment(long id) {
        return null;
    }

    @Override
    public List<Department> getAllDepartments() {
        return null;
    }

    @Override
    public void updateDepartment(Department department) {

    }

    @Override
    public void deleteDepartment(long departmentId) {

    }

    @Override
    public Employee saveEmployee(Employee employee, long departmentId) {
        return null;
    }

    @Override
    public Employee getEmployee(long id) {
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public void updateEmployee(Employee employee) {

    }

    @Override
    public void deleteEmployee(long employeeId) {

    }

    private boolean checkXMLFileExists(){
        File XMLFile = new File(String.format("%s/%s", getXMLDir(), XML_FILE_NAME));
        return XMLFile.exists();
    }

    private String getXMLDir(){
        return System.getProperty("user.dir") + "/assets/XML";
    }
}
