package com.dng.bank.app.controller;

import com.dng.bank.app.controller.core.BaseEntityController;
import com.dng.bank.app.dto.PayRequestDto;
import com.dng.bank.app.dto.PaymentDto;
import com.dng.bank.app.dto.Response;
import com.dng.bank.app.entity.Payment;
import com.dng.bank.app.service.PaymentService;
import com.dng.bank.app.service.core.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment/*")
public class PaymentController extends BaseEntityController<Payment, PaymentDto> {
	
	public PaymentController(BaseService<Payment, PaymentDto> baseEntityService) {
		super(baseEntityService);
	}
	
	@PostMapping("do-payment/")
	public ResponseEntity<Object> paymentForApplication(@RequestBody PayRequestDto requestBody) {
		Response response = ((PaymentService) this.baseEntityService).payLoan(requestBody);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("get-payment/{applicationId}")
	public ResponseEntity<Object> getPaymentForApplication(@PathVariable("applicationId") String applicationId) {
		Response response = ((PaymentService) this.baseEntityService).getPayment(Long.valueOf(applicationId));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
}
