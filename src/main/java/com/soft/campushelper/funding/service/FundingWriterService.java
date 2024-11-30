package com.soft.campushelper.funding.service;

import com.soft.campushelper.funding.Funding;
import com.soft.campushelper.funding.repository.FundingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FundingWriterService {
    private final FundingRepository fundingRepository;

    @Transactional
    public void save(Funding funding) {
        fundingRepository.save(funding);
    }

    @Transactional
    public void delete(Funding funding) {
        fundingRepository.delete(funding);
    }
}
