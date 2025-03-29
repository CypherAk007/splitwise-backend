package com.backend.splitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "expense_users")
public class ExpenseUser extends BaseModel{
    @ManyToOne
    @JoinColumn(name="expense")
    private Expense expense;

    private double amount;
    @ManyToOne
    private User user;

    @Enumerated(value = EnumType.STRING)
    private ExpenseUserType type;
}
