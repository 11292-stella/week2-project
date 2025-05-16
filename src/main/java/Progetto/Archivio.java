package Progetto;

import java.util.*;
import java.util.stream.Collectors;

public class Archivio {

    private List<ElementoCatalogo> catalogo = new ArrayList<>();

    public ElementoCatalogo ricercaPerISBN(String isbn) throws ISBNNotFoundException{

        return catalogo.stream()
                .filter(elementoCatalogo -> elementoCatalogo.getISBN()
                        .equals(isbn)).findFirst()
                .orElseThrow(() -> new ISBNNotFoundException("Elemento con ISBN " + isbn + " non trovato."));


    }

    public void aggiungiElemento(ElementoCatalogo elemento){

        boolean esiste = catalogo.stream()
                .anyMatch(elementoCatalogo -> elementoCatalogo.getISBN()
                        .equals(elemento));

        if(!esiste){
            catalogo.add(elemento);
            System.out.println("elemento aggiunto" + elemento.getTitolo());
        } else {
            System.out.println("elemento con ISBN" + elemento.getISBN() + "gia presente! non aggiunto" );
        }

    }

    public boolean rimuoviPerISBN(String isbn){
        return catalogo.removeIf(elementoCatalogo -> elementoCatalogo.getISBN()
                .equals(isbn));
    }

    public List<ElementoCatalogo> ricercaPerAnno(int anno) {
        return catalogo.stream()
                .filter(elementoCatalogo -> elementoCatalogo.getAnnoPubblicazione()== anno)
                .toList();
    }


    public List<Libro> ricercaPerAutore(String autore){
        return catalogo.stream()
                .filter(elementoCatalogo -> elementoCatalogo instanceof Libro)
                .map(elementoCatalogo -> (Libro) elementoCatalogo)
                .filter(libro -> libro.getAutore().equals(autore))
                .toList();
    }


    public boolean aggiornamentoElemento(String isbn, ElementoCatalogo nuovoElemento){
        for (int i = 0; i < catalogo.size(); i++) {
            if(catalogo.get(i).getISBN().equals(isbn)){
                catalogo.set(i,nuovoElemento);
                return true;
            }

        }
        return false;
    }


    public void stampaStatistiche(){
        Map<Boolean, List<ElementoCatalogo>> partizione = catalogo.stream()
                .collect(Collectors.partitioningBy(e-> e instanceof Libro));

        long totaleLibri = partizione.get(true).size();
        long totaleRiviste = partizione.get(false).size();

        double mediaPagine = catalogo.stream()
                .mapToDouble(ElementoCatalogo::getNumeroPagine)
                .average()
                .orElse(0);

        ElementoCatalogo maxPagine = catalogo.stream()
                .max(Comparator.comparingInt(ElementoCatalogo::getNumeroPagine))
                .orElse(null);




        System.out.println("numero totale libri: " + totaleLibri);
        System.out.println("numero totale riviste: " + totaleRiviste);
        System.out.println("Media pagine di tutti gli elementi: " + mediaPagine);

        if (maxPagine != null) {
            System.out.println("Elemento con più pagine: " + maxPagine.getTitolo() +
                    " (" + maxPagine.getNumeroPagine() + " pagine)");
        } else {
            System.out.println("Impossibile determinare l'elemento con più pagine.");
        }





    }

    public void stampaCatalogo(){
        if(catalogo.isEmpty()){
            System.out.println("Il catalogo è vuoto");
            return;
        }
        System.out.println("contenuto del catalogo:");
        catalogo.forEach(elementoCatalogo -> {
            System.out.println(elementoCatalogo);
        });
    }
}
