package com.incode.companyregistryservice.dto;

import lombok.Builder;

import java.time.Instant;
import java.time.LocalDate;

@Builder
public record PremiumCompanyDto(
        Long id,
        String companyIdentificationNumber,
        String companyName,
        LocalDate registrationDate,
        String fullAddress,
        Boolean isActive,
        Instant createdTime,
        Instant updatedTime
) {
}
