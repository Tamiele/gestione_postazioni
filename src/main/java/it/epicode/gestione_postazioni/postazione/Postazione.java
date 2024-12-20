package it.epicode.gestione_postazioni.postazione;

import it.epicode.gestione_postazioni.edificio.Edificio;

import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(name = "postazioni")
public class Postazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String codice;

    private String descrizione;

    @Column(name = "tipo_postazione")
    @Enumerated(EnumType.STRING)
    private TipoPostazione tipoPostazione;

    @Column(name = "max_occupanti")
    int maxOccupanti;

    @Column(name = "è_libera", nullable = false)
    private boolean isLibera;

    @ManyToOne
    @JoinColumn(name = "edificio_id")
    private Edificio edificio;


}