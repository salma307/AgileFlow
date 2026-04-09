package com.backend.backend.dto.liste;

import com.backend.backend.dao.entities.Task;
import com.backend.backend.dao.enums.ListType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class ListeResponseDto {
    private String id;
    private String name;
    private ListType type;
    private int order;
    private LocalDateTime createdAt;

    private String folderId;
    private String folderName;

    private String sprintId;
    private String sprintName;

    //private List<TaskDto> tasks;
}
