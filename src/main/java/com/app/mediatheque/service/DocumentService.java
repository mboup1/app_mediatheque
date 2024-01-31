package com.app.mediatheque.service;

import com.app.mediatheque.model.Document;
import com.app.mediatheque.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public void add(Document document) {
        documentRepository.save(document);
    }

    public List<Document> getAll() {
        List<Document> documents = new ArrayList<>();
        documentRepository.findAll().forEach(document -> documents.add(document));
        return documents;
    }

    public Document findById(Long id) {
        return documentRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        documentRepository.deleteById(id);
    }

    public void update(Long id, Document document) {
        Document existingDocument = documentRepository.findById(id).orElse(null);
        if (existingDocument != null) {
            document.setId(id);
            documentRepository.save(document);
        }
    }
}
