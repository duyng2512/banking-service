package com.dng.bank.app.service;

import com.dng.bank.app.dto.CreditDto;
import com.dng.bank.app.dto.Response;
import com.dng.bank.app.entity.Applicant;
import com.dng.bank.app.entity.Credit;
import com.dng.bank.app.repository.core.BaseRepository;
import com.dng.bank.app.service.core.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreditService extends BaseService<Credit, CreditDto> {
	private final ApplicantService applicantService;
	
	public CreditService(BaseRepository<Credit, Long> repository, ModelMapper modelMapper, ApplicantService applicantService) {
		super(repository, modelMapper);
		this.applicantService = applicantService;
	}
	
	
	@Override
	public Class<CreditDto> getDtoType() {
		return CreditDto.class;
	}
	
	@Override
	public Class<Credit> getEntityType() {
		return Credit.class;
	}
	
	@Override
	public String getEntityName() {
		return "Credit";
	}
	
	public Response newCreditForApplicant(Long applicantId, CreditDto dto) {
		
		if (applicantService.exist(applicantId)) {
			Applicant applicant = applicantService.findByEntityId(applicantId);
			Credit credit = modelMapper.map(dto, Credit.class);
			credit.setApplicant(applicant);
			Credit newCredit = repository.save(credit);
			
			return Response.builder()
				       .data(newCredit.getId())
				       .message("Success")
				       .success(true)
				       .build();
		} else {
			return Response.builder()
				       .message(String.format("Application ID = %s not found", applicantId))
				       .success(false)
				       .build();
		}
		
	}
}