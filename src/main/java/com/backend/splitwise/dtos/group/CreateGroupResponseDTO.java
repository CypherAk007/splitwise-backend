package com.backend.splitwise.dtos.group;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateGroupResponseDTO {
    private Long groupId;
    private String name;
    private ResponseStatus responseStatus;
    private String message;

}
