package com.payment.strategy;

import com.payment.exception.PaymentException;
import lombok.Getter;

@Getter
public class PixPayment implements PaymentStrategy {
    private final String pixKey;
    private final String pixType; // CPF, email, telefone, chave aleatória

    public PixPayment(String pixKey, String pixType) {
        this.pixKey = pixKey;
        this.pixType = pixType;
    }

    @Override
    public void pay(double amount) throws PaymentException {
        if (pixKey == null || pixKey.isEmpty()) {
            throw new PaymentException("Chave PIX inválida");
        }
        // Aqui você pode adicionar a lógica real de processamento do pagamento
        System.out.println("Pagamento de R$" + amount + " realizado via PIX usando " + pixType + ": " + pixKey);
    }

    @Override
    public String getPaymentDetails() {
        return "PIX - Tipo: " + pixType + ", Chave: " + pixKey;
    }
} 