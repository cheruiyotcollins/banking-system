package com.dtb.event.service;
@Service
public class TransactionEventListener {

    private final NotificationService notificationService;

    @Autowired
    public TransactionEventListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = "transactionQueue")
    public void handleTransactionEvent(TransactionEvent transactionEvent) {
        // Process the event (e.g., send email/SMS)
        notificationService.sendNotification(transactionEvent);
    }
}
