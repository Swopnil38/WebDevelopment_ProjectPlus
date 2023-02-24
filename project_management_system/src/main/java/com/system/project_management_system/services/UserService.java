package com.system.project_management_system.services;

import com.system.project_management_system.entity.User;
import com.system.project_management_system.pojo.UserPojo;

import java.io.IOException;
import java.util.List;

public interface UserService {

    String saveUser(UserPojo userPojo) throws IOException;

    List<User> findbyUsername(String username);
}
