package com.dng.bank.app.controller;

import com.dng.bank.app.controller.core.BaseEntityController;
import com.dng.bank.app.dto.LoanDto;
import com.dng.bank.app.dto.Response;
import com.dng.bank.app.entity.Loan;
import com.dng.bank.app.service.LoanService;
import com.dng.bank.app.service.core.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loan")
public class LoanController extends BaseEntityController<Loan, LoanDto> {
	
	public LoanController(BaseService<Loan, LoanDto> baseEntityService) {
		super(baseEntityService);
	}
	
	@PostMapping("create/{applicationId}")
	public ResponseEntity<Object> createLoanForApplication(@PathVariable("applicationId") String applicationId,
	                                                       @RequestBody LoanDto loanDto) {
		Response response = ((LoanService) this.baseEntityService).newLoanForApplicant(Long.valueOf(applicationId), loanDto);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("get/{applicationId}")
	public ResponseEntity<Object> getLoanForApplication(@PathVariable("applicationId") String applicationId) {
		Response response = ((LoanService) this.baseEntityService).getLoanOfApplicantId(Long.valueOf(applicationId));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
}
