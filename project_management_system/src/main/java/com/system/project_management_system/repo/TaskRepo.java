package com.system.project_management_system.repo;

import com.system.project_management_system.entity.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.status = :status WHERE t.id = :id")
    void updateTaskStatus(@Param("id") Integer id, @Param("status") String status);

    @Query("SELECT t FROM Task t WHERE t.user.id = :userId ORDER BY t.changed_date DESC")
    List<Task> findTasksByUserId(Integer userId);

    @Query(value = "SELECT new Task (t.id,t.name,t.assign_date,t.changed_date,t.status,t.board,t.user) FROM Task t " +
            "JOIN Board b ON t.board.id = b.id " +
            "JOIN Project p ON b.project.id = p.id " +
            "JOIN Project_User pu ON p.id = pu.project.id " +
            "WHERE p.name = :projectName " +
            "AND b.name = :boardName " +
            "AND t.user.id = :userId")
    List<Task> findTasksByProjectNameBoardNameAndUserId(
            @Param("projectName") String projectName,
            @Param("boardName") String boardName,
            @Param("userId") Integer userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "UPDATE Task t set t.name = ?1 , t.date = ?2 where t.id=?3")
    void updateTask(String taskname, LocalDateTime date, Integer id);
}
