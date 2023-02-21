package org.valard.securitytutorial.service;

import org.valard.securitytutorial.model.Employee;

import java.util.List;

public interface EmployeesService {

    List<Employee> getEmployees();
    Employee getEmployee(String id);
    void updateEmployee(Employee employee);
    void deleteEmployee(String id);
    Employee addEmployee(Employee employee);

}
