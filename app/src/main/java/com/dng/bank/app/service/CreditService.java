package com.dng.bank.app.service;

import com.dng.bank.app.dto.CreditDto;
import com.dng.bank.app.dto.Response;
import com.dng.bank.app.entity.Applicant;
import com.dng.bank.app.entity.Credit;
import com.dng.bank.app.repository.ApplicantRepository;
import com.dng.bank.app.repository.core.BaseRepository;
import com.dng.bank.app.service.core.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CreditService extends BaseService<Credit, CreditDto> {
	private final ApplicantRepository applicantRepository;
	
	public CreditService(BaseRepository<Credit, Long> repository,
	                     ModelMapper modelMapper,
	                     ApplicantRepository applicantRepository) {
		super(repository, modelMapper);
		this.applicantRepository = applicantRepository;
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
		Optional<Applicant> optional = applicantRepository.findById(applicantId);
		if (optional.isPresent()) {
			Applicant applicant = optional.get();
			Credit credit = modelMapper.map(dto, Credit.class);
			credit.setApplicant(applicant);
			Credit newCredit = repository.save(credit);
			
			return Response.builder()
				       .data(newCredit.getId())
				       .message("Successfully create new credit limit = " + credit.getTotalLimit())
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