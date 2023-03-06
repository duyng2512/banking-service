package com.dng.bank.app.service;

import com.dng.bank.app.dto.LoanDto;
import com.dng.bank.app.dto.Response;
import com.dng.bank.app.entity.Applicant;
import com.dng.bank.app.entity.Credit;
import com.dng.bank.app.entity.Loan;
import com.dng.bank.app.exception.EntityNotFoundException;
import com.dng.bank.app.repository.ApplicantRepository;
import com.dng.bank.app.repository.CreditRepository;
import com.dng.bank.app.repository.LoanRepository;
import com.dng.bank.app.repository.core.BaseRepository;
import com.dng.bank.app.service.core.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LoanService extends BaseService<Loan, LoanDto> {
	
	private final ApplicantRepository applicantRepository;
	private final CreditRepository creditRepository;
	private final PaymentService paymentService;
	
	public LoanService(BaseRepository<Loan, Long> repository,
	                   ModelMapper modelMapper,
	                   ApplicantRepository applicantRepository,
	                   CreditRepository creditRepository,
	                   PaymentService paymentService) {
		super(repository, modelMapper);
		this.applicantRepository = applicantRepository;
		this.creditRepository = creditRepository;
		this.paymentService = paymentService;
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
	
	@Transactional(propagation = Propagation.NESTED)
	public Response newLoanForApplicant(Long applicantId, LoanDto dto) {
		
		Applicant applicant = applicantRepository
			                      .findById(applicantId)
			                      .orElseThrow(() -> new EntityNotFoundException("Application not found ID " + applicantId));
		
		Credit credit = creditRepository.findFirstByApplicantAndCreditType(applicant, dto.getCreditType())
			                .orElseThrow(() -> new EntityNotFoundException("Credit type " + dto.getCreditType()
				                                                               + " not found for applicant "
				                                                               + applicantId));
		
		Loan loan = modelMapper.map(dto, Loan.class);
		loan.setApplicant(applicant);
		loan.setCredit(credit);
		
		BigDecimal limit = credit.getTotalLimit();
		
		
		if (limit.compareTo(loan.getAmount()) <= 0) {
			
			String response = String.format("Error loan=%f greater than current limit=%f for application id %s",
				loan.getAmount(),
				limit.floatValue(),
				applicantId
			);
			
			return Response.builder()
				       .message(response)
				       .success(false)
				       .build();
		} else {
			loan = this.repository.save(loan);
			
			Response paymentRes = paymentService.createPayment(loan, applicant);
			if (!paymentRes.isSuccess()) return paymentRes;
			
			String response = String.format("Successfully provide loan=%f to application id %s loan id %d",
				loan.getAmount(),
				applicantId,
				loan.getId()
			);
			
			return Response.builder()
				       .data(modelMapper.map(loan, LoanDto.class))
				       .message(response)
				       .addInfo("New payment " + paymentRes.getData().toString())
				       .success(true)
				       .build();
		}
	}
	
	public Response getLoanOfApplicantId(Long applicantId) {
		Applicant applicant = applicantRepository
			                      .findById(applicantId)
			                      .orElseThrow(() -> new EntityNotFoundException("Application not found ID " + applicantId));
		
		List<Loan> loans = ((LoanRepository) repository).findAllByApplicant(applicant);
		List<LoanDto> loanDto = loans.stream().map(s -> modelMapper.map(s, LoanDto.class)).toList();
		
		if (!loans.isEmpty()) {
			BigDecimal credit = loans.get(0).getCredit().getTotalLimit();
			BigDecimal totalLoan = loanDto.stream().map(LoanDto::getAmount).reduce(new BigDecimal("0"), BigDecimal::add);
			
			return Response.builder()
				       .data(loanDto)
				       .message("Success find total " + loanDto.size() + " loans, total value " + totalLoan)
				       .addInfo("Original credit limit: " + credit + " remain: " + credit.subtract(totalLoan))
				       .success(true)
				       .build();
		} else {
			return Response.builder()
				       .message("Success find total " + loanDto.size() + " loans, total value 0")
				       .success(true)
				       .build();
		}
	}
	
}