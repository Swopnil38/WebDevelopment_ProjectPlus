package com.system.project_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "board")
public class Board {
    @Id
    @SequenceGenerator(name = "pms_board_seq_gen", sequenceName = "pms_board_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "pms_board_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column()
    private String name;

    @ManyToOne
    @JoinColumn(name = "project_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "FK_projectId"))
    private Project project;


}
