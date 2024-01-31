package com.app.mediatheque.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="emprunts")
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idAdherent;
    private Long idDocument;

    @ManyToOne
    @JoinColumn(name = "adherent_id")
    @JsonIgnore
    private Adherent adherent;

    @ManyToOne
    @JoinColumn(name = "document_id")
//    @JsonIgnore
    private Document document;

    // Autres attributs pour la date d'emprunt, de retour, etc.


    public Emprunt() {
    }

    public Emprunt(Long idAdherent, Long idDocument) {
        this.idAdherent = idAdherent;
        this.idDocument = idDocument;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAdherent() {
        return idAdherent;
    }

    public void setIdAdherent(Long idAdherent) {
        this.idAdherent = idAdherent;
    }

    public Long getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(Long idDocument) {
        this.idDocument = idDocument;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }


    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", idAdherent=" + idAdherent +
                ", idDocument=" + idDocument +
                ", adherent=" + adherent +
                ", document=" + document +
                '}';
    }
}

