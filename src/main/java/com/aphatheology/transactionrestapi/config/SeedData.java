package com.aphatheology.transactionrestapi.config;

import com.aphatheology.transactionrestapi.entities.AccountEntity;
import com.aphatheology.transactionrestapi.respositories.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SeedData implements CommandLineRunner {
    private final AccountRepository accountRepository;

    public SeedData(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<AccountEntity> accounts = this.accountRepository.findAll();

        if (accounts.isEmpty()) {
            AccountEntity account1 = AccountEntity.builder()
                    .accountNumber("GL001")
                    .balance(BigDecimal.valueOf(2000.00))
                    .totalCredit(BigDecimal.valueOf(0.00))
                    .totalDebit(BigDecimal.valueOf(0.00))
                    .build();

            AccountEntity account2 = AccountEntity.builder()
                    .accountNumber("GL002")
                    .balance(BigDecimal.valueOf(5000.00))
                    .totalCredit(BigDecimal.valueOf(0.00))
                    .totalDebit(BigDecimal.valueOf(0.00))
                    .build();

            AccountEntity account3 = AccountEntity.builder()
                    .accountNumber("GL003")
                    .balance(BigDecimal.valueOf(500.00))
                    .totalCredit(BigDecimal.valueOf(0.00))
                    .totalDebit(BigDecimal.valueOf(0.00))
                    .build();

            accounts.add(account1);
            accounts.add(account2);
            accounts.add(account3);

            this.accountRepository.saveAll(accounts);
        }
    }
}
