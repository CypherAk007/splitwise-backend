package com.backend.splitwise.controllers;

import com.backend.splitwise.dtos.settleup.*;
import com.backend.splitwise.services.SettleUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SettleUpController {
    private final SettleUpService settleUpService;

    @Autowired
    public SettleUpController(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
    }

    public SettleUpUserResponseDTO settleUpUser(SettleUpUserRequestDTO requestDTO){
        SettleUpUserResponseDTO responseDTO = new SettleUpUserResponseDTO();
        try{
            List<Transaction> transactions =  settleUpService.settleUpUser(requestDTO.getUserId());
            responseDTO.setStatus(ResponseStatus.SUCCESS);
            responseDTO.setTransactions(transactions);

        }catch (Exception e){
            responseDTO.setStatus(ResponseStatus.FAILURE);
        }

        return responseDTO;
    }

    public SettleUpGroupResponseDTO settleUpGroup(SettleUpGroupRequestDTO requestDTO){
        SettleUpGroupResponseDTO responseDTO = new SettleUpGroupResponseDTO();
        try{
            List<Transaction> transactions =  settleUpService.settleUpGroup(requestDTO.getGroupId());
            responseDTO.setStatus(ResponseStatus.SUCCESS);
            responseDTO.setTransactions(transactions);

        }catch (Exception e){
            responseDTO.setStatus(ResponseStatus.FAILURE);
        }

        return responseDTO;
    }
}
