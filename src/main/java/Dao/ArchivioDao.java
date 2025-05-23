package Dao;

import Entities.ElementoCatalogo;
import Entities.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.*;

public class ArchivioDao {


    private EntityManager em;
    public ArchivioDao(EntityManager em){
        this.em = em;
    }

    public void aggiungiElemento(ElementoCatalogo elemento){
        em.getTransaction().begin();
        em.persist(elemento);
        em.getTransaction().commit();
    }

    public ElementoCatalogo cercaPerISBN(String isbn){
        return em.find(ElementoCatalogo.class,isbn);
    }

    public void rimuoviElemento(String isbn){
        ElementoCatalogo daRimuovere = em.find(ElementoCatalogo.class,isbn);
        if (daRimuovere != null) {
            em.getTransaction().begin();
            em.remove(daRimuovere);
            em.getTransaction().commit();

        }
    }

    public List<ElementoCatalogo> cercaPerAnno(int anno){
        TypedQuery<ElementoCatalogo> query=em.createQuery(
                "select e from ElementoCatalogo e where e.annoPubblicazione =:anno",
                ElementoCatalogo.class);
               query.setParameter("anno",anno);
               return query.getResultList();


    }

    public List<Libro>ricercaPerAutore(String autore){
        TypedQuery<Libro> query=em.createQuery(
                "select e from Libro e where e.autore =:autore",
                Libro.class);
        query.setParameter("autore",autore);
        return query.getResultList();
    }






}
