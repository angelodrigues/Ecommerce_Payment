package com.payment.service;

import com.payment.context.PaymentContext;
import com.payment.exception.PaymentException;
import com.payment.factory.PaymentFactory;
import com.payment.messaging.PaymentNotificationProducer;
import com.payment.strategy.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    
    private final PaymentNotificationProducer notificationProducer;
    
    public void processCreditCardPayment(String cardNumber, String name, String cvv, String expiryDate, double amount) throws PaymentException {
        PaymentStrategy strategy = PaymentFactory.createPayment("creditcard", cardNumber, name, cvv, expiryDate);
        PaymentContext context = new PaymentContext(strategy);
        context.executePayment(amount);
        notificationProducer.sendPaymentNotification("Pagamento com cartão de crédito processado: " + amount);
    }

    public void processPixPayment(String pixKey, String pixType, double amount) throws PaymentException {
        PaymentStrategy strategy = PaymentFactory.createPayment("pix", pixKey, pixType);
        PaymentContext context = new PaymentContext(strategy);
        context.executePayment(amount);
        notificationProducer.sendPaymentNotification("Pagamento PIX processado: " + amount);
    }

    public void processPayPalPayment(String email, String password, double amount) throws PaymentException {
        PaymentStrategy strategy = PaymentFactory.createPayment("paypal", email, password);
        PaymentContext context = new PaymentContext(strategy);
        context.executePayment(amount);
        notificationProducer.sendPaymentNotification("Pagamento PayPal processado: " + amount);
    }
} 