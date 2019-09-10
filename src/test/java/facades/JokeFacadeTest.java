package facades;

import utils.EMF_Creator;
import entities.Joke;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class JokeFacadeTest {

    private static EntityManagerFactory emf;
    private static JokeFacade FACADE;

    public JokeFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/CA1DB_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        FACADE = JokeFacade.getJokeFacade(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        FACADE = JokeFacade.getJokeFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.getTransaction().commit();

            em.getTransaction().begin();
            em.persist(new Joke("Who came first? I did. Feelsbadman", "Chicken and egg", "Bad Joke", "cake", 1));
            em.getTransaction().commit();

            em.getTransaction().begin();
            em.persist(new Joke("Knock knock. Who's there. Who. Who who?. You're an owl! HAHAHA", "Knock Knock", "Cringy Joke", "moronic", 1));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void tesGetJokeCount() {
        assertEquals(2, FACADE.getJokeCount(), "Expects two rows in the database");
    }

    @Test
    public void testGetAllJokes() {
        assertEquals("Bad Joke", FACADE.getAllJokes().get(0).getType());
    }

    @Test
    public void testGetByID() {
        assertEquals("Bad Joke", FACADE.getJokeById(FACADE.getAllJokes().get(0).getId()).getType());
    }

    @Test
    public void testGetDTOByID() {
        assertEquals("Cringy Joke", FACADE.getJokeDTOByID(FACADE.getAllJokes().get(1).getId()).getType());
    }

    // #### Tests if there is an occurence of the randomly selected value
    @Test
    public void testGetRandomByGetAllJokes() {
        assertEquals(true,
                FACADE.getAllJokes().stream().anyMatch(o -> o.getType().equals("Cringy Joke")
                ));
    }

    // #### Tests whether it's just the same value being returned ####
    // #### Note: This test only fails on null values ####
    // #### Because of the extremely unlikely case of getting the same object 100 times ####
    // #### The test will NOT fail if there's 1 or less objects ####
    @Test
    public void testRandomMultipleValues() {
        Set<Joke> arr = new HashSet<Joke>();
        for (int i = 0; i < 100; i++) {
            Joke j = FACADE.getRandomJoke();
            if (arr.contains(null)) {
                fail("Set null");
            } else if (arr.size() > 1) {
                break;
            } else if (arr.stream().anyMatch(o -> o.getType().equals(j.getType())) == false) {
                arr.add(j);
            }
        }
        if (arr.size() <= 1) {
            System.out.println("###########################");
            System.out.println("Size of set was not over 1");
            System.out.println("###########################");
        }
    }
}
