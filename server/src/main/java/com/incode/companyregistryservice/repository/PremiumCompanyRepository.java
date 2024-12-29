package com.incode.companyregistryservice.repository;

import com.incode.companyregistryservice.domain.PremiumCompany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PremiumCompanyRepository  extends JpaRepository<PremiumCompany, Long> {

    Page<PremiumCompany> findByCompanyIdentificationNumber(String companyIdentificationNumber, Pageable pageable);
}
