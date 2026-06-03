package io.github.ratewio.employeeparser.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.github.ratewio.employeeparser.Employee;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public interface EmployeeParser {
    List<Employee> parse(Reader reader);
    default List<Employee> parse(File file){
        try(FileReader reader = new FileReader(file)) {
            return parse(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    default List<Employee> parse(String source){
        try(StringReader reader = new StringReader(source)) {
            return parse(reader);
        }
    }

    static String listToJson(List<Employee> list) {
        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting().create().newBuilder();
        Gson gson = gsonBuilder.create();
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();

        return gson.toJson(list, listType);
    }
}
