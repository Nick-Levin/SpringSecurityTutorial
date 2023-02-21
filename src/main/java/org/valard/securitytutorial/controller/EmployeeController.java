package org.valard.securitytutorial.controller;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.valard.securitytutorial.model.Employee;
import org.valard.securitytutorial.service.EmployeesService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private static final String SERVICE_NAME = "employees.controller";
    private final EmployeesService employeesService;
    private final ObservationRegistry registry;

    @GetMapping(
            name = "get all employees",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployeesList() {
        return Observation.createNotStarted("employees.controller", registry)
                .observe(employeesService::getEmployees);
    }

    @GetMapping(
            name = "get employee by id",
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployee(@PathVariable String id) {
        return Observation.createNotStarted("employees.controller", registry)
                .observe(() -> employeesService.getEmployee(id));
    }

    @PatchMapping(
            name = "update employee",
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity updateEmployee(
            @RequestBody Employee employee
    ) {
        return Observation.createNotStarted("employees.controller", registry)
                .observe(() -> {
                    employeesService.updateEmployee(employee);
                    return ResponseEntity.ok("employee updated");
                });
    }

    @DeleteMapping(
            name = "delete employee",
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity deleteEmployee(
            @PathVariable String id
    ) {
        return Observation.createNotStarted("employees.controller", registry)
                .observe(() -> {
                    employeesService.deleteEmployee(id);
                    return ResponseEntity.ok("deleted successfully");
                });
    }

    @PostMapping(
            name = "update employee",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(
            @RequestBody Employee employee
    ) {
        return Observation.createNotStarted("employees.controller", registry)
                .observe(() -> employeesService.addEmployee(employee));
    }

}
