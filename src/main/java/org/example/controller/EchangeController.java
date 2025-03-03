package org.example.controller;

import org.example.model.Echange;
import org.example.service.EchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/echanges")
public class EchangeController {

    @Autowired
    private EchangeService echangeService;

    @PostMapping("/proposer")
    public Echange proposerEchange(@RequestBody Echange echange) {
        return echangeService.proposerEchange(
                echange.getObjetOffert().getId(),  // Utilisation de l'ID de l'objet offert
                echange.getObjetDemande().getId(),  // Utilisation de l'ID de l'objet demandé
                echange.getDemandeur().getId(),     // Utilisation de l'ID du demandeur
                echange.getReceveur().getId()       // Utilisation de l'ID du receveur
        );
    }


    @PostMapping("/accepter/{id}")
    public Echange accepterEchange(@PathVariable int id) {
        return echangeService.accepterEchange(id);
    }

    @PostMapping("/refuser/{id}")
    public Echange refuserEchange(@PathVariable int id) {
        return echangeService.refuserEchange(id);
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public List<Echange> getEchangesByUtilisateurId(@PathVariable int utilisateurId) {
        return echangeService.getEchangesByUtilisateurId(utilisateurId);
    }
}
