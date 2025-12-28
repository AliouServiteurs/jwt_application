package com.leserviteurs.bliothequeGestion.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.leserviteurs.bliothequeGestion.configuration.JwtUtil;
import com.leserviteurs.bliothequeGestion.model.Utilisateur;
import com.leserviteurs.bliothequeGestion.repository.UtilisateursRepository;

@RestController
@RequestMapping("/api/auth")
public class UtilisateurLoginRegisterController {
    private final UtilisateursRepository utilisateursRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UtilisateurLoginRegisterController(UtilisateursRepository utilisateursRepository,
            PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.utilisateursRepository = utilisateursRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Utilisateur utilisateur) {
        if (utilisateursRepository.findByNom(utilisateur.getNom()) != null) {
            return ResponseEntity.status(Response.SC_CONFLICT).body("Nom d'utilisateur déjà pris");
        }
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        utilisateursRepository.save(utilisateur);
        return ResponseEntity.status(Response.SC_CREATED).body(utilisateur);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Utilisateur utilisateur) {
        // La gestion de l'authentification est prise en charge par Spring Security
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(utilisateur.getNom(), utilisateur.getMotDePasse()));

            // Si l'authentification est réussie, générer un token JWT et le retourner
            if (authentication.isAuthenticated()) {
                Map<String, Object> authData = new HashMap<>();
                authData.put("token", jwtUtil.generateToken(utilisateur.getNom()));
                authData.put("type", "Bearer");
                return ResponseEntity.ok(authData);
            }
            return ResponseEntity.status(Response.SC_UNAUTHORIZED).body("Nom d'utilisateur ou mot de passe incorrect");

        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_UNAUTHORIZED).body("Nom d'utilisateur ou mot de passe incorrect");
        }
    }

}
