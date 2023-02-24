//package com.system.project_management_system.repo;
//
//import com.system.project_management_system.entity.Project;
//import com.system.project_management_system.entity.Task;
//import com.system.project_management_system.entity.Task_User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface TaskUserRepo extends JpaRepository<Task_User,Integer> {
//
//
// //   @Query(value = "SELECT t FROM task_user tu JOIN users u ON tu.user_id = u.id JOIN task t ON tu.task_ID = t.id WHERE tu.user_id = ?1 ", nativeQuery = true)
////    Optional<Task> AllTask(Integer userId);
//
//   @Query("SELECT t FROM Task t JOIN Task_User tu ON t.id = tu.task.id WHERE tu.user.id = :userId ORDER BY t.changed_date DESC")
//    //@Query("SELECT t FROM Task t JOIN board b ON t.board.id = b.id JOIN Task_User tu ON t.id = tu.task.id WHERE tu.user_id = ?1")
//   // @Query(nativeQuery = true,value = "Select task from board,task,task_user where task.board_id = board.id and task.id = task_user.task_id and task_user.user_id = ?1")
//    List<Task> findTasksByUserId(Integer userId);
//
//    @Query(value = "SELECT new Task (t.id,t.name,t.assign_date,t.changed_date,t.status,t.board,t.user) FROM Task t " +
//            "JOIN Board b ON t.board.id = b.id " +
//            "JOIN Project p ON b.project.id = p.id " +
//            "JOIN Project_User pu ON p.id = pu.project.id " +
//            "WHERE p.name = :projectName " +
//            "AND b.name = :boardName " +
//            "AND pu.user.id = :userId")
//    List<Task> findTasksByProjectNameBoardNameAndUserId(
//            @Param("projectName") String projectName,
//            @Param("boardName") String boardName,
//            @Param("userId") Integer userId);
//
//}
