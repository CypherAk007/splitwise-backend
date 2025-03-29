package com.backend.splitwise.services;

import com.backend.splitwise.dtos.settleup.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SettleUpService {

    public List<Transaction> settleUpUser(Long userId) {
        return List.of();
    }

    public List<Transaction> settleUpGroup(Long groupId) {
        return List.of();
    }
}
