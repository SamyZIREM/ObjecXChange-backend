package org.example.repository;

import org.example.model.Objet;
import org.example.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ObjetRepository extends JpaRepository<Objet, Integer> {
    List<Objet> findByNomContaining(String keyword); // Recherche par mot-clé
    List<Objet> findByUtilisateur(Utilisateur utilisateur); // Récupérer les objets d'un utilisateur
}
