package com.backend.splitwise.strategy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.backend.splitwise.dtos.settleup.Transaction;
import com.backend.splitwise.models.Expense;
import com.backend.splitwise.models.ExpenseUser;
import com.backend.splitwise.models.ExpenseUserType;
import com.backend.splitwise.models.User;
import com.backend.splitwise.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class HeapStrategyTest {
    @Mock
    private UserRepository userRepository;


    @InjectMocks
    private HeapStrategy heapStrategy;

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
         user1  = new User("Abhishek","1234567890","1234567890");
         user2  = new User("AK","1234567890","1234567890");
         user3  = new User("BK","1234567890","1234567890");

    }

    @Test
    void settleUpByStrategy(){
        Expense expense1 = new Expense();
        expense1.setPaidBy(Arrays.asList(new ExpenseUser(expense1,100,user1, ExpenseUserType.PAIDBY)));
        expense1.setOwedBy(Arrays.asList(new ExpenseUser(expense1,50,user2, ExpenseUserType.OWEDBY),
                new ExpenseUser(expense1,50,user3,ExpenseUserType.OWEDBY)));

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1,user2,user3));

        List<Transaction> transactions = heapStrategy.settleUpByStrategy(Arrays.asList(expense1));
        System.out.println(transactions);
        assertEquals(2,transactions.size());
        assertEquals(user2,transactions.get(0).getPayer());
        assertEquals(user1,transactions.get(0).getReceiver());
        assertEquals(50.0,transactions.get(0).getAmount());
    }

    @Test
    void testGetFinalValues(){
        Expense expense1 = new Expense();
        expense1.setPaidBy(Arrays.asList(new ExpenseUser(expense1,200,user1,ExpenseUserType.PAIDBY)));
        expense1.setOwedBy(Arrays.asList(new ExpenseUser(expense1,100,user2,ExpenseUserType.OWEDBY)));
        Expense expense2 = new Expense();
        expense2.setPaidBy(Arrays.asList(new ExpenseUser(expense2,200,user3,ExpenseUserType.PAIDBY)));
        expense2.setOwedBy(Arrays.asList(new ExpenseUser(expense2,100,user1,ExpenseUserType.OWEDBY)));

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1,user2,user3));

        List<UserAmtDS> result = heapStrategy.getFinalValues(Arrays.asList(expense1,expense2));
        System.out.println(result);

        assertEquals(3,result.size());
        assertEquals(100,result.get(0).getAmount());
        assertEquals(-100,result.get(1).getAmount());
        assertEquals(200,result.get(2).getAmount());


    }


}
