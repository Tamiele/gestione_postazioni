package it.epicode.gestione_postazioni;

import it.epicode.gestione_postazioni.postazione.Postazione;
import it.epicode.gestione_postazioni.postazione.PostazioneRepo;
import it.epicode.gestione_postazioni.postazione.TipoPostazione;
import it.epicode.gestione_postazioni.prenotazione.Prenotazione;
import it.epicode.gestione_postazioni.services.PrenotazioneService;
import it.epicode.gestione_postazioni.utente.Utente;
import it.epicode.gestione_postazioni.utente.UtenteRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class MenuApplication implements CommandLineRunner {

    private final PrenotazioneService prenotazioneService;
    private final PostazioneRepo postazioneRepository;
    private final UtenteRepo utenteRepository;

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Benvenuto nel sistema di gestione prenotazioni.");

        while (true) {
            System.out.println("\n1. Visualizza Postazioni\n2. Effettua Prenotazione\n3. Ricerca Postazioni\n4. Esci");
            System.out.print("Scegli un'opzione: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    List<Postazione> postazioni = postazioneRepository.findAll();
                    postazioni.forEach(p -> {
                        System.out.println("Codice: " + p.getCodice() + ", Tipo: " + p.getTipoPostazione() + ", Max Occupanti: " + p.getMaxOccupanti() + ", Descrizione: " + p.getDescrizione());
                    });
                    break;
                case 2:
                    System.out.print("Inserisci ID utente: ");
                    Long utenteId = scanner.nextLong();
                    Utente utente = utenteRepository.findById(utenteId).orElseThrow(() -> new RuntimeException("Utente non trovato."));

                    System.out.print("Inserisci ID postazione: ");
                    Long postazioneId = scanner.nextLong();
                    Postazione postazione = postazioneRepository.findById(postazioneId).orElseThrow(() -> new RuntimeException("Postazione non trovata."));

                    System.out.print("Inserisci data (yyyy-MM-dd): ");
                    LocalDate data = LocalDate.parse(scanner.next());

                    try {
                        Prenotazione prenotazione = prenotazioneService.prenota(utente, postazione, data);
                        System.out.println("Prenotazione effettuata con successo.");
                        System.out.println("Dettagli prenotazione:");

                        System.out.println("Data prenotazione: " + prenotazione.getDataPrenotazione());
                        System.out.println("Data scadenza: " + prenotazione.getDataScadenzaPrenotazione());
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Inserisci tipo di postazione (PRIVATO, OPEN_SPACE, SALA_RIUNIONI): ");
                    String tipoInput = scanner.next().toUpperCase();
                    TipoPostazione tipoPostazione;
                    try {
                        tipoPostazione = TipoPostazione.valueOf(tipoInput);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Tipo di postazione non valido.");
                        break;
                    }

                    scanner.nextLine();
                    System.out.print("Inserisci città: ");
                    String citta = scanner.nextLine();

                    List<Postazione> risultati = postazioneRepository.findByTipoPostazioneAndCitta(tipoPostazione, citta);
                    if (risultati.isEmpty()) {
                        System.out.println("Nessuna postazione trovata per i criteri specificati.");
                    } else {
                        risultati.forEach(p -> {
                            System.out.println("Codice: " + p.getCodice() + ", Tipo: " + p.getTipoPostazione() + ", Max Occupanti: " + p.getMaxOccupanti() + ", Descrizione: " + p.getDescrizione());
                        });
                    }
                    break;
                case 4:
                    System.out.println("Uscita in corso...");
                    return;
                default:
                    System.out.println("Opzione non valida.");
            }
        }
    }


}

