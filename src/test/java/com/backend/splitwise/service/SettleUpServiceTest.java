package com.backend.splitwise.service;

import com.backend.splitwise.dtos.settleup.Transaction;
import com.backend.splitwise.models.*;
import com.backend.splitwise.repositories.ExpenseRepository;
import com.backend.splitwise.repositories.ExpenseUserRepository;
import com.backend.splitwise.repositories.GroupRepository;
import com.backend.splitwise.repositories.UserRepository;
import com.backend.splitwise.services.SettleUpService;
import com.backend.splitwise.strategy.SettleUpStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SettleUpServiceTest {
    @Mock
    private GroupRepository groupRepository;
    @Mock
    private  ExpenseRepository expenseRepository;
    @Mock
    private  SettleUpStrategy settleUpStrategy;
    @Mock
    private  UserRepository userRepository;
    @Mock
    private  ExpenseUserRepository expenseUserRepository;

    @InjectMocks
    private SettleUpService settleUpService;
    private User user1;
    private User user2;
    private Group group1;
    @BeforeEach
    void setUp(){
        user1 = new User("Abhishek","1234567890","1234567890");
        user1.setId(1L);
        user2 = new User("Ak","1234567890","1234567890");

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void settleUpUserTest(){
        Expense expense1 = new Expense();
        ExpenseUser expenseUser1 = new ExpenseUser(expense1,100,user1, ExpenseUserType.PAIDBY);
        ExpenseUser expenseUser2 = new ExpenseUser(expense1,50,user2, ExpenseUserType.OWEDBY);
        ExpenseUser expenseUser3 = new ExpenseUser(expense1,50,user1, ExpenseUserType.OWEDBY);
        expense1.setPaidBy(Arrays.asList(expenseUser1));
        expense1.setOwedBy(Arrays.asList(expenseUser2,expenseUser3));
        expenseUser1.setExpense(expense1);
        expenseUser2.setExpense(expense1);
        expenseUser3.setExpense(expense1);
        Transaction transaction = new Transaction(user2,user1,50);
        Long userId = 1L;

        List<Expense> expenses = new ArrayList<>(Arrays.asList(expense1));

        when(userRepository.findById(userId)).thenReturn(Optional.of(user1));
        when(expenseUserRepository.findAllByUser(user1)).thenReturn(Arrays.asList(expenseUser1,expenseUser3));
        when(settleUpStrategy.settleUpByStrategy(anyList())).thenReturn(Arrays.asList(transaction));

        List<Transaction> transactions = settleUpService.settleUpUser(userId);
        System.out.println("Expenses retrieved: " + expenses.size());
        expenses.forEach(expense -> System.out.println("Expense ID: " + expense.getId()));
        assertNotNull(transactions);
        assertEquals(1,transactions.size());
        assertEquals(50,transactions.get(0).getAmount());
        assertEquals(user1,transactions.get(0).getReceiver());
        assertEquals(user2,transactions.get(0).getPayer());

        verify(userRepository).findById(userId);
        verify(expenseUserRepository).findAllByUser(user1);
        verify(settleUpStrategy).settleUpByStrategy(anyList());
    }

    @Test
    void settleUpGroupTest(){
        Expense expense1 = new Expense();
        ExpenseUser expenseUser1 = new ExpenseUser(expense1,100,user1, ExpenseUserType.PAIDBY);
        ExpenseUser expenseUser2 = new ExpenseUser(expense1,50,user2, ExpenseUserType.OWEDBY);
        ExpenseUser expenseUser3 = new ExpenseUser(expense1,50,user1, ExpenseUserType.OWEDBY);
        expense1.setPaidBy(Arrays.asList(expenseUser1));
        expense1.setOwedBy(Arrays.asList(expenseUser2,expenseUser3));
        expenseUser1.setExpense(expense1);
        expenseUser2.setExpense(expense1);
        expenseUser3.setExpense(expense1);
        group1 = new Group("GoaGroup",user1,Arrays.asList(user1,user2),Arrays.asList(expense1));
        Transaction transaction = new Transaction(user2,user1,50);
        Long groupId = 1L;
        group1.setId(1L);

        List<Expense> expenses = new ArrayList<>(Arrays.asList(expense1));

        when(groupRepository.findById(groupId)).thenReturn(Optional.of(group1));
        when(expenseRepository.findAllByGroup(group1)).thenReturn(Arrays.asList(expense1));
        when(settleUpStrategy.settleUpByStrategy(anyList())).thenReturn(Arrays.asList(transaction));

        List<Transaction> transactions = settleUpService.settleUpGroup(groupId);
        System.out.println("Expenses retrieved: " + expenses);
        assertNotNull(transactions);
        assertEquals(1,transactions.size());
        assertEquals(50,transactions.get(0).getAmount());
        assertEquals(user1,transactions.get(0).getReceiver());
        assertEquals(user2,transactions.get(0).getPayer());


    }

}
