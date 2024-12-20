package it.epicode.gestione_postazioni.prenotazione;

import it.epicode.gestione_postazioni.postazione.Postazione;
import it.epicode.gestione_postazioni.utente.Utente;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "prenotazioni")
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataPrenotazione;

    @Column(nullable = false)
    private LocalDate dataScadenzaPrenotazione;


    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "postazione_id")
    private Postazione postazione;


}