package com.backend.splitwise.services;

import com.backend.splitwise.dtos.settleup.*;
import com.backend.splitwise.models.Expense;
import com.backend.splitwise.models.ExpenseUser;
import com.backend.splitwise.models.Group;
import com.backend.splitwise.models.User;
import com.backend.splitwise.repositories.ExpenseRepository;
import com.backend.splitwise.repositories.ExpenseUserRepository;
import com.backend.splitwise.repositories.GroupRepository;
import com.backend.splitwise.repositories.UserRepository;
import com.backend.splitwise.strategy.SettleUpStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SettleUpService {
    private final GroupRepository groupRepository;
    private final ExpenseRepository expenseRepository;
    private final SettleUpStrategy settleUpStrategy;
    private final UserRepository userRepository;
    private final ExpenseUserRepository expenseUserRepository;

    public SettleUpService(GroupRepository groupRepository, ExpenseRepository expenseRepository, SettleUpStrategy settleUpStrategy, UserRepository userRepository, ExpenseUserRepository expenseUserRepository) {
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
        this.settleUpStrategy = settleUpStrategy;
        this.userRepository = userRepository;
        this.expenseUserRepository = expenseUserRepository;
    }

    public List<Transaction> settleUpUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not Not Found!!"));

        List<ExpenseUser> expenseUsers = expenseUserRepository.findAllByUser(user);


        List<Expense> expenses = expenseUsers.stream()
                .map(ExpenseUser::getExpense)
                .collect(Collectors.toList());

        List<Transaction> transactions =settleUpStrategy.settleUpByStrategy(expenses);
        return transactions;
    }

    public List<Transaction> settleUpGroup(Long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(()-> new RuntimeException("Group not Registered!!"));

        List<Expense> expenses = expenseRepository.findAllByGroup(group);

        List<Transaction> transactions = settleUpStrategy.settleUpByStrategy(expenses);
        return transactions;
    }
}
