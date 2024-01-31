package com.app.mediatheque.service;

import com.app.mediatheque.error.AdherentMembershipExpiredException;
import com.app.mediatheque.model.Adherent;
import com.app.mediatheque.model.Document;
import com.app.mediatheque.model.Emprunt;
import com.app.mediatheque.repository.AdherentRepository;
import com.app.mediatheque.repository.DocumentRepository;
import com.app.mediatheque.repository.EmpruntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpruntService {

    @Autowired
    private EmpruntRepository empruntRepository;

    @Autowired
    private AdherentRepository adherentRepository;  // Ajout de la déclaration

    @Autowired
    private DocumentRepository documentRepository;  // Ajout de la déclaration


//    public void add(Emprunt emprunt) {
//        empruntRepository.save(emprunt);
//    }

    public Emprunt add(Emprunt emprunt) {
        // Vérifier si l'adhérent existe
        Optional<Adherent> existingAdherent = adherentRepository.findById(emprunt.getIdAdherent());
        if (!existingAdherent.isPresent()) {
            System.out.println("L'adhérent n'existe pas");
            return null; // ou lancer une exception selon les besoins de votre application
        }
        Adherent adherent = existingAdherent.get();

        // Vérifier si l'adhérent a une adhésion périmée
        if (adherent.isAdhesionPerimee()) {
            System.out.println("L'adhérent a une adhésion périmée et ne peut pas emprunter");
            return null; // ou lancer une exception selon les besoins de votre application
//            throw new AdherentMembershipExpiredException("L'adhérent a une adhésion périmée et ne peut pas emprunter");
        }

        // Vérifier si le document existe
        Optional<Document> existingDocument = documentRepository.findById(emprunt.getIdDocument());
        if (!existingDocument.isPresent()) {
            System.out.println("Le document n'existe pas");
            return null; // ou lancer une exception selon les besoins de votre application
        }

        Document document = existingDocument.get();

        // Vérifier si le document est déjà emprunté
        if (document.isEmprunte()) {
            System.out.println("Le document est déjà emprunté");
            return null; // ou lancer une exception selon les besoins de votre application
        }

        // Vérifier si l'adhérent a déjà emprunté le maximum autorisé (3 documents dans cet exemple)
        List<Emprunt> empruntsAdherent = empruntRepository.findByAdherent(adherent);
        if (empruntsAdherent.size() >= 3) {
            System.out.println("L'adhérent a atteint le nombre maximum d'emprunts");
            return null; // ou lancer une exception selon les besoins de votre application
        }

        // Mettre à jour le statut du document à emprunté
        document.setEmprunte(true);
        documentRepository.save(document);

        // Enregistrer l'emprunt
        emprunt.setAdherent(adherent);
        emprunt.setDocument(document);
        empruntRepository.save(emprunt);

        return emprunt;
    }


    public List<Emprunt> getAll() {
        List<Emprunt> emprunts = new ArrayList<>();
        empruntRepository.findAll().forEach(emprunt -> emprunts.add(emprunt));
        return emprunts;
    }

    public Emprunt findById(Long id) {
        return empruntRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        empruntRepository.deleteById(id);
    }

    public void update(Long id, Emprunt emprunt) {
        Emprunt existingEmprunt = empruntRepository.findById(id).orElse(null);
        if (existingEmprunt != null) {
            emprunt.setId(id);
            empruntRepository.save(emprunt);
        }
    }
}
