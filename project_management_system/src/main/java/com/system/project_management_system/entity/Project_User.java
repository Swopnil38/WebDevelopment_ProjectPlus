package com.system.project_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project_user")
public class Project_User {
    @Id
    @SequenceGenerator(name = "pms_project_user_seq_gen", sequenceName = "pms_project_user_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "pms_project_user_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_userId"))
    private User user;

    @ManyToOne()
    @JoinColumn(name = "project_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_projectId"))
    private Project project;
}
