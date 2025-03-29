package com.backend.splitwise.dtos.group;

import com.backend.splitwise.models.Expense;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class CreateGroupRequestDTO {
    private String name;
    private Long createById;
    private List<Long> members;
    private List<Expense> expenses;

}
