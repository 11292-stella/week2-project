package Progetto;

import java.util.List;
import java.util.Scanner;

public class AppMain {


    public static void main(String[] args) {


        Libro libro1 = new Libro("1234","Harry Potter",2019,400,"J. K. Rowling","Fantasy");
        Libro libro2 = new Libro("1345","Il Signore degli Anelli",1955,588,"J. R. R. Tolkien","Fantasy");
        Libro libro3 = new Libro("4354","Dracula",1922,450,"Bram Stoker","Horror");
        Libro libro4 = new Libro("5463","Doctor Sleep",2014,600," Stephen King","Horror");
        Libro libro5 = new Libro("6543","IT", 1986,532,"Stephen King","Horror");

        List<Libro> libri = List.of(libro1,libro2,libro3,libro4,libro5);

        Rivista rivista1 = new Rivista("5463","Nightmare magazine",2025,20,Periodicita.MENSILE);
        Rivista rivista2 = new Rivista("7654","FantasyMagazine",2016,18,Periodicita.SEMESTRALE);
        Rivista rivista3 = new Rivista("8790","La Settimana Enigmistica",2025,10,Periodicita.SETTIMANALE);

        List<Rivista> riviste = List.of(rivista1,rivista2,rivista3);

        Archivio archivio = new Archivio();

        libri.forEach(archivio::aggiungiElemento);
        riviste.forEach(archivio::aggiungiElemento);

        Scanner scanner = new Scanner(System.in);
        boolean continua = true;

        while(continua){
            System.out.println("-----Menu-----");
            System.out.println("1. vuoi aggiungere un libro?");
            System.out.println("2. vuoi aggiungere una rivista?");
            System.out.println("3. Ricerca elemento per ISBN");
            System.out.println("4. Rimozione elemento per ISBN");
            System.out.println("5. Ricerca per anno di pubblicazione");
            System.out.println("6. Ricerca per autore");
            System.out.println("7. Aggiorna un libro dato l'ISBN");
            System.out.println("8. Aggiorna una rivista dato l'ISBN");
            System.out.println("9. Stampa statistiche");
            System.out.print("Scelta: ");

            int scelta = Integer.parseInt(scanner.nextLine());

            switch (scelta){

                case 1:
                    System.out.println("vuoi aggiungere un libro?");
                    System.out.println("ISBN: ");
                    String isbnLibro = scanner.nextLine();
                    System.out.print("Titolo: ");
                    String titoloLibro = scanner.nextLine();

                    System.out.print("Anno di pubblicazione: ");
                    int annoLibro = Integer.parseInt(scanner.nextLine());

                    System.out.print("Numero di pagine: ");
                    int pagineLibro = Integer.parseInt(scanner.nextLine());

                    System.out.print("Autore: ");
                    String autore = scanner.nextLine();

                    System.out.print("Genere: ");
                    String genere = scanner.nextLine();

                    Libro nuovoLibro = new Libro(isbnLibro,titoloLibro,annoLibro,pagineLibro,autore,genere);
                    archivio.aggiungiElemento(nuovoLibro);
                    archivio.stampaCatalogo();
                    break;

                case 2:
                    System.out.println("Aggiunta di una rivista:");

                    System.out.print("ISBN: ");
                    String isbnRivista = scanner.nextLine();

                    System.out.print("Titolo: ");
                    String titoloRivista = scanner.nextLine();

                    System.out.print("Anno di pubblicazione: ");
                    int annoRivista = Integer.parseInt(scanner.nextLine());

                    System.out.print("Numero di pagine: ");
                    int pagineRivista = Integer.parseInt(scanner.nextLine());

                    System.out.print("Periodicità (SETTIMANALE, MENSILE, SEMESTRALE): ");
                    String periodicitaStr = scanner.nextLine();
                    Periodicita periodicita = Periodicita.valueOf(periodicitaStr);

                    Rivista nuovaRivista = new Rivista(isbnRivista,titoloRivista,annoRivista,pagineRivista,periodicita);
                    archivio.aggiungiElemento(nuovaRivista);
                    archivio.stampaCatalogo();
                    break;

                case 3:
                    System.out.println("Ricerca elemento per ISBN:");
                    System.out.println("Inserisci l'ISBN: ");
                    String isbnDaCercare = scanner.nextLine();

                    try{
                        ElementoCatalogo trovato = archivio.ricercaPerISBN(isbnDaCercare);
                        System.out.println("Elemento trovato");
                        System.out.println("Titolo: " + trovato.getTitolo());
                        System.out.println("Anno: " + trovato.getAnnoPubblicazione());
                        System.out.println("Numero pagine: " + trovato.getNumeroPagine());

                    } catch (ISBNNotFoundException e){
                        System.out.println("Errore: " + e.getMessage());
                    }


                    break;
                case 4:
                    System.out.println("Rimozione elemento per ISBN:");
                    System.out.println("Inserisci l'ISBN dell'elemento da rimuovere: ");
                    String isbnDaRimuovere = scanner.nextLine();

                    boolean rimosso = archivio.rimuoviPerISBN(isbnDaRimuovere);
                    if (rimosso) {
                        System.out.println("Elemento rimosso con successo.");
                    } else {
                        System.out.println("Nessun elemento trovato con questo ISBN.");
                    }

                    archivio.stampaCatalogo();
                    break;

                case 5:
                    System.out.println("Ricerca per anno di pubblicazione");
                    System.out.println("Inserisci l'anno di pubblicazione");
                    int anno = scanner.nextInt();
                    scanner.nextLine();

                    List<ElementoCatalogo> trovati = archivio.ricercaPerAnno(anno);
                    if (trovati.isEmpty()) {
                        System.out.println("Nessun elemento trovato per l'anno " + anno);
                    } else {
                        System.out.println("Elementi trovati:");
                        trovati.forEach(e -> System.out.println(e.getTitolo() + " - ISBN: " + e.getISBN()));
                    }


                    break;

                case 6:
                    System.out.println("Ricerca per autore");
                    System.out.println("Inserisci autore");
                    String nomeAutore = scanner.nextLine();

                    List<Libro> autoreTrovato = archivio.ricercaPerAutore(nomeAutore);
                    if (autoreTrovato.isEmpty()) {
                        System.out.println("Nessun elemento trovato per l'autore: " + nomeAutore);
                    } else {
                        System.out.println("Elementi trovati:");
                        autoreTrovato.forEach(e -> System.out.println(e.getTitolo() + " - ISBN: " + e.getISBN()));
                    }
                    archivio.stampaCatalogo();
                    break;

                case 7:
                    System.out.println("Aggiornamento di un elemento esistente dato l'ISBN:");
                    System.out.println("Inserisci l'ISBN del libro da aggiornare:");
                    scanner.nextLine();
                    String isbnAggiorna = scanner.nextLine();

                    System.out.println("Nuovo titolo:");
                    String titoloLi = scanner.nextLine();
                    System.out.println("Nuovo anno di pubblicazione:");
                    int annoLi = scanner.nextInt();
                    System.out.println("Nuovo numero di pagine:");
                    int pagineLi = scanner.nextInt();
                    scanner.nextLine(); // pulizia
                    System.out.println("Nuovo autore:");
                    String autoreLibro = scanner.nextLine();
                    System.out.println("Nuovo genere:");
                    String genereLibro = scanner.nextLine();

                    Libro libroAggiornato = new Libro(isbnAggiorna,titoloLi,annoLi,pagineLi,autoreLibro,genereLibro);
                    boolean aggiornato = archivio.aggiornamentoElemento(isbnAggiorna, libroAggiornato);
                    if(aggiornato){
                        System.out.println("Libro aggiornato con successo!");
                    } else {
                        System.out.println("Libro con ISBN " + isbnAggiorna + " non trovato.");
                    }
                    archivio.aggiornamentoElemento(isbnAggiorna,libroAggiornato);
                    archivio.stampaCatalogo();

                case 8:
                    System.out.println("Aggiornamento di una rivista");
                    System.out.println("Inserisci l'ISBN della rivista da aggiornare:");
                    scanner.nextLine();
                    String isbnRiv = scanner.nextLine();

                    System.out.println("Nuovo titolo:");
                    String titoloRiv = scanner.nextLine();
                    System.out.println("Nuovo anno di pubblicazione:");
                    int annoRiv = scanner.nextInt();
                    System.out.println("Nuovo numero di pagine:");
                    int pagineRiv = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Nuova periodicità (SETTIMANALE, MENSILE, SEMESTRALE):");
                    String periodicitaStra = scanner.nextLine();
                    Periodicita periodici = Periodicita.valueOf(periodicitaStra.toUpperCase());

                    Rivista rivistaAggiornata = new Rivista(isbnRiv, titoloRiv, annoRiv, pagineRiv, periodici);
                    archivio.aggiornamentoElemento(isbnRiv,rivistaAggiornata);
                    archivio.stampaCatalogo();
                    break;

                case 9:
                    System.out.println("Statistiche del catalogo:");
                    archivio.stampaStatistiche();
                    break;

                default:
                    System.out.println("Opzione non valida, riprova.");
                    break;















            }
        }











    }




















}
