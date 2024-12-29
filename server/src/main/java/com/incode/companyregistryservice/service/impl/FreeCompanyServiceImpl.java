package com.incode.companyregistryservice.service.impl;

import com.incode.companyregistryservice.domain.FreeCompany;
import com.incode.companyregistryservice.dto.FreeCompanyDto;
import com.incode.companyregistryservice.exception.CustomException;
import com.incode.companyregistryservice.exception.CustomExceptionKey;
import com.incode.companyregistryservice.repository.FreeCompanyRepository;
import com.incode.companyregistryservice.service.api.FreeCompanyService;
import com.incode.companyregistryservice.service.mapper.FreeCompanyServiceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Service
public class FreeCompanyServiceImpl implements FreeCompanyService {

    private final FreeCompanyRepository freeCompanyRepository;
    private final FreeCompanyServiceMapper mapper;
    private final Random random = new Random();

    @Override
    public Page<FreeCompanyDto> findAllByCin(String cin, Integer page, Integer size) {
        log.info("Find Free company for cin: {}, page: {}, size: {}", cin, page, size);

        if (random.nextInt(100) <= 40) {
            throw new CustomException(CustomExceptionKey.SERVICE_NOT_AVAILABLE, " Free Service unavailable");
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "cin"));
        Page<FreeCompany> freeCompanies = freeCompanyRepository.findByCinLike(cin, pageable);
        Page<FreeCompanyDto> resultPage = freeCompanies.map(mapper::fromDomainToDto);
        log.debug("Free companies for cin:{} are:{}", cin, resultPage);
        return resultPage;
    }
}
