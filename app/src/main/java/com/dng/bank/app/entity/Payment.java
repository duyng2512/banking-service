package com.dng.bank.app.entity;

import com.dng.bank.app.entity.core.BaseLongPrimaryKeyEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class Payment extends BaseLongPrimaryKeyEntity {
	
	@ManyToOne
	@JoinColumn(name = "loan_id")
	private Loan loan;
	
	@Column(name = "payment_date")
	@Temporal(TemporalType.DATE)
	private Date paymentDate;
	
	@Column(name = "due_date")
	@Temporal(TemporalType.DATE)
	private Date dueDate;
	
	@Column(name = "amount")
	private BigDecimal amount;
}
