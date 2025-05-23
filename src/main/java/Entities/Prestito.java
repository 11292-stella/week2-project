package Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Prestito {


    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Utente utente;

    @ManyToOne
    private ElementoCatalogo elementoCatalogo;
    private LocalDate dataInizioPrestito;
    private LocalDate dataPrevistaRestituzione;
    private LocalDate dataRestituzioneEffettiva;


    public String restituzione() {
        if (dataRestituzioneEffettiva == null) {
            return "Non ancora restituito";
        } else if (dataRestituzioneEffettiva.isAfter(dataPrevistaRestituzione)) {
            return "Restituito in ritardo";
        } else {
            return "Restituito in tempo";
        }
    }

    public Prestito() {
    }

    public Prestito(Utente utente, ElementoCatalogo elementoCatalogo, LocalDate dataInizioPrestito, LocalDate dataPrevistaRestituzione, LocalDate dataRestituzioneEffettiva) {

        this.utente = utente;
        this.elementoCatalogo = elementoCatalogo;
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataPrevistaRestituzione = dataPrevistaRestituzione;
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public ElementoCatalogo getElementoCatalogo() {
        return elementoCatalogo;
    }

    public void setElementoCatalogo(ElementoCatalogo elementoCatalogo) {
        this.elementoCatalogo = elementoCatalogo;
    }

    public LocalDate getDataInizioPrestito() {
        return dataInizioPrestito;
    }

    public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
    }

    public LocalDate getDataPrevistaRestituzione() {
        return dataPrevistaRestituzione;
    }

    public void setDataPrevistaRestituzione(LocalDate dataPrevistaRestituzione) {
        this.dataPrevistaRestituzione = dataPrevistaRestituzione;
    }

    public LocalDate getDataRestituzioneEffettiva() {
        return dataRestituzioneEffettiva;
    }

    public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "id=" + id +
                ", utente=" + utente +
                ", elementoCatalogo=" + elementoCatalogo +
                ", dataInizioPrestito=" + dataInizioPrestito +
                ", dataPrevistaRestituzione=" + dataPrevistaRestituzione +
                ", dataRestituzioneEffettiva=" + dataRestituzioneEffettiva +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Prestito prestito = (Prestito) o;
        return id == prestito.id && Objects.equals(utente, prestito.utente) && Objects.equals(elementoCatalogo, prestito.elementoCatalogo) && Objects.equals(dataInizioPrestito, prestito.dataInizioPrestito) && Objects.equals(dataPrevistaRestituzione, prestito.dataPrevistaRestituzione) && Objects.equals(dataRestituzioneEffettiva, prestito.dataRestituzioneEffettiva);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, utente, elementoCatalogo, dataInizioPrestito, dataPrevistaRestituzione, dataRestituzioneEffettiva);
    }
}
