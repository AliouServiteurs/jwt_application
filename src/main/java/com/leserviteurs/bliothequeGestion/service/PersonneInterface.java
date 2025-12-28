package com.leserviteurs.bliothequeGestion.service;

import java.util.List;

import com.leserviteurs.bliothequeGestion.model.Personne;

public interface PersonneInterface {
    public void ajouterPersonne(Personne personne);
    public void supprimerPersonne(Long id);
    public void mettreAJourPersonne(Long id, Personne personne);
    public Personne getPersonneById(Long id);
    public List<Personne> getAllPersonnes();
    
}
