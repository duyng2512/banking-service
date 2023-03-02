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
@Table(name = "loan")
public class Loan extends BaseLongPrimaryKeyEntity {
	
	@ManyToOne
	@JoinColumn(name = "credit_id")
	private Credit credit;
	
	@ManyToOne
	@JoinColumn(name = "applicant_id")
	private Applicant applicant;
	
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
	
	@Column(name = "paid", columnDefinition = "boolean default false")
	private Boolean paid;
	
}
