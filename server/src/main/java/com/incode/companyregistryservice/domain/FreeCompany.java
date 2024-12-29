package com.incode.companyregistryservice.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "free_company")
public class FreeCompany extends BaseEntity {

    @Column(name = "cin")
    private String cin;

    @Column(name = "name")
    private String companyName;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "address")
    private String address;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
}
