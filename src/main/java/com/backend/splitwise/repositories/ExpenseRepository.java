package com.backend.splitwise.repositories;

import com.backend.splitwise.models.Expense;
import com.backend.splitwise.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
//    write custom querry to find allexpenses by group
    List<Expense> findAllByGroup(Group group);


}
