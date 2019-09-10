/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Cars;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author Ryge <your.name at your.org>
 */
public class CarsFacade {

    private static CarsFacade instance;
    private static EntityManagerFactory emf;

    public CarsFacade() {
    }

    public static CarsFacade getCarsFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarsFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Cars> getCars() {
        EntityManager em = getEntityManager();
        TypedQuery tq = em.createNamedQuery("cars.getAll", Cars.class);
        List<Cars> cars = tq.getResultList();
        return cars;
    }
    
    public String populate() {
        EntityManager em = getEntityManager();
        Date date = new Date();
        Cars c1 = new Cars(1L, "Mercedes-benz", "E350", "10/04-2016", 2017L, 268L, 16000L, 5L, 399000L);
        Cars c2 = new Cars(1L, "VW", "Golf", "03/07-2010", 2010L, 115L, 206000L, 5L, 130000L);
        Cars c3 = new Cars(1L, "Mitsubishi", "Lancer", "25/06-1995", 1994L, 116L, 344000L, 4L, 16000L);
        Cars c4 = new Cars(1L, "BMW", "330i", "06/03-2013", 2012L, 178L, 5000L, 4L, 260000L);
        ArrayList<Cars> cars = new ArrayList();
        cars.add(c1);
        cars.add(c2);
        cars.add(c3);
        cars.add(c4);
        em.getTransaction().begin();
        for (Cars car : cars) {
            em.persist(car);
            
        }
        em.getTransaction().commit();
        em.close();
    
        return "Success!";
        
    }

}
