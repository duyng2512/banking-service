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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credit/*")
public class CreditController extends BaseEntityController<Credit, CreditDto> {
	
	public CreditController(BaseService<Credit, CreditDto> baseEntityService) {
		super(baseEntityService);
	}
	
	@PostMapping("new/{id}")
	public ResponseEntity<Object> newCreditForApplication(@PathVariable("id") String applicationId, @RequestBody CreditDto creditDto) {
		Response response = ((CreditService) this.baseEntityService).newCreditForApplicant(Long.valueOf(applicationId), creditDto);
		;
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
}
