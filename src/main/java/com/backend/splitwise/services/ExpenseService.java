package com.backend.splitwise.services;

import com.backend.splitwise.models.*;
import com.backend.splitwise.repositories.ExpenseRepository;
import com.backend.splitwise.repositories.GroupRepository;
import com.backend.splitwise.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;

    public ExpenseService(GroupRepository groupRepository, UserRepository userRepository, ExpenseRepository expenseRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
    }

    public Expense createExpense(double amount, Map<Long, Double> payers, Map<Long, Double> owers, String description, Long createdBy, Long groupId) {

        User user = userRepository.findById(createdBy).orElseThrow(()-> new RuntimeException("User Not Found!!"));
        Group group = groupRepository.findById(groupId).orElseThrow(()-> new RuntimeException("Group Not Found!!"));
        Expense expense = new Expense();
        expense.setAmount(amount);
        List<ExpenseUser> paidBy = new ArrayList<>();
        for(Map.Entry<Long,Double> entry:payers.entrySet()){
            User user0 = userRepository.findById(entry.getKey()).orElseThrow(()-> new RuntimeException("User Not Found!!"));
            paidBy.add(new ExpenseUser(expense,entry.getValue(),user0, ExpenseUserType.PAIDBY));
        }
        List<ExpenseUser> owedBy = new ArrayList<>();
        for(Map.Entry<Long,Double> entry:owers.entrySet()){
            User user1 = userRepository.findById(entry.getKey()).orElseThrow(()-> new RuntimeException("User Not Found!!"));
            owedBy.add(new ExpenseUser(expense,entry.getValue(),user1, ExpenseUserType.OWEDBY));
        }
        expense.setPaidBy(paidBy);
        expense.setOwedBy(owedBy);
        expense.setDescription(description);
        expense.setExpenseType(ExpenseType.NORMAL);
        expense.setGroup(group);
        expense.setCreateBy(user);
        return expenseRepository.save(expense);
    }

    public Expense createPayment(double amount, Map<Long, Double> payers, Map<Long, Double> owers, String description,  Long createdBy, Long groupId) {

        User user = userRepository.findById(createdBy).orElseThrow(()-> new RuntimeException("User Not Found!!"));
        Group group = groupRepository.findById(groupId).orElseThrow(()-> new RuntimeException("Group Not Found!!"));
        Expense expense = new Expense();
        expense.setAmount(amount);
        List<ExpenseUser> paidBy = new ArrayList<>();
        for(Map.Entry<Long,Double> entry:payers.entrySet()){
            User user0 = userRepository.findById(entry.getKey()).orElseThrow(()-> new RuntimeException("User Not Found!!"));
            paidBy.add(new ExpenseUser(expense,entry.getValue(),user0, ExpenseUserType.PAIDBY));
        }
        List<ExpenseUser> owedBy = new ArrayList<>();
        for(Map.Entry<Long,Double> entry:owers.entrySet()){
            User user1 = userRepository.findById(entry.getKey()).orElseThrow(()-> new RuntimeException("User Not Found!!"));
            owedBy.add(new ExpenseUser(expense,entry.getValue(),user1, ExpenseUserType.OWEDBY));
        }

        expense.setPaidBy(paidBy);
        expense.setOwedBy(owedBy);
        expense.setDescription(description);
        expense.setExpenseType(ExpenseType.PAYMENT);
        expense.setGroup(group);
        expense.setCreateBy(user);
        return expenseRepository.save(expense);
    }
}
