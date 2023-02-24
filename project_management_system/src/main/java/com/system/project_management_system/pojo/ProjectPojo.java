package com.system.project_management_system.pojo;

import com.system.project_management_system.entity.Project;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectPojo {
    private Integer id;

    @NotEmpty(message = "Project Name cannot be Empty")
    private String name;

    private String description;

    private String start_date;

    private String end_date;

    private String status;

    public ProjectPojo(Project project){
        this.id=project.getId();
        this.name=project.getName();
        this.description=project.getDescription();
        this.start_date = String.valueOf(project.getStart_date());
        this.end_date = String.valueOf(project.getEnd_date());
        this.status = project.getStatus();
    }
}
