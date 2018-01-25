package com.hiekn.demo.test.design.filter;

import java.util.List;
import java.util.ArrayList;

public class FilterTest {

    public static void main(String[] args) {
        List<Employee> persons = new ArrayList<>();

        persons.add(new Employee("Tom", "Male", "YES"));
        persons.add(new Employee("Jack", "Male", "NO"));
        persons.add(new Employee("Jane", "Female", "NO"));
        persons.add(new Employee("Diana", "Female", "YES"));
        persons.add(new Employee("Mike", "Male", "NO"));
        persons.add(new Employee("Bob", "Male", "YES"));

        Criteria male = new CriteriaMale();
        Criteria female = new CriteriaFemale();
        Criteria retire = new CriteriaRetire();
        Criteria retireMale = new AndCriteria(retire, male);
        Criteria retireOrFemale = new OrCriteria(retire, female);

        System.out.println("Males: ");
        printPersons(male.meetCriteria(persons));

        System.out.println("Females: ");
        printPersons(female.meetCriteria(persons));

        System.out.println("Retire Males: ");
        printPersons(retireMale.meetCriteria(persons));

        System.out.println("Retire Or Females: ");
        printPersons(retireOrFemale.meetCriteria(persons));
    }

    public static void printPersons(List<Employee> persons) {
        for (Employee person : persons) {
            System.out.println(person);
        }
    }

}
