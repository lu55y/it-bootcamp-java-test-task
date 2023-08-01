package org.bootcamp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bootcamp.model.enums.Roles;


@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 40)
    private String lastname;
    @Column(nullable = false,length = 20)
    private String firstname;
    @Column(nullable = false,length = 40)
    private String surname;
    @Column(nullable = false,length = 50)
    private String email;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Roles role;

}
