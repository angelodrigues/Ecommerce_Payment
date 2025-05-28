package com.payment.controller;

import com.payment.exception.PaymentException;
import com.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/credit-card")
    public ResponseEntity<String> processCreditCardPayment(
            @RequestParam String cardNumber,
            @RequestParam String name,
            @RequestParam String cvv,
            @RequestParam String expiryDate,
            @RequestParam double amount) {
        try {
            paymentService.processCreditCardPayment(cardNumber, name, cvv, expiryDate, amount);
            return ResponseEntity.ok("Pagamento com cartão de crédito processado com sucesso");
        } catch (PaymentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/pix")
    public ResponseEntity<String> processPixPayment(
            @RequestParam String pixKey,
            @RequestParam String pixType,
            @RequestParam double amount) {
        try {
            paymentService.processPixPayment(pixKey, pixType, amount);
            return ResponseEntity.ok("Pagamento PIX processado com sucesso");
        } catch (PaymentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/paypal")
    public ResponseEntity<String> processPayPalPayment(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam double amount) {
        try {
            paymentService.processPayPalPayment(email, password, amount);
            return ResponseEntity.ok("Pagamento PayPal processado com sucesso");
        } catch (PaymentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
} 