package com.aphatheology.transactionrestapi.services;

import com.aphatheology.transactionrestapi.dtos.ApiResponse;
import com.aphatheology.transactionrestapi.dtos.TransactionRequestDto;
import com.aphatheology.transactionrestapi.entities.AccountEntity;
import com.aphatheology.transactionrestapi.entities.TransactionEntity;
import com.aphatheology.transactionrestapi.exceptions.ResourceNotFoundException;
import com.aphatheology.transactionrestapi.respositories.AccountRepository;
import com.aphatheology.transactionrestapi.respositories.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public ApiResponse makeTransfer(TransactionRequestDto payload) {
        ApiResponse response = new ApiResponse();
        response.setTransactionId(payload.getTransactionId());

        AccountEntity debitAccount = this.accountRepository.findByAccountNumber(payload.getDebitAccount()).orElseThrow(() -> new ResourceNotFoundException("Debit account does not exist", payload.getTransactionId(), "error"));

        AccountEntity creditAccount = this.accountRepository.findByAccountNumber(payload.getCreditAccount()).orElseThrow(() -> new ResourceNotFoundException("Credit account does not exist", payload.getTransactionId(), "error"));

        if(debitAccount.getBalance().compareTo(payload.getAmount()) < 0) {
            response.setMessage("Insufficient funds in debit account");
            response.setStatus("Error");
            return response;
        }

        debitAccount.setBalance(debitAccount.getBalance().subtract(payload.getAmount()));
        debitAccount.setTotalDebit(debitAccount.getTotalDebit().add(payload.getAmount()));
        this.accountRepository.save(debitAccount);

        creditAccount.setBalance(creditAccount.getBalance().add(payload.getAmount()));
        creditAccount.setTotalCredit(creditAccount.getTotalCredit().add(payload.getAmount()));
        this.accountRepository.save(creditAccount);

        TransactionEntity newTransaction = new TransactionEntity();
        newTransaction.setAmount(payload.getAmount());
        newTransaction.setTransactionId(payload.getTransactionId());
        newTransaction.setDebitAccount(debitAccount);
        newTransaction.setCreditAccount(creditAccount);
        newTransaction.setStatus("Success");
        newTransaction.setRemark(payload.getDescription());
        newTransaction.setTransactionDate(LocalDateTime.now());

        this.transactionRepository.save(newTransaction);

        response.setMessage("Transaction Processed Successfully");
        response.setStatus("Success");

        return response;
    }
}
