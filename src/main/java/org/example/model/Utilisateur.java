package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "utilisateur")
@Getter
@Setter
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String email;
    private String password;

    // Liste des objets créés par l'utilisateur
    @JsonIgnore
    @OneToMany(mappedBy = "utilisateur")
    private List<Objet> objets;

    // Liste des échanges où l'utilisateur est impliqué
    @JsonIgnore
    @OneToMany(mappedBy = "demandeur")
    private List<Echange> echanges;
}
