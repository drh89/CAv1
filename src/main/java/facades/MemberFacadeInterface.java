/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Member;
import java.util.ArrayList;

/**
 *
 * @author emilt
 */
public interface MemberFacadeInterface {
    
    public Member addMember(Member member);
    
    public void deleteMember(Long id);
    
    public Member getMember(Long id);
    public Member getMember(String name);
    
    public ArrayList<Member> getALlMembers();
    
}
