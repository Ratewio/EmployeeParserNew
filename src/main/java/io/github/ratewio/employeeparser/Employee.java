package io.github.ratewio.employeeparser;

import java.util.Objects;

public class Employee {
    public long id;
    public String firstName;
    public String lastName;
    public String country;
    public int age;

    public Employee() {
        // Пустой конструктор
    }

    public Employee(long id, String firstName, String lastName, String country, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id = " + id +
                ", firstName = " + firstName +
                ", lastName = " + lastName +
                ", country = " + country +
                ", age = " + age +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Employee employee)) return false;
        return id == employee.id && age == employee.age && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(country, employee.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, country, age);
    }
}
