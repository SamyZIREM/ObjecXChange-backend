package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "echange")
@Getter
@Setter
public class Echange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String statut; // "EN_ATTENTE", "ACCEPTE", "REFUSE"

    @Column(nullable = false)
    private LocalDateTime dateDemande;

    // Références aux objets impliqués dans l'échange
    @ManyToOne
    @JoinColumn(name = "objet_offert_id", nullable = false)
    private Objet objetOffert;

    @ManyToOne
    @JoinColumn(name = "objet_demande_id", nullable = false)
    private Objet objetDemande;

    // Références aux utilisateurs impliqués dans l'échange
    @ManyToOne
    @JoinColumn(name = "demandeur_id", nullable = false)
    private Utilisateur demandeur;

    @ManyToOne
    @JoinColumn(name = "receveur_id", nullable = false)
    private Utilisateur receveur;
}
