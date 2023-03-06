package com.dng.bank.app.controller;

import com.dng.bank.app.controller.core.BaseEntityController;
import com.dng.bank.app.dto.ApplicantDto;
import com.dng.bank.app.entity.Applicant;
import com.dng.bank.app.service.core.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/applicant")
public class ApplicationController extends BaseEntityController<Applicant, ApplicantDto> {
	
	public ApplicationController(BaseService<Applicant, ApplicantDto> baseEntityService) {
		super(baseEntityService);
	}
	
}
