package com.leserviteurs.bliothequeGestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leserviteurs.bliothequeGestion.model.Utilisateur;

@Repository
public interface UtilisateursRepository extends JpaRepository<Utilisateur, Long> {
    // Utilisateur findByEmail(String email);
    Utilisateur findByNom(String nom);
}
