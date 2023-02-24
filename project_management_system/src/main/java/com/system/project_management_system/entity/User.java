package com.system.project_management_system.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "users")
public class User implements UserDetails {


    @Id
    @SequenceGenerator(name = "pms_user_seq_gen", sequenceName = "pms_user_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "pms_user_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false,name = "email")
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name = "image",columnDefinition = "varchar(255) default 'projectmgmt'")
    private String userimage;

    @Transient
    private String imageBase64;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    @Override
    public String getUsername(){
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
