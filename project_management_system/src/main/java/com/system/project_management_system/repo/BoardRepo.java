package com.system.project_management_system.repo;

import com.system.project_management_system.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepo extends JpaRepository<Board, Integer> {

    @Query(nativeQuery = true,value = "select DISTINCT b.name  FROM Board b JOIN Project p ON p.id = b.project_id where p.name=?1")
    List<String> filterboard(String project_name);


}
