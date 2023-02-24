package com.system.project_management_system.pojo;

import com.system.project_management_system.entity.Project;
import com.system.project_management_system.entity.Project_User;
import com.system.project_management_system.entity.User;

public class Project_UserPojo {
    private Integer id;

    private Integer project;

    private Integer user;

    public Project_UserPojo(Project_User project_user){
        this.user= project_user.getUser().getId();
        this.project= project_user.getProject().getId();
    }
}
