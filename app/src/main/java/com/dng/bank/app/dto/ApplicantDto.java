package com.dng.bank.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link com.dng.bank.app.entity.Applicant} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantDto implements Serializable {
	private Long createAt;
	private Long updateAt;
	private String name;
	private String idNumber;
}