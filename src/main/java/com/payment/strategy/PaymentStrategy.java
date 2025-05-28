package com.payment.strategy;

import com.payment.exception.PaymentException;

public interface PaymentStrategy {
    void pay(double amount) throws PaymentException;
    String getPaymentDetails();
} 