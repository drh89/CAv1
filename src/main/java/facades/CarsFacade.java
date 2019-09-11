/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import DTO.CarsDTO;
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
public class CarsFacade implements CarsInterface{

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

    public void populate(Cars car) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();
        em.persist(car);
        em.getTransaction().commit();
        em.close();
    }

    
    
    
    
    
    //Interface methods implemented by Emil
    @Override
    public Cars addCar(Cars car) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(car);
        em.getTransaction().commit();
        em.close();
        return car;
    }

    @Override
    public void deleteCar(Cars car) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Cars c = em.merge(car);
            em.remove(c);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<Cars> getAllCars() {
        EntityManager em = getEntityManager();
        TypedQuery tq = em.createNamedQuery("cars.getAll", Cars.class);
        List<Cars> l = tq.getResultList();
        ArrayList<Cars> cars = new ArrayList();
        for (int i = 0; i < l.size(); ++i) {
            
            if (i == 0) {
                if (l.get(i).getMake().equals("")) {
                    Cars car = l.get(i);
                    deleteCar(car);
                }
            }
            
            cars.add(l.get(i));
        }
        return cars;
    }
    
    @Override
    public ArrayList<CarsDTO> getAllCarsDTO() {
        ArrayList<Cars> al = getAllCars();
        ArrayList<CarsDTO> alDTO = new ArrayList();
        for (int i = 0; i < al.size(); ++i) {
            alDTO.add(new CarsDTO(al.get(i)));
        }
        return alDTO;
    }

    @Override
    public void populateCars() {
        addCar(new Cars("Mercedes-benz", "E350", "10/04-2016", 399000L));
        addCar(new Cars("VW", "Golf", "03/07-2010", 130000L));
        addCar(new Cars("Mitsubishi", "Lancer", "25/06-1995", 16000L));
        addCar(new Cars("BMW", "330i", "06/03-2013", 260000L));
    }

    @Override
    public void deleteAllCars() {
        ArrayList<Cars> m = getAllCars();
        for (int i = 0; i < m.size(); ++i) {
            deleteCar(m.get(i));
        }
    }


}
