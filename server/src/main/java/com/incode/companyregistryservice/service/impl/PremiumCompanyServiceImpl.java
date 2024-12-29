package com.incode.companyregistryservice.service.impl;

import com.incode.companyregistryservice.dto.PremiumCompanyDto;
import com.incode.companyregistryservice.exception.CustomException;
import com.incode.companyregistryservice.exception.CustomExceptionKey;
import com.incode.companyregistryservice.repository.PremiumCompanyRepository;
import com.incode.companyregistryservice.service.api.PremiumCompanyService;
import com.incode.companyregistryservice.service.mapper.PremiumCompanyServiceMapper;
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
public class PremiumCompanyServiceImpl implements PremiumCompanyService {

    private final PremiumCompanyRepository premiumCompanyRepository;
    private final PremiumCompanyServiceMapper mapper;
    private final Random random = new Random();

    @Override
    public Page<PremiumCompanyDto> findByCompany(String companyIdentificationNumber, Integer page, Integer size) {
        log.info("[Premium company] - find companyIdentificationNumber: {}, page: {}, size: {}",
                companyIdentificationNumber, page, size);

        if (random.nextInt(100) <= 40) {
            throw new CustomException(CustomExceptionKey.SERVICE_NOT_AVAILABLE, " Premium Service unavailable.");
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "companyIdentificationNumber"));
        Page<PremiumCompanyDto> resultPage = premiumCompanyRepository
                .findByCompanyIdentificationNumber(companyIdentificationNumber, pageable)
                .map(mapper::fromDomainToDto);

        log.debug("[Premium company] - for companyIdentificationNumber:{} are found:{}", companyIdentificationNumber,
                resultPage.getTotalElements());

        return resultPage;
    }
}
