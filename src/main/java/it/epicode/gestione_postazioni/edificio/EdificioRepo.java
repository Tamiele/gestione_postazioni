package it.epicode.gestione_postazioni.edificio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdificioRepo extends JpaRepository<Edificio, Long> {
}
