/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Members;
import java.util.ArrayList;

/**
 *
 * @author emilt
 */
public interface MemberFacadeInterface {
    
    public Members addMember(Members member);
    
    public void deleteMember(Long id);
    
    public Members getMember(Long id);
    public Members getMember(String name);
    
    public ArrayList<Members> getAllMembers();
    
}
