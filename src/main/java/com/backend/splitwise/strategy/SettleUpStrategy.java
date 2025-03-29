package com.backend.splitwise.strategy;

import com.backend.splitwise.dtos.settleup.Transaction;
import com.backend.splitwise.models.Expense;

import java.util.List;

public interface SettleUpStrategy {
    List<Transaction> transactions(List<Expense> expenses);
}
