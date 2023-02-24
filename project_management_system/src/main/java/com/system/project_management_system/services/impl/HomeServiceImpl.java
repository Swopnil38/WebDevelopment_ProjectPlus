package com.system.project_management_system.services.impl;

import com.system.project_management_system.entity.User;
import com.system.project_management_system.repo.UserRepo;
import com.system.project_management_system.services.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {
    private final UserRepo userRepo;



    @Override
    public User fetchById(Integer id) {
        return userRepo.findById(id).orElseThrow(()->new RuntimeException("Not Found"));
    }
}
