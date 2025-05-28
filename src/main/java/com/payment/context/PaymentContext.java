package com.payment.context;

import com.payment.exception.PaymentException;
import com.payment.strategy.PaymentStrategy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public PaymentContext(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executePayment(double amount) throws PaymentException {
        if (paymentStrategy == null) {
            throw new PaymentException("Estratégia de pagamento não definida");
        }
        paymentStrategy.pay(amount);
    }

    public String getPaymentDetails() {
        if (paymentStrategy == null) {
            return "Nenhuma estratégia de pagamento definida";
        }
        return paymentStrategy.getPaymentDetails();
    }
} 