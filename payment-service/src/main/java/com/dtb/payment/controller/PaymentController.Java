package com.dtb.payment.controller;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/topup")
    public ResponseEntity<?> topup(@RequestBody TopupRequest request) {
        return ResponseEntity.ok(paymentService.topup(request));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody WithdrawRequest request) {
        return ResponseEntity.ok(paymentService.withdraw(request));
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferRequest request) {
        return ResponseEntity.ok(paymentService.transfer(request));
    }
}
