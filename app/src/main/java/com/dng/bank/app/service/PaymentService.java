package com.dng.bank.app.service;

import com.dng.bank.app.dto.PayRequestDto;
import com.dng.bank.app.dto.PaymentDto;
import com.dng.bank.app.dto.Response;
import com.dng.bank.app.entity.Applicant;
import com.dng.bank.app.entity.Loan;
import com.dng.bank.app.entity.Payment;
import com.dng.bank.app.exception.EntityNotFoundException;
import com.dng.bank.app.repository.ApplicantRepository;
import com.dng.bank.app.repository.LoanRepository;
import com.dng.bank.app.repository.PaymentRepository;
import com.dng.bank.app.repository.core.BaseRepository;
import com.dng.bank.app.service.core.BaseService;
import com.dng.bank.app.utils.PaymentUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class PaymentService extends BaseService<Payment, PaymentDto> {
	
	private final LoanRepository loanRepository;
	private final ApplicantRepository applicantRepository;
	
	public PaymentService(BaseRepository<Payment, Long> repository,
	                      ModelMapper modelMapper,
	                      LoanRepository loanRepository,
	                      ApplicantRepository applicantRepository) {
		super(repository, modelMapper);
		this.loanRepository = loanRepository;
		this.applicantRepository = applicantRepository;
	}
	
	@Override
	public Class<PaymentDto> getDtoType() {
		return PaymentDto.class;
	}
	
	@Override
	public Class<Payment> getEntityType() {
		return Payment.class;
	}
	
	@Override
	public String getEntityName() {
		return "Payment";
	}
	
	@Transactional
	public Response payLoan(PayRequestDto payRequest) {
		
		long applicantId = payRequest.getApplicantId();
		long loanId = payRequest.getApplicantId();
		long paymentId = payRequest.getPaymentId();
		BigDecimal amount = payRequest.getAmount();
		
		Applicant applicant = applicantRepository.findById(applicantId)
			                      .orElseThrow(() -> new EntityNotFoundException("Application id not found" + applicantId));
		
		Loan loan = loanRepository.findById(loanId)
			            .orElseThrow(() -> new EntityNotFoundException("Loan id not found " + loanId));
		
		Payment payment = repository.findById(paymentId)
			                  .orElseThrow(() -> new EntityNotFoundException("Payment id not found " + paymentId));
		
		if (amount.compareTo(payment.getAmount()) > 0) {
			throw new IllegalArgumentException("Payment amount greater than payment require " + payment.getAmount());
		} else {
			// Update
			payment.setAmount(BigDecimal.valueOf(payment.getAmount().subtract(amount).floatValue()));
			loan.setAmount(BigDecimal.valueOf(loan.getAmount().subtract(amount).floatValue()));
			payment.setApplicant(applicant);
			
			loan = loanRepository.save(loan);
			payment = repository.save(payment);
			
			return Response.builder()
				       .data(modelMapper.map(payment, PaymentDto.class))
				       .message("Success payment remain loan amount = " + loan.getAmount())
				       .success(true)
				       .build();
		}
	}
	
	
	@Transactional
	public Response createPayment(Loan loan, Applicant applicant) {
		try {
			Date startDate = new Date();
			Date dueDate = PaymentUtils.getDueDate(startDate, loan.getPaymentPeriod());
			BigDecimal amount = PaymentUtils.getAmountPayment(loan.getAmount(), loan.getInterest(), loan.getPaymentPeriod());
			
			Payment payment = Payment.builder()
				                  .loan(loan)
				                  .applicant(applicant)
				                  .startDate(startDate)
				                  .dueDate(dueDate)
				                  .amount(amount)
				                  .currency(loan.getCurrency())
				                  .overdue(false)
				                  .paymentPeriod(loan.getPaymentPeriod())
				                  .build();
			
			payment = repository.save(payment);
			
			log.info(payment.toString());
			
			return Response.builder()
				       .data(modelMapper.map(payment, PaymentDto.class))
				       .message("Success")
				       .success(true)
				       .build();
		} catch (Exception e) {
			return Response.builder()
				       .message(e.getMessage())
				       .success(false)
				       .build();
		}
	}
	
	@Transactional
	public Response getPayment(Long applicantId) {
		
		Applicant applicant = applicantRepository.findById(applicantId)
			                      .orElseThrow(() -> new EntityNotFoundException("Application id not found" + applicantId));
		
		List<Payment> payments = ((PaymentRepository) repository).findAllByApplicant(applicant);
		List<PaymentDto> paymentDto = payments.stream().map(s -> modelMapper.map(s, PaymentDto.class)).toList();
		
		BigDecimal totalPayment = paymentDto.stream().map(PaymentDto::getAmount).reduce(new BigDecimal("0"), BigDecimal::add);
		
		return Response.builder()
			       .data(paymentDto)
			       .message("Success find total " + paymentDto.size() + " payment, total value " + totalPayment)
			       .success(true)
			       .build();
		
	}
}