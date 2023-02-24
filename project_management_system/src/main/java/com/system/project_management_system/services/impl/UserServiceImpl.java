package com.system.project_management_system.services.impl;

import com.system.project_management_system.entity.User;
import com.system.project_management_system.exception.AppException;
import com.system.project_management_system.pojo.UserPojo;
import com.system.project_management_system.repo.UserRepo;
import com.system.project_management_system.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/projectmgmt";


    @Override
    public String saveUser(UserPojo userPojo) throws IOException {
        User user = new User();
//        if (userPojo.getId() != null){
//            user = userRepo.findById(userPojo.getId()).orElseThrow(())
//        }

        user.setEmail(userPojo.getEmail());
        user.setUsername(userPojo.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(userPojo.getPassword()));

        System.out.println("-------here");

        System.out.println("here-------");

        if(userPojo.getUserimage()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, userPojo.getUserimage().getOriginalFilename());
            fileNames.append(userPojo.getUserimage().getOriginalFilename());
            Files.write(fileNameAndPath, userPojo.getUserimage().getBytes());

            user.setUserimage(userPojo.getUserimage().getOriginalFilename());
        }

        userRepo.save(user);
   //    return new UserPojo(user);
        return "Created";

    }

    @Override
    public List<User> findbyUsername(String username) {
        try {
            return userRepo.findByUsernames(username);
        }catch (Exception e) {
            new AppException("Invalid User email", HttpStatus.BAD_REQUEST);
            return null;
        }


    }



}
