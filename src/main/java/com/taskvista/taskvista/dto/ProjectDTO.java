package com.taskvista.taskvista.dto;

import java.time.LocalDate;

public record ProjectDTO(String name, String description, LocalDate startDate, LocalDate endDate) {
}
