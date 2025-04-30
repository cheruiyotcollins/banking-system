package com.dtb.store_of_value.repository;
package com.dtb.store_of_value.model.Account;
@Repository
interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
}