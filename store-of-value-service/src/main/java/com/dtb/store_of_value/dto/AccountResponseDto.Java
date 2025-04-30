package com.dtb.store_of_value.dto;
@Getter
@Setter
class AccountResponseDto {
    public Long id;
    public String accountNumber;
    public Long profileId;
    public BigDecimal balance;
    public boolean active;
}