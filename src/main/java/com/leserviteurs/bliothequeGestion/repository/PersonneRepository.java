package com.leserviteurs.bliothequeGestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leserviteurs.bliothequeGestion.model.Personne;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {

}
