/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import DTO.CarsDTO;
import entities.Cars;
import java.util.ArrayList;

/**
 *
 * @author emilt
 */
public interface CarsInterface {
    
     public Cars addCar(Cars car);
    
    public void deleteCar(Cars car);
    
    public ArrayList<Cars> getAllCars();
    
    
    public ArrayList<CarsDTO> getAllCarsDTO();
    
    public void populateCars();
    
    public void deleteAllCars();
    
}
