//package com.system.project_management_system.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "task_user")
//public class Task_User {
//    @Id
//    @SequenceGenerator(name = "pms_task_user_seq_gen", sequenceName = "pms_task_user_id_seq", allocationSize = 1)
//    @GeneratedValue(generator = "pms_task_user_seq_gen", strategy = GenerationType.SEQUENCE)
//    private Integer id;
//
//
//    @ManyToOne()
//    @JoinColumn(name = "task_id",
//            referencedColumnName = "id",
//            foreignKey = @ForeignKey(name = "FK_taskId"))
//    private Task task;
//
//    @ManyToOne()
//    @JoinColumn(name = "user_id",
//            referencedColumnName = "id",
//            foreignKey = @ForeignKey(name = "FK_userId"))
//    private User user;
//}
