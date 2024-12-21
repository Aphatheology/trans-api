package com.aphatheology.transactionrestapi.controllers;

import com.aphatheology.transactionrestapi.dtos.ApiResponse;
import com.aphatheology.transactionrestapi.dtos.TransactionRequestDto;
import com.aphatheology.transactionrestapi.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping()
    public ResponseEntity<ApiResponse> makeTransfer(@RequestBody TransactionRequestDto payload) {
        return new ResponseEntity<>(this.accountService.makeTransfer(payload), HttpStatus.CREATED);
    }
}
