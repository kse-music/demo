package com.hiekn.demo.test.design.filter;

import java.util.List;

public interface Criteria {
    List<Employee> meetCriteria(List<Employee> persons);
}
