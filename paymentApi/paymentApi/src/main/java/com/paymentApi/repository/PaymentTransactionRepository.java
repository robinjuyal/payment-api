package com.paymentApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymentApi.entity.PaymentTransaction;


@Repository
public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long> {
    
}
