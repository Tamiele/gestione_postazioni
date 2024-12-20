package it.epicode.gestione_postazioni.prenotazione;

import it.epicode.gestione_postazioni.postazione.Postazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PrenotazioneRepo extends JpaRepository<Prenotazione, Long> {
    boolean existsByPostazioneAndDataPrenotazione(Postazione postazione, LocalDate dataPrenotazione);
}
