package com.dtb.event_service;
@ExtendWith(MockitoExtension.class)
public class TransactionEventListenerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private TransactionEventListener eventListener;

    @Test
    void shouldSendNotificationWhenTransactionEventIsReceived() {
        TransactionEvent event = new TransactionEvent();
        event.setTransactionId("123");
        event.setCustomerEmail("customer@example.com");
        event.setAmount(100.0);

        eventListener.handleTransactionEvent(event);

        // Verify that notification service methods were called
        verify(notificationService, times(1)).sendNotification(event);
    }
}
