package com.backend.splitwise.strategy;

import com.backend.splitwise.dtos.settleup.Transaction;
import com.backend.splitwise.models.Expense;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class HeapStrategy implements SettleUpStrategy{
    @Override
    public List<Transaction> settleUpByStrategy(List<Expense> expenses) {
//         PriorityQueue<Double> minHeap = new PriorityQueue<>();
//         PriorityQueue<Double> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
//         for(Expense expense:expenses){
//
//         }
         return List.of();
    }
}
