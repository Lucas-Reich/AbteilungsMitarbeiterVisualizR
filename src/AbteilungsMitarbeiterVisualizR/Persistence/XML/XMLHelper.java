package AbteilungsMitarbeiterVisualizR.Persistence.XML;

import AbteilungsMitarbeiterVisualizR.Entities.Department;
import AbteilungsMitarbeiterVisualizR.Entities.Employee;
import AbteilungsMitarbeiterVisualizR.Persistence.IPersistence;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class XMLHelper implements IPersistence {
    // TODO implement me

    static final String XML_FILE_NAME = "abteilungsMitarbeiterVisualizR.xml";

    private DocumentBuilderFactory docFactory = null;
    private DocumentBuilder docBuilder = null;
    private Document doc = null;
    private File XMLFile = null;
    private TransformerFactory transFactory = null;
    private Transformer transformer = null;
    private DOMSource source = null;
    private StreamResult result = null;
    private Path filePath = null;

    public XMLHelper() {
        if (!checkXMLFileExists()) {
            this.XMLFile = new File(String.format("%s/%s", getXMLDir(), XML_FILE_NAME));
        } else {
            this.XMLFile = new File(String.valueOf(filePath));
        }
    }

    @Override
    public Department saveDepartment(Department department) {
        Element departments = null;
        try {
            docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();
            if (XMLFile != null && XMLFile.length() > 0) {
                doc = docBuilder.parse(XMLFile);
            } else {
                doc = docBuilder.newDocument();
            }
            //Check if deparment already exists
            NodeList nl = doc.getElementsByTagName("Department");
            for (int i = 0; i < nl.getLength(); i++) {
                if (nl.item(i).getAttributes().getNamedItem("id").getNodeValue().equals(String.valueOf(department.getId()))) {
                    System.out.println("Department with id " + department.getId() + " already exists");
                }
            }
            //check if element departments exists
            if (doc.getElementsByTagName("Departments").getLength() > 0) {
                departments = (Element) doc.getFirstChild();
            } else {
                departments = doc.createElement("Departments");
                doc.appendChild(departments);
            }

            Element departmentElement = doc.createElement("Department");
            departments.appendChild(departmentElement);

            Attr attrDepartmentId = doc.createAttribute("id");
            attrDepartmentId.setValue(String.valueOf(department.getId()));
            departmentElement.setAttributeNode(attrDepartmentId);

            Attr attrDepartmentName = doc.createAttribute("name");
            attrDepartmentName.setValue(department.getName());
            departmentElement.setAttributeNode(attrDepartmentName);

            for (Employee employee : department.getEmployees()) {
                Element employeeElement = doc.createElement("Employee");
                Attr attrEmployeeId = doc.createAttribute("id");
                attrEmployeeId.setValue(String.valueOf(employee.getId()));
                Attr attrEmployeeName = doc.createAttribute("name");
                attrEmployeeName.setValue(String.valueOf(employee.getName()));

                employeeElement.setAttributeNode(attrEmployeeId);
                employeeElement.setAttributeNode(attrEmployeeName);

                departmentElement.appendChild(employeeElement);
            }

            transFactory = TransformerFactory.newInstance();
            transformer = transFactory.newTransformer();
            source = new DOMSource(doc);
            result = new StreamResult(XMLFile);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (DOMException e) {
            System.out.println(e.getMessage());
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Department getDepartment(long id) {
        try {
            docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(XMLFile);
            Element element = null;

            NodeList list = doc.getElementsByTagName("Department");
            for (int i = 0; i < list.getLength(); i++) {
                if (list.item(i).getAttributes().getNamedItem("id").getNodeValue().equals(String.valueOf(id))) {
                    element = (Element) list.item(i);
                }
            }
            Department dp = new Department(Long.valueOf(element.getAttribute("id")), element.getAttribute("name"));

            if (element.getChildNodes() != null) {
                NodeList childNodes = element.getChildNodes();
                for (int i = 0; i < childNodes.getLength(); i++) {
                    Node item = childNodes.item(i);
                    Employee em = new Employee(Long.valueOf(item.getAttributes().getNamedItem("id").getNodeValue()), item.getAttributes().getNamedItem("name").getNodeValue());
                    dp.addMitarbeiter(em);
                }

            }
            return dp;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


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

    private boolean checkXMLFileExists() {
        this.filePath = Paths.get(String.format("%s/%s", getXMLDir(), XML_FILE_NAME));
        return Files.exists(filePath);
    }

    private String getXMLDir() {
        return System.getProperty("user.dir") + "/assets/XML";
    }


}

