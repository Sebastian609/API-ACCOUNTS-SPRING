package com.bank.ACCOUNTS_API.Service;


import com.bank.ACCOUNTS_API.DTO.TransferDTO;
import com.bank.ACCOUNTS_API.Entities.AccountEntity;
import com.bank.ACCOUNTS_API.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public CompletableFuture<AccountEntity> getAccountByUUIDAsync(UUID uuid) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return accountRepository.getAccountByUUID(uuid);
            } catch (Exception e) {
                throw new RuntimeException("Error al obtener la cuenta por UUID", e);
            }
        });
    }

    public CompletableFuture<List<AccountEntity>> getAccountsByUserIdAsync(int userId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return accountRepository.getAccountsByUserId(userId);
            } catch (Exception e) {
                throw new RuntimeException("Error al obtener las cuentas por UserID", e);
            }
        });
    }

    public CompletableFuture<AccountEntity> transfer(TransferDTO transfer) {
        return CompletableFuture.supplyAsync(() -> {
            try {
               // comprbamos que existan cuenta oriegn y destino
               AccountEntity origin = accountRepository.getAccountByUUID(transfer.getOrigin());
                AccountEntity destiny = accountRepository.getAccountByUUID(transfer.getDestiny());
               if (origin==null){
                   throw new Error("No existe la cuenta origen");
               }
                if (destiny==null){
                    throw new Error("No existe la cuenta destino");
                }
                //validamos que saldo actual>saldo a deposuitar

                if(origin.getBalance()<transfer.getBalance())
                {
                    throw new Error("Saldo insuficiente");
                }

                //restamos saldo al orgien

                origin.setBalance(origin.getBalance()- transfer.getBalance());
                accountRepository.updateAccount(origin);
                //añadimos saldo al destino

                destiny.setBalance(destiny.getBalance()+ transfer.getBalance());
                accountRepository.updateAccount(destiny);

                return origin;

            } catch (Exception e) {
                throw new RuntimeException("Error al obtener las cuentas por UserID", e);
            }
        });
    }
}
