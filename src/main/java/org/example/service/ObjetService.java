package org.example.service;

import org.example.model.Objet;
import org.example.model.Utilisateur;
import org.example.repository.ObjetRepository;
import org.example.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ObjetService {

    @Autowired
    private ObjetRepository objetRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // Ajouter un objet en liant l'objet à un utilisateur
    public String ajouterObjet(Objet objet) {
        Utilisateur proprietaire = utilisateurRepository.findById(objet.getUtilisateur().getId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        objet.setUtilisateur(proprietaire); // Lier l'objet à l'utilisateur
        objetRepository.save(objet); // Sauvegarder l'objet
        return "Objet ajouté avec succès"; // Retourner un message de succès
    }

    // Recherche des objets avec un mot-clé dans leur nom
    public List<Objet> rechercherObjets(String keyword) {
        return objetRepository.findByNomContaining(keyword); // Recherche par mot-clé
    }

    // Récupérer tous les objets d'un utilisateur donné
    public List<Objet> getObjetsByUtilisateurId(int utilisateurId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return objetRepository.findByUtilisateur(utilisateur); // Récupérer les objets de l'utilisateur
    }
}
