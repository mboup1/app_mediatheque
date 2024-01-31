package com.app.mediatheque.repository;

import com.app.mediatheque.model.Adherent;
import com.app.mediatheque.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
