package com.app.mediatheque.controller;

import com.app.mediatheque.model.Adherent;
import com.app.mediatheque.service.AdherentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class AdherentController {

    @Autowired
    private AdherentService adherentService;

    // GET /adherents
    @GetMapping("adherents")
    public List<Adherent> getAdherents() {
        return adherentService.getAll();
    }

    // POST /adherents
    @PostMapping("adherents")
    public void addAdherent(@RequestBody Adherent newAdherent) {
        adherentService.add(newAdherent);
    }

    // GET /adherents/1
    @GetMapping("adherents/{id}")
    public Adherent getAdherentById(@PathVariable("id") Long id) {
        return adherentService.findById(id);
    }

    // DELETE /adherents/1
    @DeleteMapping("adherents/{id}")
    public void deleteAdherent(@PathVariable("id") Long id) {
        adherentService.delete(id);
    }

    // PUT /adherents/1
    @PutMapping("adherents/{id}")
    public void updateAdherent(@RequestBody Adherent adherent, @PathVariable("id") Long id) {
        adherentService.update(id, adherent);
    }
}
