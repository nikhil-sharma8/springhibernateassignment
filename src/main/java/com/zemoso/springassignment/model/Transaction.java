package com.zemoso.springassignment.model;

import com.zemoso.springassignment.model.utils.enums.TType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TType type;

    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate;

    @PrePersist
    public void setTransactionDate() {
        this.transactionDate = LocalDate.now();
    }

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}