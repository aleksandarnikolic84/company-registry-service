package com.incode.companyregistryservice.dto;

import lombok.Builder;

import java.time.Instant;
import java.time.LocalDate;

@Builder
public record FreeCompanyDto(Long id, String cin, String companyName, LocalDate registrationDate, String address,
                             Boolean isActive, Instant createdTime, Instant updatedTime
) {
}
