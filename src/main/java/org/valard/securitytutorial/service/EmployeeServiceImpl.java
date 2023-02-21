package org.valard.securitytutorial.service;

import org.springframework.stereotype.Service;
import org.valard.securitytutorial.model.Employee;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeesService{

    private static final List<Employee> EMPLOYEES;

    static {
        EMPLOYEES = List.of(
                Employee.builder()
                        .withId(UUID.randomUUID().toString())
                        .withName("Conor")
                        .withSalary(6000.68f)
                        .withScore(9)
                        .withBirthDate(LocalDateTime.now().minusYears(30))
                        .withAvgWorkDaysPerMonth(26)
                        .build(),
                Employee.builder()
                        .withId(UUID.randomUUID().toString())
                        .withName("Malcolm")
                        .withSalary(5600.68f)
                        .withScore(6)
                        .withBirthDate(LocalDateTime.now().minusYears(18))
                        .withAvgWorkDaysPerMonth(26)
                        .build(),
                Employee.builder()
                        .withId(UUID.randomUUID().toString())
                        .withName("Herald")
                        .withSalary(7900.68f)
                        .withScore(12)
                        .withBirthDate(LocalDateTime.now().minusYears(45))
                        .withAvgWorkDaysPerMonth(29)
                        .build(),
                Employee.builder()
                        .withId(UUID.randomUUID().toString())
                        .withName("Kyle")
                        .withSalary(2000.68f)
                        .withScore(2)
                        .withBirthDate(LocalDateTime.now().minusYears(70))
                        .withAvgWorkDaysPerMonth(8)
                        .build()
        );
    }

    @Override
    public List<Employee> getEmployees() {
        return EMPLOYEES;
    }

    @Override
    public Employee getEmployee(String id) {
        return findById(id).orElseThrow(() -> new RuntimeException("employee with id " + id + " not found"));
    }

    @Override
    public void updateEmployee(Employee employee) {
        deleteEmployee(employee.getId());
        addEmployee(employee);
    }

    @Override
    public void deleteEmployee(String id) {
        findById(id).ifPresent(EMPLOYEES::remove);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        EMPLOYEES.add(employee);
        return employee;
    }

    private Optional<Employee> findById(String id) {
        return EMPLOYEES.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst();
    }

}
