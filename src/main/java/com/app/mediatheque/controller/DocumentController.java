package com.app.mediatheque.controller;

import com.app.mediatheque.model.Document;
import com.app.mediatheque.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    // GET /documents
    @GetMapping("documents")
    public List<Document> getDocuments() {
        return documentService.getAll();
    }

    // POST /documents
    @PostMapping("documents")
    public void addDocument(@RequestBody Document newDocument) {
        documentService.add(newDocument);
    }

    // GET /documents/1
    @GetMapping("documents/{id}")
    public Document getDocumentById(@PathVariable("id") Long id) {
        return documentService.findById(id);
    }

    // DELETE /documents/1
    @DeleteMapping("documents/{id}")
    public void deleteDocument(@PathVariable("id") Long id) {
        documentService.delete(id);
    }

    // PUT /documents/1
    @PutMapping("documents/{id}")
    public void updateDocument(@RequestBody Document document, @PathVariable("id") Long id) {
        documentService.update(id, document);
    }
}
