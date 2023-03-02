package com.dng.bank.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link com.dng.bank.app.entity.Payment.PaymentId} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PaymentIdDto implements Serializable {
	private String loadId;
	private Date paymentDate;
}