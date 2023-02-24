package com.system.project_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "project")
public class Project {
    @Id
    @SequenceGenerator(name = "pms_project_seq_gen", sequenceName = "pms_project_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "pms_project_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description",nullable = true)
    private String description;

    @Column(name = "start_date",columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate start_date;

    @Column(name = "end_date")
    private LocalDate end_date;

    @Column(name = "status")
    private String status;



}
