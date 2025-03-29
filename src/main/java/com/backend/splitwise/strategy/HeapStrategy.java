package com.backend.splitwise.strategy;

import com.backend.splitwise.dtos.settleup.Transaction;
import com.backend.splitwise.models.Expense;

import java.util.List;

public class HeapStrategy implements SettleUpStrategy{
    @Override
    public List<Transaction> transactions(List<Expense> expenses) {
        return List.of();
    }
}
