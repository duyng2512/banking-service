package com.dng.bank.app.repository;

import com.dng.bank.app.entity.Applicant;
import com.dng.bank.app.entity.Loan;
import com.dng.bank.app.entity.Payment;
import com.dng.bank.app.repository.core.BaseLongPrimaryKeyRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends BaseLongPrimaryKeyRepository<Payment> {
	
	@Query("select p from Payment p where p.loan = ?1 order by p.createAt DESC")
	List<Payment> findAllByLoan(Loan loan);
	
	@Query("select p from Payment p where p.applicant = ?1 order by p.createAt DESC")
	List<Payment> findAllByApplicant(Applicant applicant);
	
}
