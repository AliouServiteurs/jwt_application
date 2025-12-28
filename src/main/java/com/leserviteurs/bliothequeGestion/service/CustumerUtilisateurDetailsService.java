package com.leserviteurs.bliothequeGestion.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.leserviteurs.bliothequeGestion.model.Utilisateur;
import com.leserviteurs.bliothequeGestion.repository.UtilisateursRepository;

@Service
public class CustumerUtilisateurDetailsService implements UserDetailsService {
    private final UtilisateursRepository utilisateursRepository;

    public CustumerUtilisateurDetailsService(UtilisateursRepository utilisateursRepository) {
        this.utilisateursRepository = utilisateursRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateursRepository.findByNom(username);
        if (utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé avec le nom: " + username);
        }

        return new User(utilisateur.getNom(), utilisateur.getMotDePasse(),
                Collections.singletonList(new SimpleGrantedAuthority(utilisateur.getRole())));
    }

}
