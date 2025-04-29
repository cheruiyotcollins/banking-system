package com.dtb.payment_service;

@ExtendWith(MockitoExtension.class)
class PaymentServiceUnitTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountServiceClient accountServiceClient;

    @Mock
    private EventPublisher eventPublisher;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    void topUp_shouldCompleteSuccessfully() {
        // Arrange
        String accountId = "acc-123";
        BigDecimal amount = new BigDecimal("100.00");
        String reference = "test-ref";

        Account account = new Account(accountId, "user-123", new BigDecimal("500.00"), true);
        when(accountServiceClient.getAccount(accountId)).thenReturn(Optional.of(account));
        when(accountServiceClient.updateBalance(accountId, amount)).thenReturn(true);

        // Act
        Transaction result = paymentService.topUp(accountId, amount, reference);

        // Assert
        assertNotNull(result);
        assertEquals(TransactionStatus.COMPLETED, result.getStatus());
        verify(eventPublisher).publishTransactionEvent(any(Transaction.class));
    }

    @Test
    void topUp_shouldFailForInactiveAccount() {
        // Arrange
        String accountId = "acc-123";
        BigDecimal amount = new BigDecimal("100.00");
        String reference = "test-ref";

        Account account = new Account(accountId, "user-123", new BigDecimal("500.00"), false);
        when(accountServiceClient.getAccount(accountId)).thenReturn(Optional.of(account)));

        // Act & Assert
        assertThrows(AccountInactiveException.class, () -> {
            paymentService.topUp(accountId, amount, reference);
        });
    }

    // More test cases...
}