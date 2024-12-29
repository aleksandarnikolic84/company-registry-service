package com.incode.companyregistryservice.service.api;

import com.incode.companyregistryservice.dto.FreeCompanyDto;
import org.springframework.data.domain.Page;

public interface FreeCompanyService {

    Page<FreeCompanyDto> findAllByCin(String cin, Integer page, Integer size);
}
