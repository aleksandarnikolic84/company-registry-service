package com.incode.companyregistryservice.web.mapper;

import com.incode.companyregistryservice.api.web.model.FreeCompaniesResponse;
import com.incode.companyregistryservice.api.web.model.FreeCompanyResponse;
import com.incode.companyregistryservice.api.web.model.PaginationResponse;
import com.incode.companyregistryservice.dto.FreeCompanyDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class FreeCompanyMapper {

    public FreeCompaniesResponse fromDtoToResponse(Page<FreeCompanyDto> companiesPage) {
        FreeCompaniesResponse result = new FreeCompaniesResponse();
        List<FreeCompanyResponse> freeCompanies = companiesPage.stream()
                .map(this::fromDtoToResponse)
                .collect(Collectors.toList());
        result.setCompanies(freeCompanies);
        result.setPagination(createPaginationData(companiesPage));
        return result;
    }

    public FreeCompanyResponse fromDtoToResponse(FreeCompanyDto dto) {
        FreeCompanyResponse response = new FreeCompanyResponse();
        response.setCin(dto.cin());
        response.setCompanyName(dto.companyName());
        response.setAddress(dto.address());
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
