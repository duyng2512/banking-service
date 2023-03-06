package com.dng.bank.app.dto;

import com.dng.bank.app.constant.CreditType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.time.DateUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * A DTO for the {@link com.dng.bank.app.entity.Credit} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CreditDto implements Serializable {
	private BigDecimal totalLimit;
	private String currency;
	private String creditRegNumber;
	private Date startDate = new Date();
	private Date endDate = DateUtils.addDays(new Date(), 365);
	private CreditType creditType;
	private boolean valid;
}