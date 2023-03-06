package com.dng.bank.app.dto;

import com.dng.bank.app.constant.CreditType;
import com.dng.bank.app.constant.PaymentPeriod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.time.DateUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * A DTO for the {@link com.dng.bank.app.entity.Loan} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LoanDto implements Serializable {
	private ApplicantDto applicant;
	private CreditDto credit;
	private BigDecimal amount;
	private String currency;
	private Date startDate = new Date();
	private Date endDate = DateUtils.addDays(new Date(), 365);
	private BigDecimal interest;
	private boolean overdue = false;
	private CreditType creditType;
	private PaymentPeriod paymentPeriod;
}