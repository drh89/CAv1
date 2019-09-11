/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Cars;

/**
 * @author Dennis
 */
public class CarsDTO {

    private String make;
    private String model;
    private String registrationDate;
    private Long price;

    public CarsDTO(Cars car) {
        this.make = car.getMake();
        this.model = car.getModel();
        this.registrationDate = car.getRegistrationDate();
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

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

}
