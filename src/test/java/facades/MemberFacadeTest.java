package facades;

import utils.EMF_Creator;
import entities.Members;
import facades.MembersFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
/**
 * @author emilt
 */
public class MemberFacadeTest {

    private static EntityManagerFactory emf;
    private static MembersFacade facade;

    public MemberFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/CAv1DB_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = MembersFacade.getMemberFacade(emf);
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
        facade = MembersFacade.getMemberFacade(emf);
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
            em.createNamedQuery("Members.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
            
            em.getTransaction().begin();
            em.persist(new Members("Turqoise with a hint of yellowgreen", "Meep", "asdf32"));
            em.getTransaction().commit();
            
            em.getTransaction().begin();
            em.persist(new Members("Scarlet", "Doomlord Bob", "bj69"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testAddMember() {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Members> query = em.createQuery("SELECT m FROM Members m", Members.class);
        List l = query.getResultList();
        int before = l.size();

        Members member = new Members("AddMember", "Test", "av23");
        facade.addMember(member);

        query = em.createQuery("SELECT m FROM Members m", Members.class);
        l = query.getResultList();
        int after = l.size();

        assertEquals(before + 1, after);
    }

    @Test
    public void testDeleteMember() {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Members> query = em.createQuery("SELECT m FROM Members m", Members.class);
        List l = query.getResultList();
        int before = l.size();
        Members member = new Members("DeleteMember", "Test", "kk12");

        em.getTransaction().begin();
        em.persist(member);
        em.getTransaction().commit();
        facade.deleteMember(member);

        l = query.getResultList();
        int after = l.size();

        assertEquals(before, after);
    }

    @Test
    public void testGetMemberByName() {
        assertEquals("Turqoise with a hint of yellowgreen", facade.getMembersByName("Meep").get(0).getColor());
    }

    @Test
    public void testGetAllMembers() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Members> query = em.createQuery("SELECT m FROM Members m", Members.class);
        List l = query.getResultList();

        assertEquals(l.size(), facade.getAllMembers().size());
        assertEquals("Doomlord Bob", facade.getAllMembers().get(1).getName());
    }

}
