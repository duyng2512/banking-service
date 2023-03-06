package com.dng.bank.app.repository;

import com.dng.bank.app.entity.Applicant;
import com.dng.bank.app.entity.Loan;
import com.dng.bank.app.repository.core.BaseLongPrimaryKeyRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoanRepository extends BaseLongPrimaryKeyRepository<Loan> {
	
	@Query("select l from Loan l where l.applicant = ?1 order by l.createAt desc")
	List<Loan> findAllByApplicant(Applicant applicant);
	
}
