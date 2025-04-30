package com.dtb.event.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "events")
public class Event {
    private String transactionId;
    private String accountId;
    private double amount;
    private String type; // topup, withdrawal, transfer
    private String customerEmail;
    private String customerPhoneNumber;
}