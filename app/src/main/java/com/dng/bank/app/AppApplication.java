package com.dng.bank.app;

import com.dng.bank.app.service.ApplicantService;
import com.dng.bank.app.service.CreditService;
import com.dng.bank.app.service.LoanService;
import com.dng.bank.app.service.PaymentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AppApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
	
}
