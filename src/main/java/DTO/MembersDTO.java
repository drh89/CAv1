/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Members;

/** 
 * @author emilt
 */
public class MembersDTO {
    
    private String student_name;
    private int student_id;
    private String student_color;

    public MembersDTO(Members member) {
        this.student_name = member.getName();
        this.student_id = member.getStudentID();
        this.student_color = member.getColor();
    }

    public String getStudent_name() {
        return student_name;
    }

    public int getStudent_id() {
        return student_id;
    }

    public String getStudent_color() {
        return student_color;
    }
    
    
    
}
