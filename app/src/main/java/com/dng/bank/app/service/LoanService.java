package com.dng.bank.app.service;

import com.dng.bank.app.dto.LoanDto;
import com.dng.bank.app.dto.Response;
import com.dng.bank.app.entity.Applicant;
import com.dng.bank.app.entity.Credit;
import com.dng.bank.app.entity.Loan;
import com.dng.bank.app.repository.CreditRepository;
import com.dng.bank.app.repository.core.BaseRepository;
import com.dng.bank.app.service.core.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LoanService extends BaseService<Loan, LoanDto> {
	
	private final ApplicantService applicantService;
	private final CreditRepository creditRepository;
	
	public LoanService(BaseRepository<Loan, Long> repository, ModelMapper modelMapper, ApplicantService applicantService, CreditRepository creditRepository) {
		super(repository, modelMapper);
		this.applicantService = applicantService;
		this.creditRepository = creditRepository;
	}
	
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
	
	public Response newLoanForApplicant(Long applicantId, LoanDto dto) {
		
		if (applicantService.exist(applicantId)) {
			
			Applicant applicant = applicantService.findByEntityId(applicantId);
			Loan loan = modelMapper.map(dto, Loan.class);
			loan.setApplicant(applicant);
			
			
			List<Credit> credits = creditRepository.findAllByApplicantId(applicantId);
			BigDecimal limit = credits.stream().map(Credit::getTotalLimit).reduce(new BigDecimal("0.0"), BigDecimal::add);
			
			if (limit.compareTo(loan.getAmount()) <= 0) {
				return Response.builder()
					       .message("Error Loan greater than current limit")
					       .success(false)
					       .build();
			}
			
			Loan newLoan = this.repository.save(loan);
			
			return Response.builder()
				       .data(newLoan.getId())
				       .message("Success")
				       .success(true)
				       .build();
		} else {
			return Response.builder()
				       .message(String.format("Application ID = %s not found", applicantId))
				       .success(false)
				       .build();
		}
		
	}
}