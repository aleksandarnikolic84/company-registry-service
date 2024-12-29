package com.incode.companyregistryservice.service.api;

import com.incode.companyregistryservice.dto.PremiumCompanyDto;
import org.springframework.data.domain.Page;

public interface PremiumCompanyService {

    Page<PremiumCompanyDto> findByCompany(String cin, Integer page, Integer size);
}
