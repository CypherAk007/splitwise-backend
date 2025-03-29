package com.backend.splitwise.controllers;

import com.backend.splitwise.dtos.expenses.CreateExpenseRequestDTO;
import com.backend.splitwise.dtos.expenses.CreateExpenseResponseDTO;
import com.backend.splitwise.dtos.expenses.ResponseStatus;
import com.backend.splitwise.models.Expense;
import com.backend.splitwise.services.ExpenseService;
import org.springframework.stereotype.Controller;

@Controller
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public CreateExpenseResponseDTO createExpense(CreateExpenseRequestDTO createExpenseRequestDTO){
        CreateExpenseResponseDTO createExpenseResponseDTO = new CreateExpenseResponseDTO();
        try {
            Expense expense =  expenseService.createExpense(createExpenseRequestDTO.getAmount(),createExpenseRequestDTO.getPayers(),createExpenseRequestDTO.getOwers(),createExpenseRequestDTO.getDescription(),createExpenseRequestDTO.getCreatedBy(),createExpenseRequestDTO.getGroupId());
            createExpenseResponseDTO.setExpenseId(expense.getId());
            createExpenseResponseDTO.setAmount(expense.getAmount());
            createExpenseResponseDTO.setMessage("Expense Successfully Created!!");
            createExpenseResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            createExpenseResponseDTO.setMessage("Expense Creation Failed!!");
            createExpenseResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return createExpenseResponseDTO;
    }

    public CreateExpenseResponseDTO createPayment(CreateExpenseRequestDTO createExpenseRequestDTO){
        CreateExpenseResponseDTO createExpenseResponseDTO = new CreateExpenseResponseDTO();
        try {
            Expense expense =  expenseService.createPayment(createExpenseRequestDTO.getAmount(),createExpenseRequestDTO.getPayers(),createExpenseRequestDTO.getOwers(),createExpenseRequestDTO.getDescription(),createExpenseRequestDTO.getCreatedBy(),createExpenseRequestDTO.getGroupId());
            createExpenseResponseDTO.setExpenseId(expense.getId());
            createExpenseResponseDTO.setAmount(expense.getAmount());
            createExpenseResponseDTO.setMessage("Payment Successfully Created!!");
            createExpenseResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            createExpenseResponseDTO.setMessage("Payment Creation Failed!!");
            createExpenseResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return createExpenseResponseDTO;
    }
}
