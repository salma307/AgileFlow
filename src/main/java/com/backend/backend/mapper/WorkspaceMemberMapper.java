package com.backend.backend.mapper;


import com.backend.backend.dao.entities.WorkspaceMember;
import com.backend.backend.dto.workspaceMember.WorkspaceMemberRequestDto;
import com.backend.backend.dto.workspaceMember.WorkspaceMemberResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceMemberMapper {

    @Autowired
    private ModelMapper modelMapper;

    public WorkspaceMemberResponseDto toResponseDto(WorkspaceMember workspaceMember){
        WorkspaceMemberResponseDto workspaceMemberResponseDto = modelMapper.map(workspaceMember,WorkspaceMemberResponseDto.class);



        return workspaceMemberResponseDto;

    }

    public WorkspaceMember requesttoEntity(WorkspaceMemberRequestDto workspaceMemberRequestDto){

        if (workspaceMemberRequestDto == null) return null;

        WorkspaceMember workspaceMember = new WorkspaceMember();

        workspaceMember.setRole(workspaceMemberRequestDto.getRole());

        return workspaceMember;

    }
}
