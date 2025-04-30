package com.dtb.event.service;

import com.bank.events.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {
    public void sendEmail(Event event) {
        // Mock implementation
        String message = String.format(
                "Dear Customer,\n\nYour transaction of %,.2f for %s has been processed.\nTransaction ID: %s\n\nThank you",
                event.getAmount(), event.getEventType(), event.getTransactionId()
        );
        log.info("Mock Email Sent to customer {}: {}", event.getCustomerId(), message);
    }
}

@Slf4j
@Service
public class SmsService {
    public void sendSms(Event event) {
        // Mock implementation
        String message = String.format(
                "Transaction alert: %s of %,.2f. Ref: %s",
                event.getEventType(), event.getAmount(), event.getTransactionId()
        );
        log.info("Mock SMS Sent to customer {}: {}", event.getCustomerId(), message);
    }
}