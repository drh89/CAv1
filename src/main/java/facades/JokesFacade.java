package facades;

import DTO.JokesDTO;
import entities.Jokes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class JokesFacade implements JokesInterface {

    private static JokesFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private JokesFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static JokesFacade getJokesFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokesFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public List<Jokes> getAllJokess() {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Jokes.findAll")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Jokes getJokesById(long id) {
        return getEntityManager().find(Jokes.class, id);
    }

    public JokesDTO getJokesDTOByID(long id) {
        return new JokesDTO(getEntityManager().find(Jokes.class, id));
    }

    @Override
    public JokesDTO getRandomJokes() {
        EntityManager em = getEntityManager();
        try {
            List<Jokes> Jokess = em.createNamedQuery("Jokes.findAll")
                    .getResultList();
            ArrayList JokesIDs = Jokess
                    .stream()
                    .map(Jokes::getId)
                    .collect(Collectors.toCollection(ArrayList::new));
            int randomID = ThreadLocalRandom.current().nextInt(0, JokesIDs.size());
            return new JokesDTO((Jokes) em.createNamedQuery("Jokes.findById")
                    .setParameter("id", (int) (long) (Jokess.get(randomID).getId()))
                    .getSingleResult());
        } finally {
            em.close();
        }
    }

    public void deleteJokes(long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Jokes.class, id));
        em.getTransaction().commit();
    }

    @Override
    public void populateJokes() {
        createJokes(new Jokes("Have you ever tried to eat a clock? It's very time consuming.", "Time", "Pun", "Doomlord Bob", 3));
        createJokes(new Jokes("What do you call birds who stick together? Vel-crows.", "Birds", "Bad", "Cakeface McGee", 3));
        createJokes(new Jokes("eBay is so useless. I tried to look up lighters and all they had was 13,749 matches.", "Ebay", "Pun", "John Haddock", 3));
        createJokes(new Jokes("A baby seal walked into a club.", "Violence", "Offensive", "Kim Kung fu", 4));
    }

    @Override
    public long getJokesCount() {
        EntityManager em = emf.createEntityManager();
        try {
            return (long) em.createNamedQuery("Jokes.findCount").getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteAllJokess() {
        EntityManager em = getEntityManager();
        try {
            em.createNamedQuery("Jokes.deleteAllRows");
        } finally {
            em.close();
        }
    }

    @Override
    public Jokes createJokes(Jokes j) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(j);
        em.getTransaction().commit();
        return j;
    }

    @Override
    public List<JokesDTO> getAllJokessDTO() {
        try {
            return ((List<Jokes>) getEntityManager()
                    .createNamedQuery("Jokes.findAll").getResultList())
                    .stream()
                    .map(o -> new JokesDTO(o))
                    .collect(Collectors.toCollection(ArrayList<JokesDTO>::new));
        } finally {
            getEntityManager().close();
        }
    }

    @Override
    public List<JokesDTO> getJokessDTOByType(String type) {
        return getAllJokessDTO().stream()
                .filter(o -> o.getType().equals(type))
                .collect(Collectors.toList());
    }
}
