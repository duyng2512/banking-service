package com.dng.bank.app.dto;

import com.dng.bank.app.constant.PaymentPeriod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A DTO for the {@link com.dng.bank.app.entity.Payment} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PaymentDto implements Serializable {
	private Long createAt;
	private Long updateAt;
	private LoanDto loan;
	private Date startDate;
	private Date dueDate;
	private BigDecimal amount;
	private String currency;
	private boolean overdue;
	private PaymentPeriod paymentPeriod;
	
	
	@Override
	public String toString() {
		return "{" +
			       " loan=" + loan.getAmount() +
			       ", dueDate=" + new SimpleDateFormat("dd-MM-yyyy").format(dueDate) +
			       ", amount=" + amount +
			       ", currency='" + currency +
			       ", overdue=" + overdue +
			       ", period=" + paymentPeriod +
			       '}';
	}
}