package com.backend.splitwise.strategy;

import com.backend.splitwise.dtos.settleup.Transaction;
import com.backend.splitwise.models.Expense;
import com.backend.splitwise.models.ExpenseUser;
import com.backend.splitwise.models.User;
import com.backend.splitwise.repositories.UserRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Primary
public class HeapStrategy implements SettleUpStrategy{
    private final UserRepository userRepository;

    public HeapStrategy(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Transaction> settleUpByStrategy(List<Expense> expenses) {
        List<Transaction> transactionsList = new ArrayList<>();
//        1:makeheapminmax
         PriorityQueue<UserAmtDS> minHeap = new PriorityQueue<>(Comparator.comparingDouble(UserAmtDS::getAmount));
         PriorityQueue<UserAmtDS> maxHeap = new PriorityQueue<>(Comparator.comparingDouble(UserAmtDS::getAmount).reversed());
//         2: collate all user sum of expenditure
         List<UserAmtDS> finalPayerPayeeList = getFinalValues(expenses);
//         3: has to pay goes to minheap and has to recieve go to maxheap
         for(UserAmtDS user: finalPayerPayeeList){
             if(user.getAmount()<0){
                 minHeap.add(user);
             }else{
                 maxHeap.add(user);
             }
         }

         while(!(minHeap.isEmpty() && maxHeap.isEmpty())){
             UserAmtDS positive = maxHeap.poll();
             UserAmtDS negitive = minHeap.poll();
             System.out.println(positive+" "+negitive);
             User receiver = positive.getUser();
             User payer = negitive.getUser();
             double payeeToPayerAmt = Math.min(positive.getAmount(),Math.abs(negitive.getAmount()));
             Transaction transaction = new Transaction(payer,receiver,payeeToPayerAmt);
             transactionsList.add(transaction);

//             append back the lefover amount to heap
            double additionalAmountAfterTotaling = positive.getAmount()+negitive.getAmount();
            if(additionalAmountAfterTotaling>0){
                maxHeap.add(new UserAmtDS(receiver,additionalAmountAfterTotaling));
                //if +ve amount left
//                that means payer has to recieve more
            }else if(additionalAmountAfterTotaling<0) {
                minHeap.add(new UserAmtDS(payer,additionalAmountAfterTotaling));
                //if -ve amount left
//                that means payer(debt full filled).: payee has some more debt to be paid
            }
        }
        return transactionsList;
    }

    public List<UserAmtDS> getFinalValues(List<Expense> expenses){
        List<User> users = userRepository.findAll();
        System.out.println("getFinalValues:: "+ users);

//        1->
//        for every user goto expenses -> paid by and owed expense user and get amount and
//        sum them
//        if+ve then that user must get amount from others and -ve vice
        List<UserAmtDS> result = new ArrayList<>();//list of user,(+-)amount
        for(User user:users) {
            double extraAmount = 0;
            for (Expense expense : expenses) {
                extraAmount += expense.getPaidBy().stream()
                        .filter(expenseUser -> expenseUser.getUser().equals(user))
                        .mapToDouble(expenseUser -> expenseUser.getAmount())
                        .sum();
                extraAmount -= expense.getOwedBy().stream()
                        .filter(expenseUser -> expenseUser.getUser().equals(user))
                        .mapToDouble(expenseUser -> expenseUser.getAmount())
                        .sum();
            }
            result.add(new UserAmtDS(user, extraAmount));
        }
        return result;
    }
}

@Getter
@Setter
@ToString
class UserAmtDS {
    private User user;
    private double amount;
    public UserAmtDS(User user,double amount){
        this.user = user;
        this.amount = amount;
    }
}
