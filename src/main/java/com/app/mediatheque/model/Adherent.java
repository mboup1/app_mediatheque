package com.app.mediatheque.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="adherents")
public class Adherent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private boolean adhesionPerimee;

    @OneToMany(mappedBy = "adherent")
//    @JsonIgnore
    private List<Emprunt> emprunts;

    public Adherent() {
    }

    public Adherent(String nom, String prenom, String email, boolean adhesionPerimee) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adhesionPerimee = adhesionPerimee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdhesionPerimee() {
        return adhesionPerimee;
    }

    public void setAdhesionPerimee(boolean adhesionPerimee) {
        this.adhesionPerimee = adhesionPerimee;
    }

    public List<Emprunt> getEmprunts() {
        return emprunts;
    }

    public void setEmprunts(List<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }

    @Override
    public String toString() {
        return "Adherent{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", adhesionPerimee=" + adhesionPerimee +
                ", emprunts=" + emprunts +
                '}';
    }
}

