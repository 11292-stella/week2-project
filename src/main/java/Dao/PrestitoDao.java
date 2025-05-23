package Dao;

import Entities.Prestito;
import Entities.Utente;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PrestitoDao {
    private EntityManager em;

    public PrestitoDao(EntityManager em){
        this.em=em;
    }

    public void salva(Prestito prestito){
        em.getTransaction().begin();
        em.persist(prestito);
        em.getTransaction().commit();
    }

    public Prestito getById(int id){
        return em.find(Prestito.class,id);
    }

    public void remove(int id){
        Prestito e = getById(id);

        if (e != null) {
            em.getTransaction().begin();
            em.remove(e);
            em.getTransaction().commit();
        }
        else {
            System.out.println("non trovato" + e);
        }
    }

    public List<Prestito> trovaTutti() {
        return em.createQuery("select u from Prestito u", Prestito.class).getResultList();
    }

    public List<Prestito> trovaPrestitiAttivi() {
        return em.createQuery(
                "select p from Prestito p where p.dataRestituzioneEffettiva is null",
                Prestito.class).getResultList();
    }
}
