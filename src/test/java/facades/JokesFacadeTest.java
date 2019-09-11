package facades;

import DTO.JokesDTO;
import utils.EMF_Creator;
import entities.Jokes;
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
public class JokesFacadeTest {

    private static EntityManagerFactory emf;
    private static JokesFacade FACADE;

    public JokesFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/CAv1DB_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        FACADE = JokesFacade.getJokesFacade(emf);
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
        FACADE = JokesFacade.getJokesFacade(emf);
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
            em.createNamedQuery("Jokes.deleteAllRows").executeUpdate();
            em.getTransaction().commit();

            em.getTransaction().begin();
            em.persist(new Jokes("Who came first? I did. Feelsbadman", "Chicken and egg", "Bad Jokes", "cake", 1));
            em.getTransaction().commit();
            
            em.getTransaction().begin();
            em.persist(new Jokes("Why don’t crabs donate? Because they’re shellfish.", "None", "Bad Jokes", "derpy", 2));
            em.getTransaction().commit();

            em.getTransaction().begin();
            em.persist(new Jokes("Knock knock. Who's there. Who. Who who?. You're an owl! HAHAHA", "Knock Knock", "Cringy Jokes", "moronic", 1));
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
    public void tesGetJokesCount() {
        assertEquals(3, FACADE.getJokesCount());
    }

    @Test
    public void testGetAllJokess() {
        assertEquals("Bad Jokes", FACADE.getAllJokess().get(0).getType());
    }

    @Test
    public void testGetByID() {
        assertEquals("Bad Jokes", FACADE.getJokesById(FACADE.getAllJokess().get(0).getId()).getType());
    }

    @Test
    public void testGetDTOByID() {
        assertEquals("Cringy Jokes", FACADE.getJokesDTOByID(FACADE.getAllJokess().get(2).getId()).getType());
    }

    // #### Tests if there is an occurence of the randomly selected value
    @Test
    public void testGetRandomByGetAllJokess() {
        assertEquals(true,
                FACADE.getAllJokess().stream().anyMatch(o -> o.getType().equals("Cringy Jokes")
                ));
    }

    // #### Tests whether it's just the same value being returned                       ####
    // #### Note: This test only fails on null values                                   ####
    // #### Because of the extremely unlikely case of getting the same object 100 times ####
    // #### The test will NOT fail if there's 1 or less objects                         ####
    // #### Alternative would be logger                                                 ####
    @Test
    public void testRandomMultipleValues() {
        Set<JokesDTO> arr = new HashSet<JokesDTO>();
        for (int i = 0; i < 100; i++) {
            JokesDTO j = FACADE.getRandomJokes();
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

    @Test
    public void testJkDTOGetAll() {
        assertEquals("Bad Jokes", FACADE.getAllJokessDTO().get(0).getType());
    }

    @Test
    public void testJkDTOGetJokessByType() {
        assertEquals("Chicken and egg", FACADE.getJokessDTOByType("Bad Jokes").get(0).getReference());
        assertEquals(2, FACADE.getJokessDTOByType("Bad Jokes").size());
    }

}
