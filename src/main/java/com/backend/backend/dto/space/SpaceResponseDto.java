package com.backend.backend.dto.space;

import com.backend.backend.dao.entities.Folder;
import com.backend.backend.dao.entities.SpaceMember;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpaceResponseDto {
    private String id;
    private String name;
    private String color;
    private boolean isPrivate;

    private String workspaceid;
    private String workspaceName;

    private String adminid;
    private String adminName;

    //private List<SpaceMemberDto> spaceMembers;

    //private List<FolderDto> folders;

}