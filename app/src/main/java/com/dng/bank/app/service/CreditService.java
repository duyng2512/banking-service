package com.dng.bank.app.service;

import com.dng.bank.app.dto.CreditDto;
import com.dng.bank.app.dto.Response;
import com.dng.bank.app.entity.Applicant;
import com.dng.bank.app.entity.Credit;
import com.dng.bank.app.exception.EntityNotFoundException;
import com.dng.bank.app.repository.ApplicantRepository;
import com.dng.bank.app.repository.CreditRepository;
import com.dng.bank.app.repository.core.BaseRepository;
import com.dng.bank.app.service.core.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	
	@Transactional
	public Response setCreditForApplicant(Long applicantId, CreditDto dto) {
		Optional<Applicant> optional = applicantRepository.findById(applicantId);
		
		if (optional.isPresent()) {
			
			Applicant applicant = optional.get();
			Credit credit = modelMapper.map(dto, Credit.class);
			credit.setApplicant(applicant);
			Credit newCredit = repository.save(credit);
			
			String responseMsg = String.format(
				"Successfully create new credit limit = %d %s, ID = %d",
				credit.getTotalLimit().intValueExact(),
				credit.getCurrency(),
				credit.getId());
			
			return Response.builder()
				       .data(newCredit.getId())
				       .message(responseMsg)
				       .success(true)
				       .build();
		} else {
			throw new EntityNotFoundException(String.format("Application ID = %s not found", applicantId));
		}
	}
	
	@Transactional
	public Response getCreditForApplicant(Long applicantId) {
		Optional<Applicant> optional = applicantRepository.findById(applicantId);
		
		if (optional.isPresent()) {
			List<Credit> list = ((CreditRepository) repository).findAllByApplicantId(applicantId);
			List<CreditDto> dtoList = list.stream().map(s -> modelMapper.map(s, CreditDto.class)).toList();
			String responseMsg = String.format(
				"Successfully get credit of applicants ID = %d", applicantId);
			
			return Response.builder()
				       .data(dtoList)
				       .message(responseMsg)
				       .success(true)
				       .build();
		} else {
			throw new EntityNotFoundException(String.format("Application ID = %s not found", applicantId));
		}
	}
}