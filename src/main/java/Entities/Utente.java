package Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numeroDiTessera;

    private String nome;
    private String cognome;
    private LocalDate dataNascita;

    @OneToMany(mappedBy = "utente")
    private List<ElementoCatalogo> elementi = new ArrayList<>();


    public Utente() {
    }


    public Utente(String nome, String cognome, LocalDate dataNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
    }



    public int getNumeroDiTessera() {
        return numeroDiTessera;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public List<ElementoCatalogo> getElementi() {
        return elementi;
    }

    public void setElementi(List<ElementoCatalogo> elementi) {
        this.elementi = elementi;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utente)) return false;
        Utente utente = (Utente) o;
        return numeroDiTessera == utente.numeroDiTessera;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroDiTessera);
    }

    @Override
    public String toString() {
        return "Utente{" +
                "numeroDiTessera=" + numeroDiTessera +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita=" + dataNascita +
                ", elementi=" + elementi +
                '}';
    }
}
