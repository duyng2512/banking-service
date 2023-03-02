package com.dng.bank.app.service;

import com.dng.bank.app.dto.LoanDto;
import com.dng.bank.app.entity.Loan;
import com.dng.bank.app.service.core.BaseService;
import org.springframework.stereotype.Service;

@Service
public class LoanService extends BaseService<Loan, LoanDto> {
	@Override
	public Class<LoanDto> getDtoType() {
		return LoanDto.class;
	}
	
	@Override
	public Class<Loan> getEntityType() {
		return Loan.class;
	}
	
	@Override
	public String getEntityName() {
		return "Loan";
	}
}