package com.backend.splitwise.dtos.settleup;



import com.backend.splitwise.models.User;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private User payer;
    private User receiver;
    private double amount;
}

//A has to pay B Rs. 500
