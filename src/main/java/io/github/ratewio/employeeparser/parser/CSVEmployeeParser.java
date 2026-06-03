package io.github.ratewio.employeeparser.parser;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.ColumnPositionMappingStrategyBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import io.github.ratewio.employeeparser.Employee;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CSVEmployeeParser implements EmployeeParser{
    private static final String[] COLUMN_MAPPING = {"id", "firstName", "lastName", "country", "age"};

    @Override
    public List<Employee> parse(Reader reader) {
        try (CSVReader csvReader = new CSVReader(reader)) {
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategyBuilder<Employee>().build();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(COLUMN_MAPPING);

            CsvToBean<Employee> csvToBean = new CsvToBeanBuilder<Employee>(csvReader)
                    .withMappingStrategy(strategy)
                    .build();

            return csvToBean.parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
