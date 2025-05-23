package Dao;

import Entities.Utente;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UtenteDao {

    private EntityManager em;

    public UtenteDao(EntityManager em){
        this.em=em;
    }

    public void salva(Utente utente){
        em.getTransaction().begin();
        em.persist(utente);
        em.getTransaction().commit();
    }

    public Utente getById(int id){
        return em.find(Utente.class,id);
    }

    public void remove(int id){
        Utente e = getById(id);

        if (e != null) {
            em.getTransaction().begin();
            em.remove(e);
            em.getTransaction().commit();
        }
        else {
            System.out.println("non trovato" + e);
        }
    }

    public List<Utente> trovaTutti() {
        return em.createQuery("select u from Utente u", Utente.class).getResultList();
    }
}
