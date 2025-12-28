package com.leserviteurs.bliothequeGestion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.leserviteurs.bliothequeGestion.model.Personne;
import com.leserviteurs.bliothequeGestion.repository.PersonneRepository;

@Service
public class PersonneImplement implements PersonneInterface {

    private final PersonneRepository personneRepository;

    public PersonneImplement(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    @Override
    public void ajouterPersonne(Personne personne) {
        personneRepository.save(personne);
    }

    @Override
    public void supprimerPersonne(Long id) {
        Optional<Personne> exitPersonne = personneRepository.findById(id);
        if (exitPersonne.isPresent()) {
            personneRepository.deleteById(id);
        } else {
            throw new RuntimeException("Impossible de supprimer, personne non trouvée avec l'id: ");
        }
    }

    @Override
    public void mettreAJourPersonne(Long id, Personne personne) {
        // Personne exiPersonne = personneRepository.findById(id).orElseThrow(() -> new
        // RuntimeException("Personne non trouvée"));
        // exiPersonne.setNom(personne.getNom());
        // exiPersonne.setPrenom(personne.getPrenom());
        // exiPersonne.setEmail(personne.getEmail());
        // personneRepository.save(exiPersonne);

        Optional<Personne> existingPersonne = personneRepository.findById(id);
        if (existingPersonne.isPresent()) {
            Personne exiPersonne = existingPersonne.get();
            exiPersonne.setNom(personne.getNom());
            exiPersonne.setPrenom(personne.getPrenom());
            exiPersonne.setEmail(personne.getEmail());
            personneRepository.save(exiPersonne);
        } else {
            throw new RuntimeException("La personne avec l'id " + id + " n'existe pas !");
        }
    }

    @Override
    public Personne getPersonneById(Long id) {
        Optional<Personne> personne = personneRepository.findById(id);
        if (personne.isPresent()) {
            return personne.get();
        } else {
            throw new RuntimeException("La personne avec l'id " + id + " n'existe pas !");
        }
    }

    @Override
    public List<Personne> getAllPersonnes() {
        return personneRepository.findAll();
    }

}
