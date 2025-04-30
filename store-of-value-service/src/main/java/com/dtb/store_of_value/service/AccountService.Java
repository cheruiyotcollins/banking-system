package com.dtb.store_of_value.service;

import com.dtb.store_of_value.repository.AccountRepository;
import com.dtb.store_of_value.dto*;
import com.dtb.store_of_value.model.Account;

@Service
class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountResponseDto createAccount(AccountRequestDto dto) {
        Account account = new Account();
        account.setAccountNumber(dto.accountNumber);
        account.setProfileId(dto.profileId);
        account.setBalance(BigDecimal.ZERO);
        account.setActive(true);
        Account saved = accountRepository.save(account);
        return toDto(saved);
    }

    public AccountResponseDto getBalance(Long id) {
        Account account = accountRepository.findById(id).orElseThrow();
        return toDto(account);
    }

    public void activateAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow();
        account.setActive(true);
        accountRepository.save(account);
    }

    public void deactivateAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow();
        account.setActive(false);
        accountRepository.save(account);
    }

    private AccountResponseDto toDto(Account account) {
        AccountResponseDto dto = new AccountResponseDto();
        dto.id = account.getId();
        dto.accountNumber = account.getAccountNumber();
        dto.profileId = account.getProfileId();
        dto.balance = account.getBalance();
        dto.active = account.isActive();
        return dto;
    }
}