package com.backend.splitwise.dtos.expenses;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateExpenseResponseDTO {
    private Long expenseId;
    private double amount;
    private ResponseStatus responseStatus;
    private String message;

}
