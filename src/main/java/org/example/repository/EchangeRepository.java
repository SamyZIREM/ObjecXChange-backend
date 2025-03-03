package org.example.repository;

import org.example.model.Echange;
import org.example.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EchangeRepository extends JpaRepository<Echange, Integer> {
    List<Echange> findByDemandeurOrReceveur(Utilisateur demandeur, Utilisateur receveur);
}
