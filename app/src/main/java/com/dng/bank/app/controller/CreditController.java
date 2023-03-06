package com.dng.bank.app.controller;

import com.dng.bank.app.controller.core.BaseEntityController;
import com.dng.bank.app.dto.CreditDto;
import com.dng.bank.app.dto.Response;
import com.dng.bank.app.entity.Credit;
import com.dng.bank.app.service.CreditService;
import com.dng.bank.app.service.core.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credit/*")
public class CreditController extends BaseEntityController<Credit, CreditDto> {
	
	public CreditController(BaseService<Credit, CreditDto> baseEntityService) {
		super(baseEntityService);
	}
	
	@PostMapping("create/{applicantId}")
	public ResponseEntity<Object> newCreditForApplication(@PathVariable("applicantId") String applicationId, @RequestBody CreditDto creditDto) {
		Response response = ((CreditService) this.baseEntityService).setCreditForApplicant(Long.valueOf(applicationId), creditDto);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("get/{applicantId}")
	public ResponseEntity<Object> getCreditForApplication(@PathVariable("applicantId") String applicationId) {
		Response response = ((CreditService) this.baseEntityService).getCreditForApplicant(Long.valueOf(applicationId));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
}
