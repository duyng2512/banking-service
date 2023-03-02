package com.dng.bank.app.repository;

public interface LoanRepository<, ID> extends org.springframework.data.jpa.repository.JpaRepository<com.dng.bank.app.entity.Loan, ID> {
}