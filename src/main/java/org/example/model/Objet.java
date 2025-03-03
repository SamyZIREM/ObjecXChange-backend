package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "objet")
@Getter
@Setter
public class Objet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String description;

    // L'utilisateur propriétaire de l'objet
    @ManyToOne
    @JoinColumn(name = "proprietaire_id", nullable = false)
    private Utilisateur utilisateur;
}
