package com.system.project_management_system.pojo;

import com.system.project_management_system.entity.Board;
import com.system.project_management_system.entity.Project;
import com.system.project_management_system.entity.Task;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskPojo {
    private Integer id;

    @NotEmpty(message = "Task Name Cannot be Empty")
    private String name;

    private LocalDateTime assign_date;

    private LocalDateTime changed_date;

    private String status;

    private Board board;

    public TaskPojo(Task task){
        this.id=task.getId();
        this.assign_date=task.getAssign_date();
        this.changed_date=task.getChanged_date();
        this.status=task.getStatus();
        this.board=task.getBoard();
    }
}
