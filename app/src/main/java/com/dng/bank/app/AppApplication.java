package com.dng.bank.app;

import com.dng.bank.app.entity.Payment;
import com.dng.bank.app.service.ApplicantService;
import com.dng.bank.app.service.CreditService;
import com.dng.bank.app.service.LoanService;
import com.dng.bank.app.service.PaymentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AppApplication {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context =  SpringApplication.run(AppApplication.class, args);
		ApplicantService applicantService = context.getBean(ApplicantService.class);
		System.out.println(applicantService.findAll());
		
		CreditService creditService = context.getBean(CreditService.class);
		System.out.println(creditService.findAll());
		
		LoanService loanService = context.getBean(LoanService.class);
		System.out.println(loanService.findAll());
		
		PaymentService paymentService = context.getBean(PaymentService.class);
		System.out.println(paymentService.findAll());
	}
	
}
