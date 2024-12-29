package com.incode.companyregistryservice.web.mapper;

import com.incode.companyregistryservice.api.web.model.PaginationResponse;
import com.incode.companyregistryservice.api.web.model.PremiumCompaniesResponse;
import com.incode.companyregistryservice.api.web.model.PremiumCompanyResponse;
import com.incode.companyregistryservice.dto.PremiumCompanyDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class PremiumCompanyMapper {

    public PremiumCompaniesResponse fromDtoToResponse(Page<PremiumCompanyDto> companyPage) {
        PremiumCompaniesResponse result = new PremiumCompaniesResponse();
        List<PremiumCompanyResponse> collected = companyPage.stream()
                .map(this::fromDtoToResponse)
                .collect(Collectors.toList());
        result.setCompanies(collected);
        result.setPagination(createPaginationData(companyPage));
        return result;
    }

    public PremiumCompanyResponse fromDtoToResponse(PremiumCompanyDto dto) {
        PremiumCompanyResponse response = new PremiumCompanyResponse();
        response.setCompanyName(dto.companyName());
        response.setCompanyFullAddress(dto.fullAddress());
        response.setCompanyIdentificationNumber(dto.companyIdentificationNumber());
        response.setIsActive(dto.isActive());
        response.setRegistrationDate(dto.registrationDate());
        return response;
    }

    private PaginationResponse createPaginationData(Page companiesPage) {
        PaginationResponse result = new PaginationResponse();
        result.setPageNumber(companiesPage.getNumber());
        result.setPageSize(companiesPage.getNumberOfElements());
        result.setTotalPages(companiesPage.getTotalPages());
        result.setTotalElements((int)companiesPage.getTotalElements());
        return result;
    }
}
