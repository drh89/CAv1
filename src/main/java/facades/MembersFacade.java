/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import DTO.MembersDTO;
import entities.Members;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author emilt
 */
public class MembersFacade implements MembersInterface {

    private static MembersFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MembersFacade() {
    }

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

    public ArrayList<Members> getAllMembers() {
        EntityManager em = getEntityManager();
        TypedQuery<Members> query = em.createQuery("SELECT m FROM Members m", Members.class);
        ArrayList<Members> l = new ArrayList();

        for (Members m : query.getResultList()) {
            l.add((Members) m);
        }

        return l;
    }

    public ArrayList<MembersDTO> getMembersDTOByName(String name) {
        ArrayList<MembersDTO> mdto = new ArrayList();
        ArrayList<Members> m = getMembersByName(name);

        for (int i = 0; i < m.size(); ++i) {
            mdto.add(new MembersDTO(m.get(i)));
        }
        return mdto;
    }

    @Override
    public ArrayList<MembersDTO> getAllMembersDTO() {
        ArrayList<MembersDTO> mdto = new ArrayList();
        ArrayList<Members> m = getAllMembers();

        for (int i = 0; i < m.size(); ++i) {
            //Current solution to problem occuring when deleting all members and then populating after
            //Problem occurs when there is no members from JPA, and pressing reload name button
            //then the names wont be updated, unless there is another name.
            //change the javascript to handle if data is empty
            if (i == 0) {
                if (m.get(i).getName().equals("")) {
                    Members member = m.get(i);
                    deleteMember(member);
                }
            }
            mdto.add(new MembersDTO(m.get(i)));
        }
        return mdto;
    }

    @Override
    public void populateMembers() {
        addMember(new Members("Grøn", "Sven", 1));
        addMember(new Members("Blå", "Bandit", 2));
        addMember(new Members("Hvid", "Bro", 3));
        addMember(new Members("Lilla", "Sveske", 4));
        addMember(new Members("Rød", "Bandit", 5));
        addMember(new Members("Gul", "Abekat", 6));
    }

    @Override
    public void deleteAllMembers() {
        ArrayList<Members> m = getAllMembers();
        for (int i = 0; i < m.size(); ++i) {
            deleteMember(m.get(i));
        }
    }
    
}
