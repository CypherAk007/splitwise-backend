package com.backend.splitwise.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_groups")//jpa query name changed
//@Table(name = "groups") // ✅ Table name changed to "groups" in DB
public class Group extends BaseModel{
    private String name;

//    Admin - > 1 user can create many groups
//    1 group and be createby one user
    @ManyToOne
    private User createdBy;

    @ManyToMany
    private List<User> members;

    @OneToMany
    private List<Expense> expenses;
    //    Why do we have expenses when Expense is also having group ?
//    - given a particular expense to which group does it belong to ?
//    - if we didn't have expense then we have to iterate every group and
//            check if this expense belongs to the particular group or not

}
