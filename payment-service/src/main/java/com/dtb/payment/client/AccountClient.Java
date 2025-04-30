
package com.dtb.payment.client;

@FeignClient(name = "store-of-value-service")
public interface AccountClient {
    @PostMapping("/api/accounts/credit")
    void credit(@RequestParam Long accountId, @RequestParam BigDecimal amount);

    @PostMapping("/api/accounts/debit")
    void debit(@RequestParam Long accountId, @RequestParam BigDecimal amount);
}

