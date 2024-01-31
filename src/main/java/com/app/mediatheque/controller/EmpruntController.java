package com.app.mediatheque.controller;

import com.app.mediatheque.model.Emprunt;
import com.app.mediatheque.service.EmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class EmpruntController {

    @Autowired
    private EmpruntService empruntService;

    // GET /emprunts
    @GetMapping("emprunts")
    public List<Emprunt> getEmprunts() {
        return empruntService.getAll();
    }

    // POST /emprunts
    @PostMapping("emprunts")
    public void addEmprunt(@RequestBody Emprunt newEmprunt) {
        empruntService.add(newEmprunt);
    }

    // GET /emprunts/1
    @GetMapping("emprunts/{id}")
    public Emprunt getEmpruntById(@PathVariable("id") Long id) {
        return empruntService.findById(id);
    }

    // DELETE /emprunts/1
    @DeleteMapping("emprunts/{id}")
    public void deleteEmprunt(@PathVariable("id") Long id) {
        empruntService.delete(id);
    }

    // PUT /emprunts/1
    @PutMapping("emprunts/{id}")
    public void updateEmprunt(@RequestBody Emprunt emprunt, @PathVariable("id") Long id) {
        empruntService.update(id, emprunt);
    }
}
