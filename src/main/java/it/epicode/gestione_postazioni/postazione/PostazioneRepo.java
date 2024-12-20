package it.epicode.gestione_postazioni.postazione;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostazioneRepo extends JpaRepository<Postazione, Long> {
    @Query("SELECT p FROM Postazione p JOIN p.edificio e WHERE p.tipoPostazione = :tipo AND e.citta = :citta")
    List<Postazione> findByTipoPostazioneAndCitta(@Param("tipo") TipoPostazione tipo, @Param("citta") String citta);
}
