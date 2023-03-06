package com.dng.bank.app.entity;

import com.dng.bank.app.constant.CreditType;
import com.dng.bank.app.entity.core.BaseLongPrimaryKeyEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "credit")
public class Credit extends BaseLongPrimaryKeyEntity {
	
	@ManyToOne
	@JoinColumn(name = "applicant_id")
	private Applicant applicant;
	
	@Column(name = "total_limit")
	private BigDecimal totalLimit;
	
	@Column
	private String currency;
	
	@Column(name = "reg_number")
	@NaturalId
	private String creditRegNumber;
	
	@Column(name = "start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column(name = "end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	
	@Column(name = "credit_type")
	@Enumerated(EnumType.STRING)
	private CreditType creditType;
}
