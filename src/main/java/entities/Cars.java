/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ryge <your.name at your.org>
 */
@Entity
@NamedQueries({
    @NamedQuery(name= "cars.getAll", query ="SELECT c FROM Cars c")
})
public class Cars implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    private String registrationDate;
    private Long modelYear;
    private Long horsepower;
    private Long mileage;
    private Long doors;
    private Long price;

    public Cars() {
    }

    public Cars(Long id, String make, String model, String registrationDate, Long modelYear, Long horsepower, Long mileage, Long doors, Long price) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.registrationDate = registrationDate;
        this.modelYear = modelYear;
        this.horsepower = horsepower;
        this.mileage = mileage;
        this.doors = doors;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Long getModelYear() {
        return modelYear;
    }

    public void setModelYear(Long modelYear) {
        this.modelYear = modelYear;
    }

    public Long getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Long horsepower) {
        this.horsepower = horsepower;
    }

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public Long getDoors() {
        return doors;
    }

    public void setDoors(Long doors) {
        this.doors = doors;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

}
