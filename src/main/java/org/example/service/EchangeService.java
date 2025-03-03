package org.example.service;

import org.example.model.Echange;
import org.example.model.Objet;
import org.example.model.Utilisateur;
import org.example.repository.EchangeRepository;
import org.example.repository.ObjetRepository;
import org.example.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EchangeService {

    @Autowired
    private EchangeRepository echangeRepository;

    @Autowired
    private ObjetRepository objetRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Echange proposerEchange(int objetOffertId, int objetDemandeId, int demandeurId, int receveurId) {
        Objet objetOffert = objetRepository.findById(objetOffertId)
                .orElseThrow(() -> new RuntimeException("Objet offert non trouvé"));
        Objet objetDemande = objetRepository.findById(objetDemandeId)
                .orElseThrow(() -> new RuntimeException("Objet demandé non trouvé"));

        Utilisateur demandeur = utilisateurRepository.findById(demandeurId)
                .orElseThrow(() -> new RuntimeException("Demandeur non trouvé"));
        Utilisateur receveur = utilisateurRepository.findById(receveurId)
                .orElseThrow(() -> new RuntimeException("Receveur non trouvé"));

        Echange echange = new Echange();
        echange.setObjetOffert(objetOffert);
        echange.setObjetDemande(objetDemande);
        echange.setDemandeur(demandeur);
        echange.setReceveur(receveur);
        echange.setStatut("EN_ATTENTE");

        // Définir explicitement la date de demande si vous ne voulez pas que la base de données le fasse
        echange.setDateDemande(LocalDateTime.now());  // Définit la date actuelle

        return echangeRepository.save(echange);
    }


    public Echange accepterEchange(int echangeId) {
        Echange echange = echangeRepository.findById(echangeId)
                .orElseThrow(() -> new RuntimeException("Échange non trouvé"));
        echange.setStatut("ACCEPTE");
        return echangeRepository.save(echange);
    }

    public Echange refuserEchange(int echangeId) {
        Echange echange = echangeRepository.findById(echangeId)
                .orElseThrow(() -> new RuntimeException("Échange non trouvé"));
        echange.setStatut("REFUSE");
        return echangeRepository.save(echange);
    }

    public List<Echange> getEchangesByUtilisateurId(int utilisateurId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return echangeRepository.findByDemandeurOrReceveur(utilisateur, utilisateur);
    }
}
