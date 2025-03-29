package com.backend.splitwise.services;

import com.backend.splitwise.models.Expense;
import com.backend.splitwise.models.Group;
import com.backend.splitwise.models.User;
import com.backend.splitwise.repositories.GroupRepository;
import com.backend.splitwise.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public GroupService(UserRepository userRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    public Group createGroup(String name, Long createById, List<Expense> expenses, List<Long> members) {
        Optional<Group> existingGroup = groupRepository.findByName(name);
        if(existingGroup.isPresent()){
            throw new RuntimeException("User with Phone Number Already Present. Please Login!!");
        }
        Optional<User> user = userRepository.findById(createById);
        if(!user.isPresent()){
            throw new RuntimeException("User Not Found!!");
        }
        Group group = new Group();
        group.setName(name);
        group.setCreatedBy(user.get());
        group.setExpenses(new ArrayList<>());
        List<User> membersList = new ArrayList<>();
        for(Long uid:members){
            Optional<User> user1 = userRepository.findById(uid);
            if(!user1.isPresent()){
                throw new RuntimeException("User Not Found!!");
            }
            membersList.add(user1.get());
        }
        group.setMembers(membersList);
        return groupRepository.save(group);

    }
}
