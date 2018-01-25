package com.hiekn.demo.test.design.filter;

import java.util.List;

public class OrCriteria implements Criteria {

    private Criteria criteria;
    private Criteria otherCriteria;

    public OrCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Employee> meetCriteria(List<Employee> persons) {
        List<Employee> firstCriteriaItems = criteria.meetCriteria(persons);
        List<Employee> otherCriteriaItems = otherCriteria.meetCriteria(persons);

        for (Employee person : otherCriteriaItems) {
            if (!firstCriteriaItems.contains(person)) {
                firstCriteriaItems.add(person);
            }
        }
        return firstCriteriaItems;
    }
}
