package io.github.ratewio.employeeparser.parser;

import io.github.ratewio.employeeparser.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EmployeeParserTest {

    @Test
    void jsonTest(){
        String expected = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"firstName\": \"Josh\",\n" +
                "    \"lastName\": \"Doe\",\n" +
                "    \"country\": \"USA\",\n" +
                "    \"age\": 25\n" +
                "  }\n" +
                "]";

        Employee employee = new Employee(1, "Josh", "Doe", "USA", 25);

        String actual = EmployeeParser.listToJson(List.of(employee));

        //удаляем табуляцию, сноски и пробелы
        expected = expected.replaceAll("\\s+", "");
        actual = actual.replaceAll("\\s+", "");

        Assertions.assertEquals(expected, actual);

    }
}
