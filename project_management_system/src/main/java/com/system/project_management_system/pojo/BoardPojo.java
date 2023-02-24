package com.system.project_management_system.pojo;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BoardPojo {
    private Integer id;

    @NotEmpty(message = "Board Name cannot be Empty")
    private String name;
}
