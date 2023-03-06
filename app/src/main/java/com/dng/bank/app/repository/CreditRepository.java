package com.dng.bank.app.repository;

import com.dng.bank.app.constant.CreditType;
import com.dng.bank.app.entity.Applicant;
import com.dng.bank.app.entity.Credit;
import com.dng.bank.app.repository.core.BaseLongPrimaryKeyRepository;

import java.util.List;
import java.util.Optional;

public interface CreditRepository extends BaseLongPrimaryKeyRepository<Credit> {
	
	List<Credit> findAllByApplicantId(Long applicantId);
	
	Optional<Credit> findFirstByApplicantAndCreditType(Applicant applicant, CreditType creditType);
	
}
