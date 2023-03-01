package br.com.dea.management.deamanagement.student.domain;

import br.com.dea.management.deamanagement.user.domain.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String university;

    @Column
    private String graduation;

    @Column
    private LocalDate finishDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
