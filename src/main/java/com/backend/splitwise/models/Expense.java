package com.backend.splitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Entity(name = "expenses")//name of the table should be in plural form so that it dont conflict
public class Expense extends BaseModel{
    private double amount;

    @OneToMany(mappedBy = "expense")
    private List<ExpenseUser> paidBy;

    //    if we want bidirectional mapping expenseUserList
    @OneToMany(mappedBy = "expense") //- > one expense can have multiple expense user
    private List<ExpenseUser> owedBy;

    private String description;

    //    Tells that this is enum and store the string value in db
    @Enumerated(value = EnumType.STRING)
    private ExpenseType expenseType;

    @ManyToOne
    @JoinColumn(name = "user_groups")
    private Group group;

    @ManyToOne
    private User createBy;
}
