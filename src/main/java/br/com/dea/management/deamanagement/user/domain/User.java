package br.com.dea.management.deamanagement.user.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@NamedQuery(name = "myQuery", query = "SELECT u FROM User u WHERE u.name = :name")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String linkedin;

}
