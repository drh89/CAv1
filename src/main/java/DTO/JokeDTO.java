/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Joke;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Henning
 */
public class JokeDTO implements Serializable {

    private Long id;
    private String jokeLine;
    private String reference;
    private String type;
    private String authorName;
    private int rating;
    
    public JokeDTO()
    {
        
    }

    public JokeDTO(Joke jk) {
        this.jokeLine = jk.getJokeLine();
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

    public String getJokeLine() {
        return jokeLine;
    }

    public void setJokeLine(String jokeLine) {
        this.jokeLine = jokeLine;
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
