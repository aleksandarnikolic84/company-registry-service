package com.incode.companyregistryservice.web;


import com.incode.companyregistryservice.api.web.FreeCompaniesApi;
import com.incode.companyregistryservice.api.web.model.FreeCompaniesResponse;
import com.incode.companyregistryservice.dto.FreeCompanyDto;
import com.incode.companyregistryservice.service.api.FreeCompanyService;
import com.incode.companyregistryservice.web.mapper.FreeCompanyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class FreeCompaniesApiImpl implements FreeCompaniesApi {

    private final FreeCompanyService freeCompanyService;
    private final FreeCompanyMapper mapper;

    @Override
    public ResponseEntity<FreeCompaniesResponse> findPrimaryCompanies(String query, Integer page, Integer size) {

        Page<FreeCompanyDto> companyPage = freeCompanyService.findAllByCin(query, page, size);

        return ResponseEntity.ok(mapper.fromDtoToResponse(companyPage));
    }
}
