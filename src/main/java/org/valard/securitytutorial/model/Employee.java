package org.valard.securitytutorial.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true, setterPrefix = "with")
@AllArgsConstructor
public class Employee {

    private String id;
    private String name;
    private LocalDateTime birthDate;
    private float salary;
    private int score;
    private int avgWorkDaysPerMonth;

}
