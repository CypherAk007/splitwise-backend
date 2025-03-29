package com.backend.splitwise.dtos.expenses;

import com.backend.splitwise.models.ExpenseType;
import com.backend.splitwise.models.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class CreateExpenseRequestDTO {
    private double amount;
    private Map<Long,Double> payers;
    private Map<Long,Double> owers;
    private String description;

    private Long groupId;
    private Long createdBy;

}
