package com.dtb.event.service;

import com.bank.events.model.Event;
import com.bank.events.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public void sendNotification(TransactionEvent event) {
        sendEmail(event);
        sendSms(event);
    }

    private void sendEmail(TransactionEvent event) {
        // Mock sending an email
        logger.info("Sending email to {}: Transaction {} of {} USD", event.getCustomerEmail(), event.getTransactionId(), event.getAmount());
    }

    private void sendSms(TransactionEvent event) {
        // Mock sending an SMS
        logger.info("Sending SMS to {}: Transaction {} of {} USD", event.getCustomerPhoneNumber(), event.getTransactionId(), event.getAmount());
    }
}