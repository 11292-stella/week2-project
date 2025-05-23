import Dao.ArchivioDao;
import Dao.PrestitoDao;
import Dao.UtenteDao;
import Entities.*;
import Enumerated.Periodicita;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AppMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        EntityManager em = emf.createEntityManager();

        ArchivioDao archivioDao = new ArchivioDao(em);
        UtenteDao utenteDao = new UtenteDao(em);
        PrestitoDao prestitoDao = new PrestitoDao(em);

        try{
            Utente utente = new Utente("Mario", "Bros", LocalDate.of(1995, 6, 12));
            Utente utente1 = new Utente("Yoshy", "Yosh", LocalDate.of(1999, 3, 11));
            Utente utente2 = new Utente("Toad", "Mushroom", LocalDate.of(2000, 8, 1));
            Utente utente3 = new Utente("Peach", "Princess", LocalDate.of(2001, 5, 10));
            utenteDao.salva(utente);
            utenteDao.salva(utente1);
            utenteDao.salva(utente2);
            utenteDao.salva(utente3);

            Libro libro = new Libro("LIB124","Harry Potter",2019,400,"J. K. Rowling","Fantasy");
            Libro libro1 = new Libro("1345","Il Signore degli Anelli",1955,588,"J. R. R. Tolkien","Fantasy");
            Libro libro2 = new Libro("LIB122","Dracula",1922,450,"Bram Stoker","Horror");
            Libro libro3 = new Libro("5463","Doctor Sleep",2014,600," Stephen King","Horror");
            Libro libro4 = new Libro("LIB123","IT", 1986,532,"Stephen King","Horror");
            libro.setUtente(utente);
            libro1.setUtente(utente1);
            libro2.setUtente(utente2);
            libro3.setUtente(utente3);
            libro4.setUtente(utente);
            archivioDao.aggiungiElemento(libro);
            archivioDao.aggiungiElemento(libro1);
            archivioDao.aggiungiElemento(libro2);
            archivioDao.aggiungiElemento(libro3);
            archivioDao.aggiungiElemento(libro4);

            Rivista rivista1 = new Rivista("RIV456","Nightmare magazine",2025,20,Periodicita.MENSILE);
            Rivista rivista2 = new Rivista("7654","FantasyMagazine",2016,18,Periodicita.SEMESTRALE);
            Rivista rivista3 = new Rivista("RIV458","La Settimana Enigmistica",2025,10,Periodicita.SETTIMANALE);

            rivista1.setUtente(utente1);
            rivista2.setUtente(utente2);
            rivista3.setUtente(utente3);

            archivioDao.aggiungiElemento(rivista1);
            archivioDao.aggiungiElemento(rivista2);
            archivioDao.aggiungiElemento(rivista3);

            ElementoCatalogo trovatoISBN = archivioDao.cercaPerISBN("LIB123");
            System.out.println("Trovato per ISBN: " + trovatoISBN.getTitolo());

            List<ElementoCatalogo> trovatiAnno = archivioDao.cercaPerAnno(2023);
            System.out.println("Trovati per anno 2023:");
            trovatiAnno.forEach(e -> System.out.println(" - " + e.getTitolo()));

            List<Libro> trovatiAutore = archivioDao.ricercaPerAutore("Stephen King");
            System.out.println("Libri trovati per autore:");
            trovatiAutore.forEach(l -> System.out.println(" - " + l.getTitolo()));

            Prestito prestito = new Prestito(utente, libro, LocalDate.now(), LocalDate.now().plusDays(30), null);
            prestitoDao.salva(prestito);

            List<Prestito> prestitiAttivi = prestitoDao.trovaPrestitiAttivi();
            System.out.println("Prestiti attivi:");
            prestitiAttivi.forEach(p ->
                    System.out.println(" - " + p.getUtente().getNome() +
                            " ha in prestito: " + p.getElementoCatalogo().getTitolo())
            );

            archivioDao.rimuoviElemento("RIV456");
            System.out.println("Rivista RIV456 rimossa.");

        } catch(Exception e){
            System.out.println("errore durante l'operazione:");
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        boolean continua = true;

        while(continua){

            System.out.println("--- MENU ---");
            System.out.println("1. Cerca per ISBN");
            System.out.println("2. Cerca per anno");
            System.out.println("3. Cerca per autore");
            System.out.println("4. Elenca prestiti attivi");
            System.out.println("6. Aggiungi libro/rivista");
            System.out.println("7. Aggiungi libro/rivista");
            System.out.println("5. Esci");
            System.out.print("Scelta: ");
            String scelta = scanner.nextLine();

            switch (scelta){
                case "1":
                    System.out.print("Inserisci ISBN: ");
                    String isbn = scanner.nextLine();
                    ElementoCatalogo trovato = archivioDao.cercaPerISBN(isbn);
                    if (trovato != null) {
                        System.out.println("Titolo: " + trovato.getTitolo());

                    }else{
                        System.out.println("Nessun elemento trovato con ISBN " + isbn);
                    }
                    break;

                case "2":
                    System.out.print("Inserisci anno di pubblicazione: ");
                    int anno = Integer.parseInt(scanner.nextLine());
                    List<ElementoCatalogo> perAnno=archivioDao.cercaPerAnno(anno);
                    perAnno.forEach(e-> System.out.println(" " +e.getTitolo()));
                    break;

                case "3":
                    System.out.print("Inserisci autore: ");
                    String autore = scanner.nextLine();
                    List<Libro> libriAutore = archivioDao.ricercaPerAutore(autore);
                    libriAutore.forEach(l -> System.out.println("  " + l.getTitolo()));
                    break
                            ;
                case "4":
                    List<Prestito> prestiti = prestitoDao.trovaPrestitiAttivi();
                    prestiti.forEach(p -> System.out.println("  " + p.getUtente().getNome() +
                            " ha in prestito: " + p.getElementoCatalogo().getTitolo()));
                    break;

                case "5":
                    continua = false;
                    System.out.println("Uscita dal programma.");
                    break;

                case "6":
                    System.out.println("Vuoi aggiungere un libro o una rivista? (libro/rivista)");
                    String tipo = scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn1 = scanner.nextLine();

                    System.out.print("Titolo: ");
                    String titolo = scanner.nextLine();

                    System.out.print("Anno pubblicazione: ");
                    int anno1 = Integer.parseInt(scanner.nextLine());

                    System.out.print("Numero pagine: ");
                    int pagine = Integer.parseInt(scanner.nextLine());

                    if (tipo.equals("libro")) {
                        System.out.print("Autore: ");
                        String autore1 = scanner.nextLine();

                        System.out.print("Genere: ");
                        String genere = scanner.nextLine();

                        Libro nuovoLibro = new Libro(isbn1, titolo, anno1, pagine, autore1, genere);
                        archivioDao.aggiungiElemento(nuovoLibro);
                        System.out.println("Libro aggiunto con successo!");

                    } else if (tipo.equals("rivista")) {
                        System.out.println("Periodicità (SETTIMANALE, MENSILE, SEMESTRALE): ");
                        String periodicitaInput = scanner.nextLine().toUpperCase();

                        try {
                            Periodicita periodicita = Periodicita.valueOf(periodicitaInput);
                            Rivista nuovaRivista = new Rivista(isbn1, titolo, anno1, pagine, periodicita);
                            archivioDao.aggiungiElemento(nuovaRivista);
                            System.out.println("Rivista aggiunta con successo!");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Periodicità non valida.");
                        }

                    } else {
                        System.out.println("Tipo non riconosciuto. Inserisci 'libro' o 'rivista'.");
                    }
                    break;

                case "7":
                    System.out.print("Inserisci l'ISBN dell'elemento da rimuovere: ");
                    String isbnDaRimuovere = scanner.nextLine();
                    archivioDao.rimuoviElemento(isbnDaRimuovere);
                    System.out.println("Elemento con ISBN " + isbnDaRimuovere + " rimosso, se presente.");
                    break;



                default:
                    System.out.println("Scelta non valida.");









            }


        }
    }

}
