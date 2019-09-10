/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Cars;
import java.util.Date;

/**
 *
 * @author Dennis
 */
public class CarsDTO {

    private String make;
    private String model;
    private Date registrationDate;
    private Long modelYear;
    private Long horsepower;
    private Long mileage;
    private Long doors;
    private Long price;

    public CarsDTO(Cars car) {
        this.make = car.getMake();
        this.model = car.getModel();
        this.registrationDate = car.getRegistrationDate();
        this.modelYear = car.getModelYear();
        this.horsepower = car.getHorsepower();
        this.mileage = car.getMileage();
        this.doors = car.getDoors();
        this.price = car.getPrice();
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
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
