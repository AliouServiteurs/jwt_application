package com.leserviteurs.bliothequeGestion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leserviteurs.bliothequeGestion.model.Personne;
import com.leserviteurs.bliothequeGestion.service.PersonneImplement;


@RestController
@RequestMapping("/api/personnes")
public class PersonneController {

    private final PersonneImplement personneImpl;

    public PersonneController(PersonneImplement personneImpl) {
        this.personneImpl = personneImpl;
    }

    @GetMapping("/trouver/{id}")
    public ResponseEntity<Personne> getPersonneById(@PathVariable Long id) {
        try {
            Personne personne = personneImpl.getPersonneById(id);
            return new ResponseEntity<>(personne, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/ajouter")
    public ResponseEntity<Personne> ajouterPersonne(@RequestBody Personne personne) {
        personneImpl.ajouterPersonne(personne);
        return new ResponseEntity<>(personne, HttpStatus.CREATED);
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Personne> supprimerPersonne(@PathVariable Long id) {
        try {
            personneImpl.getPersonneById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/mettreAJour/{id}")
    public ResponseEntity<Personne> mettreAJourPersonne(@PathVariable Long id, @RequestBody Personne personne) {
        try {
            personneImpl.mettreAJourPersonne(id, personne);
            return new ResponseEntity<>(personne, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/tous")
    public List<Personne> getAllPersonnes() {
        return personneImpl.getAllPersonnes();
    }
}
