package org.example.controller;

import org.example.model.Objet;
import org.example.service.ObjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/objets")
public class ObjetController {

    @Autowired
    private ObjetService objetService;

    // Ajouter un objet en utilisant un corps JSON
    @PostMapping("/ajouter")
    public String ajouterObjet(@RequestBody Objet objet) {
        return objetService.ajouterObjet(objet); // Retourne un message de succès
    }

    // Recherche des objets par un mot-clé dans leur nom
    @GetMapping("/rechercher")
    public List<Objet> rechercherObjets(@RequestParam String keyword) {
        return objetService.rechercherObjets(keyword);
    }


    // Récupérer tous les objets d'un utilisateur en particulier
    @GetMapping("/utilisateur/{utilisateurId}")
    public List<Objet> getObjetsByUtilisateurId(@PathVariable int utilisateurId) {
        return objetService.getObjetsByUtilisateurId(utilisateurId);
    }
}
