package com.backend.splitwise.controllers;


import com.backend.splitwise.dtos.group.CreateGroupRequestDTO;
import com.backend.splitwise.dtos.group.CreateGroupResponseDTO;
import com.backend.splitwise.dtos.group.ResponseStatus;
import com.backend.splitwise.models.Expense;
import com.backend.splitwise.models.Group;
import com.backend.splitwise.services.GroupService;
import org.springframework.stereotype.Controller;

@Controller
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    public CreateGroupResponseDTO createGroup(CreateGroupRequestDTO createGroupRequestDTO){
        CreateGroupResponseDTO createGroupResponseDTO = new CreateGroupResponseDTO();
        try {
            Group group =  groupService.createGroup(createGroupRequestDTO.getName(),createGroupRequestDTO.getCreateById(),createGroupRequestDTO.getExpenses(),createGroupRequestDTO.getMembers());
            createGroupResponseDTO.setGroupId(group.getId());
            createGroupResponseDTO.setName(group.getName());
            createGroupResponseDTO.setMessage("Group Successfully Created!!");
            createGroupResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            createGroupResponseDTO.setMessage("Group Creation Failed!!");
            createGroupResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return createGroupResponseDTO;
    }
}
