package com.system.project_management_system.repo;

import com.system.project_management_system.entity.Project;
import com.system.project_management_system.entity.Task;
import com.system.project_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Integer> {


}
