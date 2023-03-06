package com.dng.bank.app.repository;

import com.dng.bank.app.entity.Credit;
import com.dng.bank.app.repository.core.BaseLongPrimaryKeyRepository;

import java.util.List;

public interface CreditRepository extends BaseLongPrimaryKeyRepository<Credit> {
	
	List<Credit> findAllByApplicantId(Long applicantId);
	
}
