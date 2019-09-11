/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Cars;
import entities.Members;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author Emil
 */
public class CarsFacadeTest {

    private static EntityManagerFactory emf;
    private static CarsFacade facade;

    public CarsFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/CAv1DB_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = CarsFacade.getCarsFacade(emf);
    }
    
    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        facade = CarsFacade.getCarsFacade(emf);
    }
    
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Cars.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
            
            em.getTransaction().begin();
            em.persist(new Cars("Turqoise with a hint of yellowgreen", "Meep", "Hej", 100L));
            em.getTransaction().commit();
            
            em.getTransaction().begin();
            em.persist(new Cars("Doomlord Bob", "Hun er ret lækker", "idk", 1000L));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    @Test
    public void testAddMember() {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Cars> query = em.createQuery("SELECT c FROM Cars c", Cars.class);
        List l = query.getResultList();
        int before = l.size();
        
        Cars car = new Cars("AddMember", "Test", "bandit",10L);
        facade.addCar(car);

        query = em.createQuery("SELECT c FROM Cars c", Cars.class);
        l = query.getResultList();
        int after = l.size();

        assertEquals(before + 1, after);
    }
    
    @Test
    public void testDeleteMember() {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Cars> query = em.createQuery("SELECT c FROM Cars c", Cars.class);
        List l = query.getResultList();
        int before = l.size();
        Cars car = new Cars("DeleteCar", "Test", "deletedis", 10L);

        em.getTransaction().begin();
        em.persist(car);
        em.getTransaction().commit();
        facade.deleteCar(car);

        l = query.getResultList();
        int after = l.size();

        assertEquals(before, after);
    }
    
    @Test
    public void testGetAllMembers() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Cars> query = em.createQuery("SELECT c FROM Cars c", Cars.class);
        List l = query.getResultList();

        assertEquals(l.size(), facade.getAllCars().size());
        assertEquals("Doomlord Bob", facade.getAllCars().get(1).getMake());
    }
    
  
}
