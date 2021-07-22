package fr.esgi.stonks.authentication.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    Set<Role> roles;
}
