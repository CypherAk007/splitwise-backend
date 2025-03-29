package com.backend.splitwise.repositories;

import com.backend.splitwise.models.Expense;
import com.backend.splitwise.models.ExpenseUser;
import com.backend.splitwise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseUserRepository extends JpaRepository<Expense,Long> {
    List<ExpenseUser> findAllByUser(User user);
}
