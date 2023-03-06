package com.dng.bank.app.config;

import com.dng.bank.app.constant.CreditType;
import com.dng.bank.app.entity.Applicant;
import com.dng.bank.app.entity.Credit;
import com.dng.bank.app.repository.ApplicantRepository;
import com.dng.bank.app.repository.CreditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
@AllArgsConstructor
@Slf4j
public class AppBoostrap implements CommandLineRunner {
	
	private final ApplicantRepository applicantRepository;
	private final CreditRepository creditRepository;
	
	@Override
	public void run(String... args) {
		
		Applicant applicant = Applicant.builder()
			                      .idNumber("333_000_111")
			                      .name("Dave James")
			                      .build();
		
		applicant = applicantRepository.save(applicant);
		
		Credit credit = Credit.builder()
			                .applicant(applicant)
			                .creditRegNumber("000_000_100")
			                .creditType(CreditType.CAR)
			                .currency("VND")
			                .startDate(new Date())
			                .endDate(DateUtils.addDays(new Date(), 365))
			                .totalLimit(new BigDecimal("1000"))
			                .valid(true)
			                .build();
		
		credit = creditRepository.save(credit);
		log.info("Init data Applicant ID {} Credit ID {}", applicant.getId(), credit.getId());
	}
}
