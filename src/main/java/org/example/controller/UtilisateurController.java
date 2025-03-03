package org.example.controller;


import org.example.model.Utilisateur;
import org.example.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/inscription")
    public Utilisateur inscrireUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.inscrireUtilisateur(utilisateur.getNom(), utilisateur.getEmail(), utilisateur.getPassword());
    }

    @PostMapping("/connexion")
    public ResponseEntity<Map<String, Object>> seConnecter(@RequestParam String email, @RequestParam String password) {
        return utilisateurService.seConnecter(email, password);
    }
}
