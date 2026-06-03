package io.github.ratewio.employeeparser.parser;

import io.github.ratewio.employeeparser.Employee;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class XMLEmployeeParser implements EmployeeParser{
    public List<Employee> parse(Reader reader) {
        List<Employee> employees = new ArrayList<>();

        try {
            // Создаем DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Парсим XML файл
            Document document = builder.parse(new InputSource(reader));

            // Нормализуем XML (убираем пробелы и переносы строк)
            document.getDocumentElement().normalize();

            // Получаем все элементы employee
            NodeList nodeList = document.getElementsByTagName("employee");

            // Проходим по каждому employee
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Извлекаем данные
                    long id = Long.parseLong(getElementValue(element, "id"));
                    String firstName = getElementValue(element, "firstName");
                    String lastName = getElementValue(element, "lastName");
                    String country = getElementValue(element, "country");
                    int age = Integer.parseInt(getElementValue(element, "age"));

                    // Создаем объект Employee и добавляем в список
                    Employee employee = new Employee(id, firstName, lastName, country, age);
                    employees.add(employee);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }

    // Вспомогательный метод для получения значения элемента
    private static String getElementValue(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }
}
