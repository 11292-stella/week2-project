package Progetto;

public class Rivista extends ElementoCatalogo{

    private Periodicita periodicita;

    public Rivista(String ISBN, String titolo, Integer annoPubblicazione, Integer numeroPagine, Periodicita periodicita) {
        super(ISBN, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "titolo='" + getTitolo() + '\'' +
                ", anno=" + getAnnoPubblicazione() +
                ", numero=" + getNumeroPagine() +
                ", periodicità=" + getPeriodicita() +
                '}';
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }
}
