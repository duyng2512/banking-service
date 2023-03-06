package com.dng.bank.app.service;

import com.dng.bank.app.dto.ApplicantDto;
import com.dng.bank.app.entity.Applicant;
import com.dng.bank.app.repository.core.BaseRepository;
import com.dng.bank.app.service.core.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ApplicantService extends BaseService<Applicant, ApplicantDto> {
	public ApplicantService(BaseRepository<Applicant, Long> repository, ModelMapper modelMapper) {
		super(repository, modelMapper);
	}
	
	@Override
	public Class<ApplicantDto> getDtoType() {
		return ApplicantDto.class;
	}
	
	@Override
	public Class<Applicant> getEntityType() {
		return Applicant.class;
	}
	
	@Override
	public String getEntityName() {
		return "Applicant";
	}
}
