package Progetto;

import java.time.LocalDate;
import java.util.Objects;

public abstract class ElementoCatalogo {

    private String ISBN;
    private String titolo;
    private Integer annoPubblicazione;
    private Integer numeroPagine;

    public ElementoCatalogo(String ISBN, String titolo, Integer annoPubblicazione, Integer numeroPagine) {
        this.ISBN = ISBN;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    @Override
    public String toString() {
        return "ElementoCatalogo{" +
                "ISBN='" + ISBN + '\'' +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ElementoCatalogo that = (ElementoCatalogo) o;
        return Objects.equals(ISBN, that.ISBN) && Objects.equals(titolo, that.titolo) && Objects.equals(annoPubblicazione, that.annoPubblicazione) && Objects.equals(numeroPagine, that.numeroPagine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBN, titolo, annoPubblicazione, numeroPagine);
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Integer getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(Integer annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public Integer getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(Integer numeroPagine) {
        this.numeroPagine = numeroPagine;
    }
}
