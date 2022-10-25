package com.barry.software.payement.repositories;

import com.barry.software.payement.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
