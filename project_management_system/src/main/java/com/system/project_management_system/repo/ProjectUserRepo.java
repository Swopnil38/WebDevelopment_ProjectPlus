package com.system.project_management_system.repo;

import com.system.project_management_system.entity.Board;
import com.system.project_management_system.entity.Project;
import com.system.project_management_system.entity.Project_User;
import com.system.project_management_system.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectUserRepo extends JpaRepository<Project_User,Integer> {
    @Query(value = "select * from project_user where user_id=?1",nativeQuery = true)
    List<Project> findAllUserAssociatedProject(Integer id);

    @Query(value = "SELECT count(pu.project_id) FROM project_user pu JOIN users u ON pu.user_id = u.id JOIN project p ON pu.project_id = p.id WHERE pu.user_id = ?1",nativeQuery = true)
    Long totalProject(Integer user_id);

    //Could Be Combined with Above
    @Query(value = "SELECT p FROM project_user pu JOIN users u ON pu.user_id = u.id JOIN project p ON pu.project_id = p.id WHERE pu.user_id = ?1",nativeQuery = true)
    List<Project> findallProject(Integer user_id);


//    @Query(value = "SELECT b FROM Board b WHERE b.project IN (SELECT pu.project FROM Project_User pu WHERE pu.user.id = :userId)",nativeQuery = true)
  //  List<Board> findallBoard(@Param("userId") Integer userId);

    @Query(value = "SELECT b FROM Board b JOIN Project p ON b.project.id = p.id JOIN Project_User pu ON p.id = pu.project.id WHERE pu.user.id = ?1 ORDER BY b.name DESC",nativeQuery = true)
    List<Board> findallBoard(Integer user_id);

    @Query(value = "select b from  Board  b where b.project.id = ?1")
    List<Board> findBoardofID(Integer project_id);


    @Query(value = "SELECT count(pu.project_id) FROM project_user pu JOIN users u ON pu.user_id = u.id JOIN project p ON pu.project_id = p.id WHERE p.status = 'Started' AND pu.user_id = ?1",nativeQuery = true)
    Long runningProject(Integer user_id);

 //   @Query(value = "SELECT p FROM project p JOIN project_user pu ON p.id = pu.project.id WHERE pu.user.id = :userId ORDER BY p.start_date DESC",nativeQuery = true)
   // List<Project> findProjectsByUserId(@Param("userId") Integer userId);

    @Query("SELECT b FROM Board b JOIN Project p ON b.project.id = p.id JOIN Project_User pu ON p.id = pu.project.id WHERE pu.user.id = ?1")
    List<Board> BoardofParticularUSer(Integer user_id);

    @Query(value = "SELECT p FROM Project p JOIN Project_User pu ON p.id = pu.project.id WHERE pu.user.id = :userId ORDER BY p.start_date DESC")
    List<Project> findProjectsByUserId(@Param("userId") Integer userId);



}
