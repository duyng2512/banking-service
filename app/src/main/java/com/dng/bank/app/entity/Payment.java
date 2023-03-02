package com.dng.bank.app.entity;

import com.dng.bank.app.entity.core.BaseLongPrimaryKeyEntity;
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
public class Loan extends BaseLongPrimaryKeyEntity {



}
