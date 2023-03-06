package com.dng.bank.app.entity;

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
@Table(name = "payment")
@Builder
@AllArgsConstructor
public class Payment extends BaseLongPrimaryKeyEntity {
	
	@ManyToOne
	@JoinColumn(name = "loan_id")
	private Loan loan;
	
	@ManyToOne
	@JoinColumn(name = "applicant_id")
	private Applicant applicant;
	
	@Column(name = "start_data")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column(name = "due_date")
	@Temporal(TemporalType.DATE)
	private Date dueDate;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	private String currency;
	
	private boolean overdue;
	
	@Column(name = "payment_period")
	@Enumerated(EnumType.STRING)
	private PaymentPeriod paymentPeriod;
	
	@Override
	public String toString() {
		return "Payment{" +
			       "loan=" + loan.getId() +
			       ", applicant=" + applicant.getId() +
			       ", startDate=" + startDate +
			       ", dueDate=" + dueDate +
			       ", amount=" + amount +
			       ", currency='" + currency + '\'' +
			       ", overdue=" + overdue +
			       ", paymentPeriod=" + paymentPeriod +
			       ", id=" + id +
			       ", createAt=" + createAt +
			       ", updateAt=" + updateAt +
			       '}';
	}
}
