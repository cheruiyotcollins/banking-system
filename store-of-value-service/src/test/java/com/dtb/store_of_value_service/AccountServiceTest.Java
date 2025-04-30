package com.dtb.store_of_value_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ProfileClient profileClient;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAccount_success() {
        AccountRequestDto request = new AccountRequestDto();
        request.accountNumber = "ACC123";
        request.profileId = 1L;

        ProfileDto profileDto = new ProfileDto();
        profileDto.id = 1L;
        profileDto.email = "test@example.com";
        profileDto.fullName = "Test User";

        Account savedAccount = new Account();
        savedAccount.setAccountNumber("ACC123");
        savedAccount.setProfileId(1L);
        savedAccount.setBalance(BigDecimal.ZERO);
        savedAccount.setActive(true);

        when(profileClient.getProfileById(1L)).thenReturn(profileDto);
        when(accountRepository.save(any(Account.class))).thenReturn(savedAccount);

        AccountResponseDto response = accountService.createAccount(request);

        assertEquals("ACC123", response.accountNumber);
        assertEquals(1L, response.profileId);
        assertEquals(BigDecimal.ZERO, response.balance);
        assertTrue(response.active);
    }

    @Test
    void testGetBalance_success() {
        Account account = new Account();
        account.setAccountNumber("ACC001");
        account.setProfileId(2L);
        account.setBalance(BigDecimal.valueOf(500));
        account.setActive(true);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        AccountResponseDto dto = accountService.getBalance(1L);

        assertEquals("ACC001", dto.accountNumber);
        assertEquals(BigDecimal.valueOf(500), dto.balance);
    }

    @Test
    void testActivateAccount_setsActiveToTrue() {
        Account account = new Account();
        account.setActive(false);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        accountService.activateAccount(1L);

        assertTrue(account.isActive());
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void testDeactivateAccount_setsActiveToFalse() {
        Account account = new Account();
        account.setActive(true);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        accountService.deactivateAccount(1L);

        assertFalse(account.isActive());
        verify(accountRepository, times(1)).save(account);
    }
}
