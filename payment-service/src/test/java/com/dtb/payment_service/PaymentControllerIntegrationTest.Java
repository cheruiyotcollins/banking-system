package com.dtb.payment_service;
@SpringBootTest
@AutoConfigureMockMvc
class PaymentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Test
    void topUp_shouldReturnCreatedTransaction() throws Exception {
        // Arrange
        String accountId = "acc-123";
        BigDecimal amount = new BigDecimal("100.00");
        String reference = "test-ref";

        Transaction mockTransaction = new Transaction();
        mockTransaction.setId("txn-123");
        mockTransaction.setAccountId(accountId);
        mockTransaction.setAmount(amount);
        mockTransaction.setStatus(TransactionStatus.COMPLETED);

        when(paymentService.topUp(accountId, amount, reference)).thenReturn(mockTransaction);

        TopUpRequest request = new TopUpRequest(accountId, amount, reference);

        // Act & Assert
        mockMvc.perform(post("/api/payments/topup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request))
                        .header("Authorization", "Bearer valid-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("txn-123"))
                .andExpect(jsonPath("$.status").value("COMPLETED"));
    }

    private String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}