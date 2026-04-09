package com.backend.backend.dto.space;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpaceDto {
    private String id;
    private String name;
    private String color;
    private boolean isPrivate;

    private String workspaceid;
    private String workspaceName;

}