package com.app.mediatheque.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="emprunts")
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateDebutEmprunt;
    private LocalDate dateRetourEmprunt;

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

    public Emprunt(LocalDate dateDebutEmprunt, Long idAdherent, Long idDocument) {
        this.dateDebutEmprunt = dateDebutEmprunt;
        this.idAdherent = idAdherent;
        this.idDocument = idDocument;
    }

    public LocalDate getDateDebutEmprunt() {
        return dateDebutEmprunt;
    }

    public void setDateDebutEmprunt(LocalDate dateDebutEmprunt) {
        this.dateDebutEmprunt = dateDebutEmprunt;
    }

    public LocalDate getDateRetourEmprunt() {
        return dateRetourEmprunt;
    }

    public void setDateRetourEmprunt(LocalDate dateRetourEmprunt) {
        this.dateRetourEmprunt = dateRetourEmprunt;
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
                ", dateDebutEmprunt=" + dateDebutEmprunt +
                ", dateRetourEmprunt=" + dateRetourEmprunt +
                ", idAdherent=" + idAdherent +
                ", idDocument=" + idDocument +
                ", adherent=" + adherent +
                ", document=" + document +
                '}';
    }
}

