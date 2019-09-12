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
    
    public List<Cars> getCarsByMake(String name){
        EntityManager em = getEntityManager();
        TypedQuery tq = em.createNamedQuery("cars.make", Cars.class);
        tq.setParameter("name",name);
        List<Cars> cars = (List) tq.getResultList();
        return cars;
    }

    public void populate(Cars car) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();
        em.persist(car);
        em.getTransaction().commit();
        em.close();

        

    }

}
