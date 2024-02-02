package com.app.mediatheque.repository;

import com.app.mediatheque.model.Adherent;
import com.app.mediatheque.model.Document;
import com.app.mediatheque.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    List<Emprunt> findByAdherent(Adherent adherent);
    // Ajoutez cette méthode pour récupérer les emprunts en retard
    List<Emprunt> findByDateDebutEmpruntBeforeAndDateRetourEmprunt(LocalDate dateRetourPrevue, LocalDate dateDebutEmprunt);

}
