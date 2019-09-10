package facades;

import DTO.JokeDTO;
import entities.Joke;
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
        return getEntityManager().find(Joke.class, id);
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
            ArrayList jokeIDs = jokes
                    .stream()
                    .map(Joke::getId)
                    .collect(Collectors.toCollection(ArrayList::new));
            int randomID = ThreadLocalRandom.current().nextInt(0, jokeIDs.size());
            return (Joke) em.createNamedQuery("Joke.findById")
                    .setParameter("id", (int) (long) (jokes.get(randomID).getId()))
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
        createJoke(new Joke("Have you ever tried to eat a clock? It's very time consuming.", "Time", "Pun", "Doomlord Bob", 3));
        createJoke(new Joke("What do you call birds who stick together? Vel-crows.", "Birds", "Bad", "Cakeface McGee", 3));
        createJoke(new Joke("eBay is so useless. I tried to look up lighters and all they had was 13,749 matches.", "Ebay", "Pun", "John Haddock", 3));
        createJoke(new Joke("A baby seal walked into a club.", "Violence", "Offensive", "Kim Kung fu", 4));
    }

    @Override
    public long getJokeCount() {
        EntityManager em = emf.createEntityManager();
        try {
            return (long) em.createNamedQuery("Joke.findCount").getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteAllJokes() {
        EntityManager em = getEntityManager();
        try {
            em.createNamedQuery("Joke.deleteAllRows");
        } finally {
            em.close();
        }
    }

    @Override
    public Joke createJoke(Joke j) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(j);
        em.getTransaction().commit();
        return j;
    }

    @Override
    public List<JokeDTO> getAllJokesDTO() {
        try {
            return ((List<Joke>) getEntityManager()
                    .createNamedQuery("Joke.findAll").getResultList())
                    .stream()
                    .map(o -> new JokeDTO(o))
                    .collect(Collectors.toCollection(ArrayList<JokeDTO>::new));
        } finally {
            getEntityManager().close();
        }
    }

    @Override
    public List<JokeDTO> getJokesDTOByType(String type) {
        return getAllJokesDTO().stream()
                .filter(o -> o.getType().equals(type))
                .collect(Collectors.toList());
    }
}
