/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Cars;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

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
    
        public List<Cars> getCars(){
        EntityManager em = getEntityManager();
        TypedQuery tq = em.createNamedQuery("cars.all", Cars.class);
        List<Cars> cars = tq.getResultList();
        return cars;
    }

}
