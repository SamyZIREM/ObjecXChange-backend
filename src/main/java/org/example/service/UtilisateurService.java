package org.example.service;

import org.example.model.Utilisateur;
import org.example.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur inscrireUtilisateur(String nom, String email, String password) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(nom);
        utilisateur.setEmail(email);
        utilisateur.setPassword(password);  // Assurez-vous de hasher le mot de passe
        return utilisateurRepository.save(utilisateur);
    }

    public ResponseEntity<Map<String, Object>> seConnecter(String email, String password) {
        // Recherche de l'utilisateur par son email
        Optional<Utilisateur> optionalUtilisateur = Optional.ofNullable(utilisateurRepository.findByEmail(email));

        if (optionalUtilisateur.isPresent()) {
            Utilisateur utilisateur = optionalUtilisateur.get();

            // Vérification du mot de passe
            if (utilisateur.getPassword().equals(password)) {
                // Connexion réussie, retour des données de l'utilisateur
                Map<String, Object> response = new HashMap<>();
                response.put("id", utilisateur.getId());
                response.put("nom", utilisateur.getNom());
                response.put("email", utilisateur.getEmail());

                // Renvoi de l'objet utilisateur avec un statut HTTP 200 OK
                return ResponseEntity.ok(response);
            } else {
                // Si le mot de passe est incorrect
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Mot de passe incorrect");
            }
        } else {
            // Si l'utilisateur n'existe pas
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Utilisateur non trouvé");
        }
    }

}
