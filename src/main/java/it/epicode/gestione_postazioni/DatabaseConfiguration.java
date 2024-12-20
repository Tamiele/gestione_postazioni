package it.epicode.gestione_postazioni;

import com.github.javafaker.Faker;
import it.epicode.gestione_postazioni.edificio.Edificio;
import it.epicode.gestione_postazioni.edificio.EdificioRepo;
import it.epicode.gestione_postazioni.postazione.Postazione;
import it.epicode.gestione_postazioni.postazione.PostazioneRepo;
import it.epicode.gestione_postazioni.postazione.TipoPostazione;
import it.epicode.gestione_postazioni.utente.Utente;
import it.epicode.gestione_postazioni.utente.UtenteRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseConfiguration implements CommandLineRunner {

    private final EdificioRepo edificioRepository;
    private final PostazioneRepo postazioneRepository;
    private final UtenteRepo utenteRepository;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();

        //  Edifici

        if (edificioRepository.count() == 0 && postazioneRepository.count() == 0) {
            for (int i = 0; i < 3; i++) {
                Edificio edificio = new Edificio();
                edificio.setNome(faker.company().name());
                edificio.setIndirizzo(faker.address().streetAddress());
                edificio.setCitta(faker.address().city());
                edificioRepository.save(edificio);

                //  Postazioni
                for (int j = 0; j < 5; j++) {
                    Postazione postazione = new Postazione();
                    postazione.setCodice("POST-" + faker.number().randomNumber(5, true));
                    postazione.setDescrizione(faker.lorem().sentence());
                    postazione.setTipoPostazione(faker.options().option(TipoPostazione.class));
                    postazione.setMaxOccupanti(faker.number().numberBetween(20, 100));
                    postazione.setEdificio(edificio);
                    postazioneRepository.save(postazione);
                }
            }
        }

        //  Utenti
        if (utenteRepository.count() == 0) {
            for (int i = 0; i < 10; i++) {
                Utente utente = new Utente();
                utente.setUsername(faker.name().username());
                utente.setNomeCompleto(faker.name().fullName());
                utente.setEmail(faker.internet().emailAddress());
                utenteRepository.save(utente);
            }
        }
    }

}

