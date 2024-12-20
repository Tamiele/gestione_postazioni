package it.epicode.gestione_postazioni.utente;

import it.epicode.gestione_postazioni.prenotazione.Prenotazione;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String nomeCompleto;


    @Column(nullable = false)
    private String email;





}