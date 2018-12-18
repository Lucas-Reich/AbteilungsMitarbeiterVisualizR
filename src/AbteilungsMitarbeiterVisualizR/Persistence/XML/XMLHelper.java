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
    private static final String XML_FILE_NAME = "abteilungsMitarbeiterVisualizR.xml";

    private DocumentBuilderFactory docFactory = null;
    private DocumentBuilder docBuilder = null;
    private Document doc = null;
    private File XMLFile = null;
    private TransformerFactory transFactory = null;
    private Transformer transformer = null;
    private DOMSource source = null;
    private StreamResult result = null;
    private Path filePath = null;

    private XMLHelper() {}

    public static XMLHelper init() {
        XMLHelper xmlHelper = new XMLHelper();
        if (!xmlHelper.checkXMLFileExists()) {
            xmlHelper.initializeXmlFile();
        } else {
            xmlHelper.XMLFile = new File(String.valueOf(xmlHelper.filePath));
        }

        return xmlHelper;
    }

    @Override
    public Department saveDepartment(Department department) {
        Element departmentElement = null;
        try {
            docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(XMLFile);

            //Check if department already exists
            NodeList nl = doc.getElementsByTagName("Department");
            for (int i = 0; i < nl.getLength(); i++) {
                if (nl.item(i).getAttributes().getNamedItem("name").getNodeValue().equals(department.getName())) {
                    System.out.println("Department with name " + department.getName() + " already exists");
                    return null;
                }
            }
            NodeList departmentNl = doc.getElementsByTagName("Departments");
            Element departments = (Element) departmentNl.item(0);
            NodeList departmentCounterNl = doc.getElementsByTagName("DepartmentCounter");
            Element departmentCounter = (Element) departmentCounterNl.item(0);

            departmentCounter.getAttributes().getNamedItem("counter").setNodeValue(String.valueOf(Integer.valueOf(departmentCounter.getAttribute("counter")) + 1));

            departmentElement = doc.createElement("Department");
            departments.appendChild(departmentElement);

            Attr attrDepartmentId = doc.createAttribute("id");
            attrDepartmentId.setValue(departmentCounter.getAttribute("counter"));
            departmentElement.setAttributeNode(attrDepartmentId);

            Attr attrDepartmentName = doc.createAttribute("name");
            attrDepartmentName.setValue(department.getName());
            departmentElement.setAttributeNode(attrDepartmentName);

            transFactory = TransformerFactory.newInstance();
            transformer = transFactory.newTransformer();
            source = new DOMSource(doc);
            result = new StreamResult(XMLFile);
            transformer.transform(source, result);

        } catch (ParserConfigurationException | DOMException e) {
            System.out.println(e.getMessage());
        } catch (TransformerException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return Department.init(Long.valueOf(departmentElement.getAttribute("id")), departmentElement.getAttribute("name"));
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
            if (element != null) {
                dp = Department.init(Long.valueOf(element.getAttribute("id")), element.getAttribute("name"));
                if (element.getChildNodes() != null) {
                    addEmployeesToDepartment(element, dp);
                }
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
                    Department dp = Department.init(Long.valueOf(element.getAttribute("id")), element.getAttribute("name"));
                    if (element.getChildNodes() != null) {
                        addEmployeesToDepartment(element, dp);
                    }
                    departments.add(dp);
                }

            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
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


        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
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

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Employee saveEmployee(Employee employee, long departmentId) {
        docFactory = DocumentBuilderFactory.newInstance();
        Element employeeElement = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(XMLFile);

            NodeList employeeCounterNl = doc.getElementsByTagName("EmployeeCounter");
            Element employeeCounter = (Element) employeeCounterNl.item(0);

            employeeCounter.getAttributes().getNamedItem("counter").setNodeValue(String.valueOf(Integer.valueOf(employeeCounter.getAttribute("counter")) + 1));

            NodeList employeesNl = doc.getElementsByTagName("Employees");
            Element employees = (Element) employeesNl.item(0);

            employeeElement = doc.createElement("Employee");
            employees.appendChild(employeeElement);

            Attr attrEmployeeId = doc.createAttribute("id");
            attrEmployeeId.setValue(employeeCounter.getAttribute("counter"));
            employeeElement.setAttributeNode(attrEmployeeId);

            Attr attrEmployeeName = doc.createAttribute("name");
            attrEmployeeName.setValue(employee.getName());
            employeeElement.setAttributeNode(attrEmployeeName);

            NodeList departmentsNl = doc.getElementsByTagName("Department");
            if (departmentsNl != null) {
                for (int i = 0; i < departmentsNl.getLength(); i++) {
                    if (departmentsNl.item(i).getAttributes().getNamedItem("id").getNodeValue().equals(String.valueOf(departmentId))) {
                        Element department = (Element) departmentsNl.item(i);
                        Element dpEmployeeElement = doc.createElement("Employee");

                        Attr dpEmployeeId = doc.createAttribute("id");
                        dpEmployeeId.setValue(attrEmployeeId.getValue());
                        dpEmployeeElement.setAttributeNode(dpEmployeeId);

                        Attr dpEmployeeName = doc.createAttribute("name");
                        dpEmployeeName.setValue(attrEmployeeName.getValue());
                        dpEmployeeElement.setAttributeNode(dpEmployeeName);

                        department.appendChild(dpEmployeeElement);
                    }
                }
            }



            transFactory = TransformerFactory.newInstance();
            transformer = transFactory.newTransformer();
            source = new DOMSource(doc);
            result = new StreamResult(XMLFile);
            transformer.transform(source, result);

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }

        return Employee.init(Long.valueOf(employeeElement.getAttribute("id")), employeeElement.getAttribute("name"));
    }

    @Override
    public Employee getEmployee(long id) {
        Employee em = null;
        try {
            docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(XMLFile);

            NodeList list = doc.getElementsByTagName("Employee");
            for (int i = 0; i < list.getLength(); i++) {
                if (list.item(i).getAttributes().getNamedItem("id").getNodeValue().equals(String.valueOf(id))) {
                    Element element = (Element) list.item(i);
                    em = Employee.init(Long.valueOf(element.getAttribute("id")), element.getAttribute("name"));
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return em;
    }

    @Override
    public List<Employee> getEmployees(long id) {
        List<Employee> employeesList = new ArrayList<>();
        docFactory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(XMLFile);

            NodeList nl = doc.getElementsByTagName("Department");
            if(nl != null){
                for(int i = 0; i < nl.getLength(); i++){
                    Element department = (Element) nl.item(i);
                    if(Long.valueOf(department.getAttribute("id")).equals(id)){
                        NodeList children = department.getChildNodes();
                        if (children != null) {
                            for (int j = 0; j < children.getLength(); j++) {
                                Element employeeElement = (Element) children.item(j);
                                Employee employee = Employee.init(Long.valueOf(employeeElement.getAttribute("id")), employeeElement.getAttribute("name"));

                                employeesList.add(employee);
                            }
                        }
                    }

                }
            }


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return employeesList;
    }

    @Override
    public void updateEmployee(Employee employee) {
        docFactory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(XMLFile);
            NodeList nl = doc.getElementsByTagName("Employee");
            if (nl != null) {
                for (int i = 0; i < nl.getLength(); i++) {
                    Element element = (Element) nl.item(i);
                    if (element.getAttribute("id").equals(String.valueOf(employee.getId()))) {
                        element.getAttributes().getNamedItem("id").setNodeValue(String.valueOf(employee.getId()));
                        element.getAttributes().getNamedItem("name").setNodeValue(employee.getName());
                    }
                }
            }
            transFactory = TransformerFactory.newInstance();
            transformer = transFactory.newTransformer();
            source = new DOMSource(doc);
            result = new StreamResult(XMLFile);
            transformer.transform(source, result);


        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(long employeeId) {
        docFactory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(XMLFile);

            NodeList nl = doc.getElementsByTagName("Employee");
            for (int i = 0; i < nl.getLength(); i++) {
                Element element = (Element) nl.item(i);
                if (element.getAttribute("id").equals(String.valueOf(employeeId))) {
                    element.getParentNode().removeChild(element);
                }
            }
            transFactory = TransformerFactory.newInstance();
            transformer = transFactory.newTransformer();
            source = new DOMSource(doc);
            result = new StreamResult(XMLFile);
            transformer.transform(source, result);

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }

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

            Element dataSet = doc.createElement("DataSet");
            doc.appendChild(dataSet);

            Element departmentCounter = doc.createElement("DepartmentCounter");
            Attr attrDepartmentCounter = doc.createAttribute("counter");
            attrDepartmentCounter.setValue(String.valueOf(0));
            departmentCounter.setAttributeNode(attrDepartmentCounter);
            dataSet.appendChild(departmentCounter);

            Element employeeCounter = doc.createElement("EmployeeCounter");
            Attr attrEmployeeCounter = doc.createAttribute("counter");
            attrEmployeeCounter.setValue(String.valueOf(0));
            employeeCounter.setAttributeNode(attrEmployeeCounter);
            dataSet.appendChild(employeeCounter);

            Element departments = doc.createElement("Departments");
            dataSet.appendChild(departments);

            Element employees = doc.createElement("Employees");
            dataSet.appendChild(employees);

            transFactory = TransformerFactory.newInstance();
            transformer = transFactory.newTransformer();
            source = new DOMSource(doc);
            result = new StreamResult(XMLFile);
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException e) {
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
            Employee em = Employee.init(Long.valueOf(childElement.getAttribute("id")), childElement.getAttribute("name"));
            department.addEmployee(em);
        }
    }

}

