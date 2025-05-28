package com.payment.strategy;

import com.payment.exception.PaymentException;
import lombok.Getter;

@Getter
public class CreditCardPayment implements PaymentStrategy {
    private final String cardNumber;
    private final String name;
    private final String cvv;
    private final String expiryDate;

    public CreditCardPayment(String cardNumber, String name, String cvv, String expiryDate) {
        this.cardNumber = cardNumber;
        this.name = name;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    @Override
    public void pay(double amount) throws PaymentException {
        if (cardNumber == null || cardNumber.length() != 16) {
            throw new PaymentException("Número do cartão inválido");
        }
        // Aqui você pode adicionar a lógica real de processamento do pagamento
        System.out.println("Pagamento de R$" + amount + " realizado com cartão de crédito (" +
                maskCardNumber(cardNumber) + ") em nome de " + name);
    }

    @Override
    public String getPaymentDetails() {
        return "Cartão de Crédito: " + maskCardNumber(cardNumber) + ", Nome: " + name;
    }

    private String maskCardNumber(String cardNumber) {
        return "**** **** **** " + cardNumber.substring(12);
    }
} 