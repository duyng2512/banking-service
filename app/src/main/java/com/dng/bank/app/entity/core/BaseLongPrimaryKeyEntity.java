package com.dng.bank.app.entity.core;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class BaseLongPrimaryKeyEntity extends BaseEntity<Long> {
}
