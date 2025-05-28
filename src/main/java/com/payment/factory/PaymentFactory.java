package com.payment.factory;

import com.payment.exception.PaymentException;
import com.payment.strategy.CreditCardPayment;
import com.payment.strategy.PayPalPayment;
import com.payment.strategy.PaymentStrategy;
import com.payment.strategy.PixPayment;

public class PaymentFactory {
    
    public static PaymentStrategy createPayment(String type, String... params) throws PaymentException {
        return switch (type.toLowerCase()) {
            case "creditcard" -> new CreditCardPayment(
                params[0], // cardNumber
                params[1], // name
                params[2], // cvv
                params[3]  // expiryDate
            );
            case "pix" -> new PixPayment(
                params[0], // pixKey
                params[1]  // pixType
            );
            case "paypal" -> new PayPalPayment(
                params[0], // email
                params[1]  // password
            );
            default -> throw new PaymentException("Tipo de pagamento não suportado: " + type);
        };
    }
} 