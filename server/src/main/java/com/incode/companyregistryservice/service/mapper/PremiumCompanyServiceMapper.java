package com.incode.companyregistryservice.service.mapper;

import com.incode.companyregistryservice.domain.PremiumCompany;
import com.incode.companyregistryservice.dto.PremiumCompanyDto;
import org.springframework.stereotype.Component;

@Component
public class PremiumCompanyServiceMapper {

    public PremiumCompanyDto fromDomainToDto(PremiumCompany domain) {
        return PremiumCompanyDto.builder()
                .id(domain.getId())
                .companyIdentificationNumber(domain.getCompanyIdentificationNumber())
                .companyName(domain.getCompanyName())
                .createdTime(domain.getCreatedTime())
                .fullAddress(domain.getFullAddress())
                .isActive(domain.getIsActive())
                .registrationDate(domain.getRegistrationDate())
                .updatedTime(domain.getUpdatedTime())
                .build();
    }
}
