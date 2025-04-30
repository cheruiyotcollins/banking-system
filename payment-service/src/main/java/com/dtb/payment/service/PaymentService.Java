package com.dtb.payment_service.service;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {
    private final AccountClient accountClient;
    private final TransactionRepository transactionRepo;
    private final EventPublisher eventPublisher;

    public Transaction topup(TopupRequest request) {
        // Call Store of Value to credit account
        accountClient.credit(request.getAccountId(), request.getAmount());
        Transaction txn = transactionRepo.save(buildTransaction("TOPUP", request.getAmount(), null, request.getAccountId()));
        eventPublisher.publish(txn);
        return txn;
    }

    public Transaction withdraw(WithdrawRequest request) {
        accountClient.debit(request.getAccountId(), request.getAmount());
        Transaction txn = transactionRepo.save(buildTransaction("WITHDRAWAL", request.getAmount(), request.getAccountId(), null));
        eventPublisher.publish(txn);
        return txn;
    }

    public Transaction transfer(TransferRequest request) {
        accountClient.debit(request.getFromAccountId(), request.getAmount());
        accountClient.credit(request.getToAccountId(), request.getAmount());
        Transaction txn = transactionRepo.save(buildTransaction("TRANSFER", request.getAmount(), request.getFromAccountId(), request.getToAccountId()));
        eventPublisher.publish(txn);
        return txn;
    }

    private Transaction buildTransaction(...) { ... }
}
