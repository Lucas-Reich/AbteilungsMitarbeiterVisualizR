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
import java.util.ArrayList;
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
            initializeXmlFile();
        } else {
            this.XMLFile = new File(String.valueOf(filePath));
        }
    }

    @Override
    public Department saveDepartment(Department department) {
        Element departmentCounter = null;
        Element employeeCounter = null;
        Element departments = null;
        try {
            docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(XMLFile);

            //Check if department already exists
            NodeList nl = doc.getElementsByTagName("Department");
            for (int i = 0; i < nl.getLength(); i++) {
                if (nl.item(i).getAttributes().getNamedItem("name").getNodeValue().equals(department.getName())) {
                    System.out.println("Department with name " + department.getName() + " already exists");
                    //todo throw new Exception??
                }
            }
            departments = (Element) doc.getElementsByTagName("Departments");
            departmentCounter = (Element) doc.getElementsByTagName("DepartmentCounter");

            Element departmentElement = doc.createElement("Department");
            departments.appendChild(departmentElement);

            Attr attrDepartmentId = doc.createAttribute("id");
            attrDepartmentId.setValue(String.valueOf(department.getId()));
            departmentElement.setAttributeNode(attrDepartmentId);

            Attr attrDepartmentName = doc.createAttribute("name");
            attrDepartmentName.setValue(department.getName());
            departmentElement.setAttributeNode(attrDepartmentName);

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
        Department dp = null;
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
            dp = new Department(Long.valueOf(element.getAttribute("id")), element.getAttribute("name"));
            if (element.getChildNodes() != null) {
                addEmployeesToDepartment(element, dp);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return dp;
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        docFactory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(XMLFile);
            NodeList nl = doc.getElementsByTagName("Department");
            if (nl != null) {
                for (int i = 0; i < nl.getLength(); i++) {
                    Element element = (Element) nl.item(i);
                    Department dp = new Department(Long.valueOf(element.getAttribute("id")), element.getAttribute("name"));
                    if (element.getChildNodes() != null) {
                        addEmployeesToDepartment(element, dp);
                    }
                    departments.add(dp);
                }

            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public void updateDepartment(Department department) {
        docFactory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(XMLFile);
            NodeList nl = doc.getElementsByTagName("Department");
            if (nl != null) {
                for (int i = 0; i < nl.getLength(); i++) {
                    Element element = (Element) nl.item(i);
                    if (element.getAttribute("id").equals(String.valueOf(department.getId()))) {
                        element.getAttributes().getNamedItem("id").setNodeValue(String.valueOf(department.getId()));
                        element.getAttributes().getNamedItem("name").setNodeValue(department.getName());
                    }
                }
            }
            transFactory = TransformerFactory.newInstance();
            transformer = transFactory.newTransformer();
            source = new DOMSource(doc);
            result = new StreamResult(XMLFile);
            transformer.transform(source, result);


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteDepartment(long departmentId) {
        docFactory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(XMLFile);

            NodeList departments = doc.getElementsByTagName("Department");
            for (int i = 0; i < departments.getLength(); i++) {
                Element element = (Element) departments.item(i);
                if (element.getAttribute("id").equals(String.valueOf(departmentId))) {
                    element.getParentNode().removeChild(element);
                }
            }
            transFactory = TransformerFactory.newInstance();
            transformer = transFactory.newTransformer();
            source = new DOMSource(doc);
            result = new StreamResult(XMLFile);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

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
    public List<Employee> getEmployees(long id) {
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

    private void initializeXmlFile() {
        this.XMLFile = new File(String.format("%s/%s", getXMLDir(), XML_FILE_NAME));
        try {
            docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();

            Element departmentCounter = doc.createElement("DepartmentCounter");
            Attr attrDepartmentCounter = doc.createAttribute("counter");
            attrDepartmentCounter.setValue(String.valueOf(1));
            departmentCounter.setAttributeNode(attrDepartmentCounter);

            Element employeeCounter = doc.createElement("EmployeeCounter");
            Attr attrEmployeeCounter = doc.createAttribute("counter");
            attrEmployeeCounter.setValue(String.valueOf(1));
            employeeCounter.setAttributeNode(attrEmployeeCounter);

            Element departments = doc.createElement("Departments");
            doc.appendChild(departments);

            Element employees = doc.createElement("Employees");
            doc.appendChild(employees);

            transFactory = TransformerFactory.newInstance();
            transformer = transFactory.newTransformer();
            source = new DOMSource(doc);
            result = new StreamResult(XMLFile);
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private String getXMLDir() {
        return System.getProperty("user.dir") + "/assets/XML";
    }

    private void addEmployeesToDepartment(Element element, Department department) {
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Element childElement = (Element) childNodes.item(i);
            Employee em = new Employee(Long.valueOf(childElement.getAttribute("id")), childElement.getAttribute("name"));
            department.addEmployee(em);
        }
    }

}

