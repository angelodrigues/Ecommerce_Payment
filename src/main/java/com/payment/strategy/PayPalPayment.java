package com.payment.strategy;

import com.payment.exception.PaymentException;
import lombok.Getter;

@Getter
public class PayPalPayment implements PaymentStrategy {
    private final String email;
    private final String password;

    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void pay(double amount) throws PaymentException {
        if (email == null || !email.contains("@")) {
            throw new PaymentException("Email do PayPal inválido");
        }
        // Aqui você pode adicionar a lógica real de processamento do pagamento
        System.out.println("Pagamento de R$" + amount + " realizado via PayPal usando email: " + email);
    }

    @Override
    public String getPaymentDetails() {
        return "PayPal - Email: " + email;
    }
} 