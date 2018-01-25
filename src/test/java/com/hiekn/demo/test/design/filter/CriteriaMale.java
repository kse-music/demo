package com.hiekn.demo.test.design.filter;

import java.util.ArrayList;
import java.util.List;

public class CriteriaMale implements Criteria {

    @Override
    public List<Employee> meetCriteria(List<Employee> persons) {
        List<Employee> malePersons = new ArrayList<>();
        for (Employee person : persons) {
            if (person.getGender().equalsIgnoreCase("MALE")) {
                malePersons.add(person);
            }
        }
        return malePersons;
    }
}
