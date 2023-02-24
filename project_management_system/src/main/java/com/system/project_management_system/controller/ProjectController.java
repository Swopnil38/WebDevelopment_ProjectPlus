package com.system.project_management_system.controller;

import com.system.project_management_system.entity.Board;
import com.system.project_management_system.entity.Project;
import com.system.project_management_system.entity.Task;
import com.system.project_management_system.exception.AppException;
import com.system.project_management_system.pojo.ProjectPojo;
import com.system.project_management_system.pojo.TaskPojo;
import com.system.project_management_system.pojo.UserPojo;
import com.system.project_management_system.repo.ProjectUserRepo;
import com.system.project_management_system.services.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectUserRepo projectUserRepo;

    @PostMapping("/saveproject")
    public String SaveProject(@Valid ProjectPojo projectPojo,Principal principal){

            int project_id = projectService.saveNewProject(projectPojo);
            projectService.SaveJoiningProjectTable(principal.getName(),project_id);
        System.out.println("HERE REACHED ");
            return "redirect:/";


    }




    @GetMapping("/addproject")
    public String register(){
        return "new-project";
    }

    @GetMapping("/try-board")
    public String newboard(){
        return "user/board";
    }
    @GetMapping("/board")
    public String board(Model model){
        String name = "try";

        //Retreive USer ID
        int user_id = projectService.UserAssociatedId(name);
        //projectService.UserAssociatedTask(user_id);

        // int task_id = projectService.SaveTask();
        //projectService.SaveJoiningTaskTable(name,task_id);



        //Retreive All TAsk of User
        List <Task> taskList = projectService.AllTasks(user_id);

        //HAve Just Distinct Task
        List<String> distinctStatus = new ArrayList<>();
        for (Task task : taskList) {
            String status = task.getStatus();
            if (!distinctStatus.contains(status)) {
                distinctStatus.add(status);
            }
        }

        System.out.println(taskList.getClass());
        System.out.println("------------");
        if (!taskList.isEmpty()) {
            Task task = taskList.get(0);
            Class statusType = task.getStatus().getClass();
            System.out.println("Status column is of type: " + statusType.getName());
        } else {
            System.out.println("Task list is empty");
        }

        List<Project> allprojects = projectService.AllProject(user_id);
        List<Board> allboard = projectService.AllBoard(user_id);


        model.addAttribute("AllProject",allprojects);
        model.addAttribute("AllBoard",allboard);
        model.addAttribute("UniqueTask",distinctStatus);
        model.addAttribute("AllTaskList",taskList);
        return "board";
    }

    @GetMapping("/project")
    public String projectList(Model model){
        String name = "try";
        int user_id = projectService.UserAssociatedId(name);
        //projectService.UserAssociatedTask(user_id);

       // int task_id = projectService.SaveTask();
        //projectService.SaveJoiningTaskTable(name,task_id);




        Long total_projects = projectService.TotalProjects(user_id);
        Long ongoing_projects = projectService.OngoingProjects(user_id);
        model.addAttribute("totalprojects",total_projects);
        model.addAttribute("ongoingprojects",ongoing_projects);


        List<Project> projectList= projectService.AllProject(user_id);
        List<Task> taskList = projectService.AllTasks(user_id);
        List<Task> top5Tasks = taskList.stream()
                .limit(5)
                .collect(Collectors.toList());
        model.addAttribute("taskDetails",top5Tasks);
        model.addAttribute("projectDetails",projectList);

        List<Task> completedTasks = taskList.stream()
                .filter(task -> task.getStatus().equals("Completed"))
                .collect(Collectors.toList());
        int completed_task = completedTasks.size();
        int total_task = taskList.size();
        model.addAttribute("completetask",completed_task);
        model.addAttribute("totaltask",total_task);







        return "project";
    }

    @PostMapping("/update-task-status")
    public ResponseEntity<String> updateTaskStatus(@RequestParam Integer taskID,@RequestParam String status) {
        System.out.println("---------------------------------");
        System.out.println("REACHED PART 1");
        try {
            System.out.println("---------------------------------");
            System.out.println("REACHED PART 1 SUCESS");

            System.out.println(taskID);
            System.out.println(status);

            projectService.updatetaskstatus(taskID,status);

            // Return a success response
            return ResponseEntity.ok("Task status updated successfully.");
        } catch (Exception e) {
            System.out.println("---------------------------------");
            System.out.println("REACHED PART 1 FAILED");

            System.out.println(e.getMessage());
            // Return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating task status: " + e.getMessage());
        }
    }

    @PostMapping("/updated-board-name")
    @ResponseBody
    public List<String> returnboardname(@RequestParam String project_name){
        try {
            List<String> updatedboard = projectService.filterBoard(project_name);
            return updatedboard;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // Return an error response
            return null;
        }
    }

    @PostMapping("/updated-task-name")
    @ResponseBody
    public List<Task> returntaskname(@RequestParam String project_name,@RequestParam String board_name){
        try {
            String name = "try";
            int user_id = projectService.UserAssociatedId(name);
            List<Task> updatedboard = projectService.findtasks(project_name,board_name,user_id);
            return updatedboard;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // Return an error response
            return null;
        }
    }

    @PostMapping("/update-task")
    @ResponseBody
    public String updatetask(@Valid TaskPojo taskPojo){
        try{
            projectService.updatetask(taskPojo);
            return "SUCESS";
        }catch (Exception e){
            return "FAILED";
        }

    }


}
