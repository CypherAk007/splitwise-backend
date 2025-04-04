package com.backend.splitwise.dtos.settleup;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleUpGroupResponseDTO {
    private ResponseStatus status;
    private List<Transaction> transactions;
}
