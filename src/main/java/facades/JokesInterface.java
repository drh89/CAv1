/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import DTO.JokesDTO;
import entities.Jokes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Henning
 */
public interface JokesInterface {
    
    public List<Jokes> getAllJokess();
    
    public Jokes getJokesById(long id);
    
    public JokesDTO getRandomJokes();
    
    public long getJokesCount();
    
    public void populateJokes();
    
    public void deleteAllJokess();
    
    public Jokes createJokes(Jokes j);
    
    public List<JokesDTO> getAllJokessDTO();
    
    public List<JokesDTO> getJokessDTOByType(String type);
    
}

