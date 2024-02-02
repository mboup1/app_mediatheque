package com.app.mediatheque.service;

import com.app.mediatheque.model.Adherent;
import com.app.mediatheque.model.Emprunt;
import com.app.mediatheque.repository.AdherentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdherentService {

    @Autowired
    private AdherentRepository adherentRepository;

    public void add(Adherent adherent) {
        adherentRepository.save(adherent);
    }

    public List<Adherent> getAll() {
        List<Adherent> adherents = new ArrayList<>();
        adherentRepository.findAll().forEach(adherent -> adherents.add(adherent));
        return adherents;
    }

    public Adherent findById(Long id) {
        return adherentRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        adherentRepository.deleteById(id);
    }

    public void update(Long id, Adherent adherent) {
        Adherent existingAdherent = adherentRepository.findById(id).orElse(null);
        if (existingAdherent != null) {
            adherent.setId(id);
            adherentRepository.save(adherent);
        }
    }

    public List<String> getEmailsForEmprunts(List<Emprunt> emprunts) {
        List<String> emails = new ArrayList<>();
        for (Emprunt emprunt : emprunts) {
            Adherent adherent = emprunt.getAdherent();
            if (adherent != null) {
                emails.add(adherent.getEmail());
            }
        }
        return emails;
    }
}
