package com.bank.ACCOUNTS_API.Repository;

import com.bank.ACCOUNTS_API.Entities.AccountEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class AccountRepository {


    private final ObjectMapper mapper = new ObjectMapper();
    private final String filePath = "C:/Users/sebav/OneDrive/Escritorio/ACCOUNTS-API/src/main/resources/accounts.json";


    public AccountEntity getAccountByUUID(UUID uuid) throws IOException {
        List<AccountEntity> accounts = getAllAccounts();
        return accounts.stream()
                .filter(account -> account.getUuid().equals(uuid))
                .findFirst()
                .orElse(null);
    }

    public List<AccountEntity> getAccountsByUserId(int userId) throws IOException {
        List<AccountEntity> accounts = getAllAccounts();
        return accounts.stream()
                .filter(account -> account.getUserId() == userId)
                .collect(Collectors.toList());
    }

    private List<AccountEntity> getAllAccounts() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("no existe ");
            return new ArrayList<>();
        }
        return mapper.readValue(file, new TypeReference<List<AccountEntity>>() {});
    }

    public void updateAccount(AccountEntity updatedAccount) throws IOException {
        List<AccountEntity> accounts = getAllAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUuid().equals(updatedAccount.getUuid())) {
                accounts.set(i, updatedAccount);
                saveAllAccounts(accounts);
                return;
            }
        }
        throw new IllegalArgumentException("Account with UUID " + updatedAccount.getUuid() + " not found");
    }

    private void saveAllAccounts(List<AccountEntity> accounts) throws IOException {
        mapper.writeValue(new File(filePath), accounts);
    }
}
