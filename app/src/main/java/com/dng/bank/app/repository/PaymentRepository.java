package com.dng.bank.app.repository;

public interface PaymentRepository extends org.springframework.data.jpa.repository.JpaRepository<com.dng.bank.app.entity.Payment, com.dng.bank.app.entity.Payment.PaymentId> ,org.springframework.data.jpa.repository.JpaSpecificationExecutor<com.dng.bank.app.entity.Payment> {
}