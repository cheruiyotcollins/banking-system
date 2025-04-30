package com.dtb.payment.model;
@Data
@Entity
public class Transaction {
    @Id @GeneratedValue
    private Long id;

    private String type; // TOPUP, WITHDRAWAL, TRANSFER
    private BigDecimal amount;

    private Long sourceAccountId;
    private Long destinationAccountId;

    private LocalDateTime timestamp;
    private String status; // SUCCESS, FAILED

}
