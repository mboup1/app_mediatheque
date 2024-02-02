package com.app.mediatheque.controller;

import com.app.mediatheque.model.Emprunt;
import com.app.mediatheque.repository.EmpruntRepository;
import com.app.mediatheque.service.EmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class EmpruntController {

    @Autowired
    private EmpruntService empruntService;

    @Autowired
    private EmpruntRepository empruntRepository;

    // GET /emprunts
    @GetMapping("emprunts")
    public List<Emprunt> getEmprunts() {
        return empruntService.getAllDocumentsEmprunts();
    }

    // POST /emprunts
    @PostMapping("emprunts")
    public ResponseEntity<String> addEmprunt(@RequestBody Emprunt newEmprunt) {
        Emprunt createdEmprunt = empruntService.add(newEmprunt);
        if (createdEmprunt != null) {
            return new ResponseEntity<>("Emprunt ajouté avec succès", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Impossible d'ajouter l'emprunt", HttpStatus.BAD_REQUEST);
        }
    }

    // GET /emprunts/1
    @GetMapping("emprunts/{id}")
    public ResponseEntity<Emprunt> getEmpruntById(@PathVariable("id") Long id) {
        Emprunt emprunt = empruntService.findById(id);
        if (emprunt != null) {
            return new ResponseEntity<>(emprunt, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE /emprunts/1
    @DeleteMapping("emprunts/{id}")
    public ResponseEntity<String> deleteEmprunt(@PathVariable("id") Long id) {
        empruntService.delete(id);
        return new ResponseEntity<>("Emprunt supprimé avec succès", HttpStatus.OK);
    }

    // PUT /emprunts/1
    @PutMapping("emprunts/{id}")
    public ResponseEntity<String> updateEmprunt(@RequestBody Emprunt emprunt, @PathVariable("id") Long id) {
        empruntService.update(id, emprunt);
        return new ResponseEntity<>("Emprunt mis à jour avec succès", HttpStatus.OK);
    }

    // PUT /emprunts/rendre/1
    @PutMapping("emprunts/rendre/{id}")
    public boolean rendreDocument(Long empruntId) {
        Emprunt emprunt = empruntRepository.findById(empruntId).orElse(null);

        if (emprunt != null) {
            // Logique pour effectuer le rendu du document, par exemple :
            emprunt.getDocument().setEmprunte(false);
            empruntRepository.deleteById(empruntId);
            return true; // Rendu réussi
        } else {
            return false; // Emprunt introuvable
        }
    }

    // GET /emprunts/enretard
    @GetMapping("emprunts/enretard")
    public ResponseEntity<List<Emprunt>> getEmpruntsEnRetard() {
        List<Emprunt> empruntsEnRetard = empruntService.getEmpruntsEnRetard();
        if (empruntsEnRetard != null && !empruntsEnRetard.isEmpty()) {
            return new ResponseEntity<>(empruntsEnRetard, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    // GET /emprunts/enretard/emails
    @GetMapping("emprunts/enretard/emails")
    public ResponseEntity<List<String>> getEmailsAdherentsEnRetard() {
        List<String> emailsAdherentsEnRetard = empruntService.getEmailsAdherentsEnRetard();
        if (emailsAdherentsEnRetard != null && !emailsAdherentsEnRetard.isEmpty()) {
            return new ResponseEntity<>(emailsAdherentsEnRetard, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


}
