package com.dng.bank.app.entity;

import com.dng.bank.app.entity.core.BaseLongPrimaryKeyEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.NaturalId;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "applicant")
@Builder
@AllArgsConstructor
public class Applicant extends BaseLongPrimaryKeyEntity {
	
	@Column(name = "name")
	private String name;
	
	@NaturalId
	@Column(name = "identify_number")
	private String idNumber;
	
}
