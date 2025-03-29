package com.backend.splitwise.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity(name = "expense_users")
@AllArgsConstructor
@NoArgsConstructor

public class ExpenseUser extends BaseModel{
    @ManyToOne(fetch = FetchType.LAZY) // Avoid unnecessary fetching
    @JoinColumn(name="expense")

    private Expense expense;

    private double amount;
    @ManyToOne(fetch = FetchType.LAZY) // Avoid unnecessary fetching
    private User user;

    @Enumerated(value = EnumType.STRING)
    private ExpenseUserType type;
}
