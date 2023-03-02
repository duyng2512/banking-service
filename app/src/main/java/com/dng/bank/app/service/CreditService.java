package com.dng.bank.app.service;

import com.dng.bank.app.dto.CreditDto;
import com.dng.bank.app.entity.Credit;
import com.dng.bank.app.service.core.BaseService;
import org.springframework.stereotype.Service;

@Service
public class CreditService extends BaseService<Credit, CreditDto> {
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
}