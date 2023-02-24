package com.system.project_management_system.services.impl;

import com.system.project_management_system.entity.*;
import com.system.project_management_system.pojo.ProjectPojo;
import com.system.project_management_system.pojo.TaskPojo;
import com.system.project_management_system.repo.*;
import com.system.project_management_system.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepo projectRepo;

    private final ProjectUserRepo projectUserRepo;

    private final UserRepo userRepo;


    private final TaskRepo taskRepo;
    private final BoardRepo boardRepo;

    @Override
    public Integer saveNewProject(ProjectPojo projectPojo) {
        Project project = new Project();

        project.setName(projectPojo.getName());
        project.setDescription(projectPojo.getDescription());

//        Convert String Date Format to Local Date Format
        String dateString = projectPojo.getStart_date();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate startDate = LocalDate.parse(dateString, formatter);

//        Set Start Date of Project
        project.setStart_date(startDate);

//        Get Current Date
        LocalDate todayDate = LocalDate.now();

//        Compare todayDate with Start date
        if (startDate.isBefore(todayDate)){
            project.setStatus("Started");
        }else if (startDate.isAfter(todayDate)){
            project.setStatus("Pending");
        }else{
            project.setStatus("Started");
        }


        projectRepo.save(project);




        return project.getId();

    }

    @Override
    public int SaveTask() {
        Task task = new Task();
        task.setName("Try Task");
        task.setStatus("Completed");
       // task.setProject(projectRepo.findById(4).orElseThrow());
        taskRepo.save(task);
        return task.getId();
    }

    @Override
    public void SaveJoiningTaskTable(String username, Integer project_id){
//        Task_User task_user = new Task_User();
//        task_user.setUser(userRepo.findByUsername(username).orElseThrow());
//        task_user.setTask(taskRepo.findById(project_id).orElseThrow());
//        taskUserRepo.save(task_user);

    }

    @Override
    public void SaveJoiningProjectTable(String username, Integer project_id){
        Project_User project_user = new Project_User();
        project_user.setUser(userRepo.findByUsername(username).orElseThrow());
        project_user.setProject(projectRepo.findById(project_id).orElseThrow());
        projectUserRepo.save(project_user);

    }



    @Override
    public Integer UserAssociatedId(String username) {
        int id = userRepo.findIdByUsernames(username);
        return id;
    }



    @Override
    public Long TotalProjects(Integer user_id) {
        Long no_of_Projects = projectUserRepo.totalProject(user_id);
        System.out.println("-----------------");
        System.out.println("Total No o Projects");
        System.out.println(no_of_Projects);
        System.out.println("------------------------");
        return no_of_Projects;
    }


    @Override
    public Long OngoingProjects(Integer user_id) {
        Long no_of_current_Projects = projectUserRepo.runningProject(user_id);
        System.out.println("-----------------");
        System.out.println("Total No Current Projects");
        System.out.println(no_of_current_Projects);
        System.out.println("------------------------");
        return no_of_current_Projects;
    }

    @Override
    public List<Task> AllTasks(Integer user_id) {
//        Optional<Task> tasks = taskUserRepo.AllTask(user_id);
        List<Task> tasks = taskRepo.findTasksByUserId(user_id);

        System.out.println("-----------------");
        System.out.println("Total No of Tasks");
        for (Task task : tasks){
            System.out.println(task.getName());
            System.out.println(task.getBoard().getName());
        }
        System.out.println("------------------------");
        return tasks;
    }

    @Override
    public List<Project> AllProject(Integer user_id) {
//        List<Project> projects = projectUserRepo.AllProject(user_id);
        List<Project> projects = projectUserRepo.findProjectsByUserId(user_id);



        return projects;
    }

    public List<Board> AllBoard(Integer user_id){
//        List<Project> projects = projectUserRepo.findProjectsByUserId(user_id);
//
//        List<Integer> projectIds = projects.stream().map(Project::getId).collect(Collectors.toList());
//        List<Board> allBoard = new ArrayList<>();
//
//        System.out.println("-------------------------------------------");
//        System.out.println("HERE IS THE BOARD LIST ");
//        for (Integer projectId : projectIds) {
//            List<Board> board = projectUserRepo.findBoardofID(projectId); // assuming you have a TaskRepository that can fetch tasks by projectId
//            allBoard.addAll(board);
//            System.out.println( board );
//
//        }
//
//        System.out.println("-------------------------------------------");
//        System.out.println("");
//
//
//
//        return allBoard;

        List<Board> boards = projectUserRepo.BoardofParticularUSer(user_id);
        return boards;





    }

    @Override
    public String updatetaskstatus(Integer task_id, String status) {
        System.out.println("-------------------------------");
        System.out.println("REACHED TO UPDATED");
        System.out.println(task_id);
        System.out.println(status);
        try {
            taskRepo.updateTaskStatus(task_id, status);
            System.out.println("UPDATED ");
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e);
        }

        return "UPDATED";
    }

    @Override
    public List<String> filterBoard(String projectname) {
        return boardRepo.filterboard(projectname);
    }

    @Override
    public List<Task> findtasks(String projectname, String boardname, Integer userid) {
        return taskRepo.findTasksByProjectNameBoardNameAndUserId(projectname,boardname,userid);
    }

    @Override
    public String updatetask(TaskPojo taskPojo) {
        String taskname = taskPojo.getName();
        LocalDateTime date = taskPojo.getAssign_date();
        Integer id = taskPojo.getId();

        taskRepo.updateTask(taskname,date,id);

        return "SUCEED";
    }
}
