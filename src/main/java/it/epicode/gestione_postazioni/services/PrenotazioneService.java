package it.epicode.gestione_postazioni.services;

import it.epicode.gestione_postazioni.postazione.Postazione;
import it.epicode.gestione_postazioni.prenotazione.Prenotazione;
import it.epicode.gestione_postazioni.prenotazione.PrenotazioneRepo;
import it.epicode.gestione_postazioni.utente.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrenotazioneService {

    private final PrenotazioneRepo prenotazioneRepository;



    public Prenotazione prenota(Utente utente, Postazione postazione, LocalDate data) {

        boolean alreadyBooked = prenotazioneRepository.existsByPostazioneAndDataPrenotazione(postazione, data);
        if (alreadyBooked) {
            throw new RuntimeException("Postazione non disponibile per questa data.");
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setUtente(utente);
        prenotazione.setPostazione(postazione);
        prenotazione.setDataPrenotazione(data);
        prenotazione.setDataScadenzaPrenotazione(data.plusDays(1));

        return prenotazioneRepository.save(prenotazione);
    }


}
