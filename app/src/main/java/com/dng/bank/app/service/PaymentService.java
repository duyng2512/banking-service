package com.dng.bank.app.service;

import com.dng.bank.app.dto.PaymentDto;
import com.dng.bank.app.dto.Response;
import com.dng.bank.app.entity.Loan;
import com.dng.bank.app.entity.Payment;
import com.dng.bank.app.repository.LoanRepository;
import com.dng.bank.app.repository.core.BaseRepository;
import com.dng.bank.app.service.core.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PaymentService extends BaseService<Payment, PaymentDto> {
	
	private final LoanRepository loanRepository;
	
	public PaymentService(BaseRepository<Payment, Long> repository, ModelMapper modelMapper, LoanRepository loanRepository) {
		super(repository, modelMapper);
		this.loanRepository = loanRepository;
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
	public Response makePaymentForLoan(Long loanId, PaymentDto dto) {
		
		Payment payment = modelMapper.map(dto, Payment.class);
		Optional<Loan> optional = loanRepository.findById(loanId);
		
		if (optional.isPresent()) {
			
			Payment newPayment = repository.save(payment);
			Loan loan = optional.get();
			loan.setAmount(loan.getAmount().subtract(dto.getAmount()));
			loan.setPaid(loan.getAmount().equals(BigDecimal.ZERO));
			
			loanRepository.save(loan);
			
			return Response.builder()
				       .data(newPayment.getId())
				       .message("Success payment remain loan amount = " + loan.getAmount())
				       .success(true)
				       .build();
			
		} else {
			return Response.builder()
				       .message(String.format("Loan ID = %s not found", loanId))
				       .success(false)
				       .build();
		}
		
	}
	
}