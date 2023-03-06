package com.dng.bank.app.entity.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseEntity<T extends Serializable> implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected T id;
	
	@Column(nullable = false, columnDefinition = "default EXTRACT(EPOCH from CURRENT_TIMESTAMP())")
	protected Long createAt;
	
	@Column(nullable = false, columnDefinition = "default EXTRACT(EPOCH from CURRENT_TIMESTAMP())")
	protected Long updateAt;
	
	@PrePersist
	public void prePersist() {
		if (createAt == null) {
			createAt = System.currentTimeMillis();
		}
		updateAt = 0L;
	}
	
	@PreUpdate
	public void preUpdate() {
		updateAt = System.currentTimeMillis();
	}
}
