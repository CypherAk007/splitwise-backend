package com.backend.splitwise.dtos.settleup;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SettleUpUserResponseDTO {
    private ResponseStatus status;
//    once settle up is clicked then you have to return list of transactions
//    we dont have transactions so we genarate it from expense
//    temporary object transaction is created for above
    private List<Transaction> transactions;



}
