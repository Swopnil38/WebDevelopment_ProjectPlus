package com.system.project_management_system.pojo;

import com.system.project_management_system.entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPojo {
//
//    @NotNull
//    @NotEmpty
    private Integer id;

    @NotEmpty(message = "Email can't be empty")
    private String email;


    private String username;


    @NotEmpty(message = "Password can't be empty")
    private String password;

    private MultipartFile userimage;

    public UserPojo(User user){
        this.id= user.getId();
        this.email= user.getEmail();
        this.username= user.getUsername();
        this.password=user.getPassword();
    }
}
