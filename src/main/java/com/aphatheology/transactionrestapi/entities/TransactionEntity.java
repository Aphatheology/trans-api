package com.aphatheology.transactionrestapi.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "debit_account_number", referencedColumnName = "account_number", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private AccountEntity debitAccount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "credit_account_number", referencedColumnName = "account_number", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private AccountEntity creditAccount;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String transactionId;

    @Column
    private String remark;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime transactionDate;
}
