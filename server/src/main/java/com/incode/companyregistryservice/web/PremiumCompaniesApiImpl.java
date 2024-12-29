package com.incode.companyregistryservice.web;


import com.incode.companyregistryservice.api.web.PremiumCompaniesApi;
import com.incode.companyregistryservice.api.web.model.PremiumCompaniesResponse;
import com.incode.companyregistryservice.dto.PremiumCompanyDto;
import com.incode.companyregistryservice.service.api.PremiumCompanyService;
import com.incode.companyregistryservice.web.mapper.PremiumCompanyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class PremiumCompaniesApiImpl implements PremiumCompaniesApi {

    private final PremiumCompanyService premiumCompanyService;
    private final PremiumCompanyMapper mapper;

    @Override
    public ResponseEntity<PremiumCompaniesResponse> findPremiumCompanies(String query, Integer page, Integer size) {

        Page<PremiumCompanyDto> companyPage = premiumCompanyService.findByCompany(query, page, size);

        return ResponseEntity.ok(mapper.fromDtoToResponse(companyPage));
    }
}
