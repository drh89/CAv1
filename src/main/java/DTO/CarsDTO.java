/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Dennis
 */
public class CarsDTO {
    private String make;
    private String model;
    private Long registrationDate;
    private Long modelYear;
    private Long horsepower;
    private Long mileage;
    private Long doors;
    private Long price;
    
    
    public CarsDTO(String make, String model, Long registrationDate, Long modelYear, Long horsepower, Long mileage, Long doors, Long price) {
        this.make = make;
        this.model = model;
        this.registrationDate = registrationDate;
        this.modelYear = modelYear;
        this.horsepower = horsepower;
        this.mileage = mileage;
        this.doors = doors;
        this.price = price;
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

    public Long getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Long registrationDate) {
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
