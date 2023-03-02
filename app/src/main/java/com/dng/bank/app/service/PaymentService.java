package com.dng.bank.app.service;

import com.dng.bank.app.dto.PaymentDto;
import com.dng.bank.app.entity.Payment;
import com.dng.bank.app.service.core.BaseService;
import org.springframework.stereotype.Service;

@Service
public class PaymentService extends BaseService<Payment, PaymentDto> {
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
}