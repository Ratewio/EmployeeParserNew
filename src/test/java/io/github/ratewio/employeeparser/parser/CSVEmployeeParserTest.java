package io.github.ratewio.employeeparser.parser;

import io.github.ratewio.employeeparser.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CSVEmployeeParserTest {
    CSVEmployeeParser parser = new CSVEmployeeParser();

    @CsvSource({
            "1,John,Smith,USA,25",
            "2,Inav,Petrov,RU,23",
            "3,Sergey,Bolshoi,BL,30",
            "4,Josh,White,CA,35"
    })
    @ParameterizedTest
    void testCSVParsing(long id, String firstName, String lastName, String country, int age){

        //expected
        Employee expected = new Employee(id, firstName, lastName, country, age);

        //actual
        Employee actual = parser.parse(String.format("%d,%s,%s,%s,%d", id, firstName, lastName, country, age)).getFirst();

        Assertions.assertEquals(expected, actual);
    }
}
