package facades;

import DTO.JokeDTO;
import entities.Joke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class JokeFacade implements JokeInterface {

    private static JokeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private JokeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static JokeFacade getJokeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    @Override
    public List<Joke> getAllJokes() {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Joke.findAll")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Joke getJokeById(long id) {
        EntityManager em = getEntityManager();
        try {
            return (Joke) em.createNamedQuery("Joke.findById")
                    .setParameter("id", id)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public JokeDTO getJokeDTOByID(long id) {
        return new JokeDTO(getEntityManager().find(Joke.class, id));
    }

    @Override
    public Joke getRandomJoke() {
        EntityManager em = getEntityManager();
        try {
            List<Joke> jokes = em.createNamedQuery("Joke.findAll")
                    .getResultList();
            ArrayList jokeIDs = new ArrayList<>();
            for (Joke j : jokes) {
                jokeIDs.add(j.getId());
            }
            int randomID = ThreadLocalRandom.current().nextInt(0, jokeIDs.size());
            return (Joke) em.createNamedQuery("Joke.findById")
                    .setParameter("id", (int) (long)(jokes.get(randomID).getId()))
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public void deleteJoke(long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Joke.class, id));
        em.getTransaction().commit();
    }

    @Override
    public void populateJoke() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //TODO Remove/Change this before use
    public long getJokeCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long renameMeCount = (long) em.createQuery("SELECT COUNT(j) FROM Joke j").getSingleResult();
            return renameMeCount;
        } finally {
            em.close();
        }
    }
}
