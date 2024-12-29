package com.incode.companyregistryservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "premium_company")
public class PremiumCompany extends BaseEntity {

    @Column(name = "company_identification_number")
    private String companyIdentificationNumber;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "full_address")
    private String fullAddress;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
}
