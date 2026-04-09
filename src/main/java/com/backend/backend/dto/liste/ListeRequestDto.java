package com.backend.backend.dto.liste;

import com.backend.backend.dao.enums.ListType;
import lombok.Data;

@Data
public class ListeRequestDto {
    private String name;
    private ListType type;
    private int order;
    private String folderId;
    private String sprintId;

}
