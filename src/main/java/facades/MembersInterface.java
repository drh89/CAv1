/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import DTO.MembersDTO;
import entities.Members;
import java.util.ArrayList;

/**
 *
 * @author emilt
 */
public interface MembersInterface {
    
    public Members addMember(Members member);
    
    public void deleteMember(Members member);

    public ArrayList<Members> getMembersByName(String name);
    
    public ArrayList<Members> getAllMembers();
    
    
    public ArrayList<MembersDTO> getMembersDTOByName(String name);
    
    public ArrayList<MembersDTO> getAllMembersDTO();
    
    public void populateMembers();
    
    public void deleteAllMembers();
    
}
