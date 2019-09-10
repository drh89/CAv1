/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Joke;
import java.util.List;

/**
 *
 * @author Henning
 */
public interface JokeInterface {
    
    public List<Joke> getAllJokes();
    
    public Joke getJokeById(long id);
    
    public Joke getRandomJoke();
    
    public void populateJoke();
       
}

