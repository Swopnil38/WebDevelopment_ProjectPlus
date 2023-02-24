package com.system.project_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
public class Task {
    @Id
    @SequenceGenerator(name = "pms_task_seq_gen", sequenceName = "pms_task_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "pms_task_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "assign_date",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime assign_date;

    @Column(name = "changed_date",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime changed_date;

    @Column(name = "status")
    private String status;

    @ManyToOne()
    @JoinColumn(name = "board_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_boardId"))
    private Board board;

    @ManyToOne()
    @JoinColumn(name = "user_id",
            referencedColumnName = "id",foreignKey = @ForeignKey(name = "FK_userId"))
    private User user;




}
