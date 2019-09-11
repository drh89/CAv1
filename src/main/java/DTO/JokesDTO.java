/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Jokes;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Henning
 */
public class JokesDTO implements Serializable {

    private Long id;
    private String JokesLine;
    private String reference;
    private String type;
    private String authorName;
    private int rating;
    
    public JokesDTO()
    {
        
    }

    public JokesDTO(Jokes jk) {
        this.JokesLine = jk.getJokesLine();
        this.reference = jk.getReference();
        this.type = jk.getType();
        this.authorName = jk.getAuthorName();
        this.rating = jk.getRating();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJokesLine() {
        return JokesLine;
    }

    public void setJokesLine(String JokesLine) {
        this.JokesLine = JokesLine;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }
}
