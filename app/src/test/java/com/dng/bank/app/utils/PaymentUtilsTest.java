package com.dng.bank.app.utils;

import com.dng.bank.app.constant.PaymentPeriod;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

class PaymentUtilsTest {
	
	@Test
	void getDueDate() {
		Date now = new Date();
		Date date = PaymentUtils.getDueDate(now, PaymentPeriod.MONTH);
		Assertions.assertEquals(now.getMonth() + 1, date.getMonth());
	}
	
	@Test
	void getAmountPayment() {
		
		BigDecimal principal = new BigDecimal("1000");
		BigDecimal interest = new BigDecimal("12");
		
		BigDecimal eachMonth = PaymentUtils.getAmountPayment(principal, interest, PaymentPeriod.MONTH);
		System.out.println(eachMonth);
		
		BigDecimal eachQuarter = PaymentUtils.getAmountPayment(principal, interest, PaymentPeriod.QUARTER);
		System.out.println(eachQuarter);
		
		
	}
}