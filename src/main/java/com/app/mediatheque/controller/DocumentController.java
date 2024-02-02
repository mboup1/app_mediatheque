package com.app.mediatheque.controller;

import com.app.mediatheque.model.Document;
import com.app.mediatheque.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping
    public ResponseEntity<List<Document>> getDocuments() {
        List<Document> documents = documentService.getAll();
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addDocument(@RequestBody Document newDocument) {
        documentService.add(newDocument);
        return new ResponseEntity<>("Document ajouté avec succès", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocumentById(@PathVariable("id") Long id) {
        Document document = documentService.findById(id);
        if (document != null) {
            return new ResponseEntity<>(document, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDocument(@PathVariable("id") Long id) {
        documentService.delete(id);
        return new ResponseEntity<>("Document supprimé avec succès", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDocument(@RequestBody Document document, @PathVariable("id") Long id) {
        documentService.update(id, document);
        return new ResponseEntity<>("Document mis à jour avec succès", HttpStatus.OK);
    }
}
