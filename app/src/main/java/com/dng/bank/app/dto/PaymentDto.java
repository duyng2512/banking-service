package com.dng.bank.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * A DTO for the {@link com.dng.bank.app.entity.Payment} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PaymentDto implements Serializable {
	private PaymentIdDto paymentId;
	private Date dueDate;
	private BigDecimal amount;
}