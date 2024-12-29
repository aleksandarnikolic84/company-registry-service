package com.incode.companyregistryservice.service.mapper;

import com.incode.companyregistryservice.domain.FreeCompany;
import com.incode.companyregistryservice.dto.FreeCompanyDto;
import org.springframework.stereotype.Component;

@Component
public class FreeCompanyServiceMapper {

    public FreeCompanyDto fromDomainToDto(FreeCompany domain) {
        return FreeCompanyDto.builder()
                .id(domain.getId())
                .companyName(domain.getCompanyName())
                .address(domain.getAddress())
                .cin(domain.getCin())
                .isActive(domain.getIsActive())
                .createdTime(domain.getCreatedTime())
                .updatedTime(domain.getUpdatedTime())
                .registrationDate(domain.getRegistrationDate())
                .build();

    }
}
