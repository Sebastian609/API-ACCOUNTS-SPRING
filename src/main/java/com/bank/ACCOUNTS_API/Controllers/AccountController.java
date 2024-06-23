package com.bank.ACCOUNTS_API.Controllers;

import com.bank.ACCOUNTS_API.DTO.TransferDTO;
import com.bank.ACCOUNTS_API.Entities.AccountEntity;
import com.bank.ACCOUNTS_API.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{uuid}")
    public CompletableFuture<AccountEntity> getAccountByUUID(@PathVariable UUID uuid) {
        return accountService.getAccountByUUIDAsync(uuid);
    }

    @GetMapping("/hola")
    public ResponseEntity<String> getAccountByUUID() {
        return ResponseEntity.ok("hola");
    }


    @GetMapping("/user/{userId}")
    public CompletableFuture<List<AccountEntity>> getAccountsByUserId(@PathVariable int userId) {
        return accountService.getAccountsByUserIdAsync(userId);
    }

    @PostMapping("/transfer")
    public CompletableFuture<AccountEntity> transfer(@RequestBody TransferDTO transferDto) {
        if(transferDto.getOrigin().toString().trim()=="" || transferDto.getDestiny().toString().trim()==""){
            throw new Error();
        }

        if(transferDto.getBalance()<=0){
            throw new Error();
        }
        return accountService.transfer(transferDto);
    }
}
