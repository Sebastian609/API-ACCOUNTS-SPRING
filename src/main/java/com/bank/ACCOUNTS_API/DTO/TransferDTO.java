package com.bank.ACCOUNTS_API.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@NoArgsConstructor
@Getter
@Setter
 
public class TransferDTO {
    private UUID origin;
    private UUID destiny;
    private double balance;
}
