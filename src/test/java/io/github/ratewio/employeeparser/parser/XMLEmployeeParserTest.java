package io.github.ratewio.employeeparser.parser;

import io.github.ratewio.employeeparser.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class XMLEmployeeParserTest {
    XMLEmployeeParser parser = new XMLEmployeeParser();


    @ValueSource(strings = {"<staff>\n" +
            "    <employee>\n" +
            "        <id>1</id>\n" +
            "        <firstName>John</firstName>\n" +
            "        <lastName>Doe</lastName>\n" +
            "        <country>USA</country>\n" +
            "        <age>25</age>\n" +
            "    </employee>\n" +
            "</staff>"})
    @ParameterizedTest
    void testXMLParsing(String xml) {
        Employee expected = new Employee(1, "John", "Doe", "USA", 25);

        Employee actual = parser.parse(xml).getFirst();

        Assertions.assertEquals(expected, actual);
    }

    @ValueSource(strings = {"/<staff>"})
    @ParameterizedTest
    void testXMLParsingListZeroSize(String xml) {
        int expected = 0;
        int actual = parser.parse(xml).size();
        Assertions.assertEquals(expected, actual);
    }
}
