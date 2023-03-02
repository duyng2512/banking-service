package com.dng.bank.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "credit_facility")
public class CreditFacility extends BaseLongPrimaryKeyEntity{



}
