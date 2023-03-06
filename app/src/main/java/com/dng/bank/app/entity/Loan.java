package com.dng.bank.app.entity;

import com.dng.bank.app.constant.CreditType;
import com.dng.bank.app.constant.PaymentPeriod;
import com.dng.bank.app.entity.core.BaseLongPrimaryKeyEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "loan")
@Builder
@AllArgsConstructor
public class Loan extends BaseLongPrimaryKeyEntity {
	
	@ManyToOne
	@JoinColumn(name = "applicant_id")
	private Applicant applicant;
	
	@ManyToOne
	@JoinColumn(name = "credit_id")
	private Credit credit;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	@Column
	private String currency;
	
	@Column(name = "start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column(name = "end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@Column(name = "interest")
	private BigDecimal interest;
	
	@Column(name = "overdue", columnDefinition = "boolean default false")
	private boolean overdue;
	
	@Column(name = "credit_type")
	@Enumerated(EnumType.STRING)
	private CreditType creditType;
	
	@Column(name = "payment_period")
	@Enumerated(EnumType.STRING)
	private PaymentPeriod paymentPeriod;
	
}
