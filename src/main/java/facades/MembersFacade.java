/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Members;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author emilt
 */
public class MembersFacade implements MemberFacadeInterface {

    private static MembersFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private MembersFacade() {}
    
    
    public static MembersFacade getMemberFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MembersFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

     @Override
    public Members addMember(Members member) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(member);
        em.getTransaction().commit();
        return member;
    }

    @Override
    public void deleteMember(Members member) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Members memb = em.merge(member);
            em.remove(memb);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public ArrayList<Members> getMembersByName(String name) {
        EntityManager em = getEntityManager();
        TypedQuery<Members> query = em.createQuery("SELECT m FROM Members m WHERE m.name = :name", Members.class);
        query.setParameter("name", name);
        ArrayList<Members> l = new ArrayList();

        for (Members m : query.getResultList()) {
            l.add((Members) m);
        }

        return l;
    }

    @Override
    public ArrayList<Members> getAllMembers() {
        EntityManager em = getEntityManager();
        TypedQuery<Members> query = em.createQuery("SELECT m FROM Members m", Members.class);
        ArrayList<Members> l = new ArrayList();

        for (Members m : query.getResultList()) {
            l.add((Members) m);
        }

        return l;
    }

}
