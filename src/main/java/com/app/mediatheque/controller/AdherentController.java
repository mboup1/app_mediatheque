package com.app.mediatheque.controller;

import com.app.mediatheque.model.Adherent;
import com.app.mediatheque.service.AdherentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/adherents")
public class AdherentController {

    @Autowired
    private AdherentService adherentService;

    @GetMapping
    public ResponseEntity<List<Adherent>> getAdherents() {
        List<Adherent> adherents = adherentService.getAll();
        return new ResponseEntity<>(adherents, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addAdherent(@RequestBody Adherent newAdherent) {
        adherentService.add(newAdherent);
        return new ResponseEntity<>("Adhérent ajouté avec succès", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adherent> getAdherentById(@PathVariable("id") Long id) {
        Adherent adherent = adherentService.findById(id);
        if (adherent != null) {
            return new ResponseEntity<>(adherent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdherent(@PathVariable("id") Long id) {
        adherentService.delete(id);
        return new ResponseEntity<>("Adhérent supprimé avec succès", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAdherent(@RequestBody Adherent adherent, @PathVariable("id") Long id) {
        adherentService.update(id, adherent);
        return new ResponseEntity<>("Adhérent mis à jour avec succès", HttpStatus.OK);
    }
}
