package com.system.project_management_system.services;

import com.system.project_management_system.entity.Board;
import com.system.project_management_system.entity.Project;
import com.system.project_management_system.entity.Task;
import com.system.project_management_system.pojo.ProjectPojo;
import com.system.project_management_system.pojo.TaskPojo;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Integer saveNewProject(ProjectPojo projectPojo);

    int SaveTask();

    void SaveJoiningTaskTable(String username,Integer project_id);

    void SaveJoiningProjectTable(String username,Integer project_id);



    Integer UserAssociatedId(String username);



    Long TotalProjects(Integer user_id);



    Long OngoingProjects(Integer user_id);


    List<Task> AllTasks(Integer user_id);

    List<Project> AllProject(Integer user_id);

    List<Board> AllBoard(Integer user_id);

    String updatetaskstatus(Integer task_id,String status);
    List<String> filterBoard(String projectname);

    List<Task> findtasks(String projectname,String boardname, Integer userid);

    String updatetask(TaskPojo taskPojo);
}
