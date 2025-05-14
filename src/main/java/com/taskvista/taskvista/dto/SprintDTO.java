package com.taskvista.taskvista.dto;

import java.time.LocalDate;

public record SprintDTO (String name, String goal, LocalDate startDate, LocalDate endDate, Long projectId){
}
