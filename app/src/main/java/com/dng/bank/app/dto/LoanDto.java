package com.dng.bank.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
	private Long createAt;
	private Long updateAt;
	private BigDecimal amount;
	private String currency;
	private Date startDate;
	private Date endDate;
	private BigDecimal interest;
}