package com.dng.bank.app.utils;

import com.dng.bank.app.constant.PaymentPeriod;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class PaymentUtils {
	
	public static Date getDueDate(Date startDate, PaymentPeriod period) {
		switch (period) {
			case MONTH -> {
				return DateUtils.addMonths(startDate, 1);
			}
			case QUARTER -> {
				return DateUtils.addMonths(startDate, 3);
			}
			default -> {
				return startDate;
			}
		}
	}
	
	public static BigDecimal getAmountPayment(BigDecimal principal,
	                                          BigDecimal interest,
	                                          PaymentPeriod period) {
		
		BigDecimal eachPeriodInterest = interest;
		
		BigDecimal total = interest.add(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(100.00), 5, RoundingMode.DOWN);
		
		switch (period) {
			case MONTH -> eachPeriodInterest = total.multiply(principal).divide(BigDecimal.valueOf(12), 5, RoundingMode.DOWN);
			
			case QUARTER -> eachPeriodInterest = total.multiply(principal).divide(BigDecimal.valueOf(4), 5, RoundingMode.DOWN);
		}
		
		return eachPeriodInterest;
		
	}
}

