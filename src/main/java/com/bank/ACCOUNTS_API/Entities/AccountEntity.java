package com.bank.ACCOUNTS_API.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AccountEntity {
    private UUID uuid;
    private int userId;
    private int bankId;
    private double balance;
}
