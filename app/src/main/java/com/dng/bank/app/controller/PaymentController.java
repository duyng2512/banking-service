package com.dng.bank.app.controller;

import com.dng.bank.app.controller.core.BaseEntityController;
import com.dng.bank.app.dto.PaymentDto;
import com.dng.bank.app.dto.Response;
import com.dng.bank.app.entity.Payment;
import com.dng.bank.app.service.PaymentService;
import com.dng.bank.app.service.core.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment/*")
public class PaymentController extends BaseEntityController<Payment, PaymentDto> {
	
	public PaymentController(BaseService<Payment, PaymentDto> baseEntityService) {
		super(baseEntityService);
	}
	
	@PostMapping("new/{id}")
	public ResponseEntity<Object> newLoanForApplication(@PathVariable("id") String loadId, @RequestBody PaymentDto dto) {
		Response response = ((PaymentService) this.baseEntityService).doPaymentForLoan(Long.valueOf(loadId), dto);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
}
