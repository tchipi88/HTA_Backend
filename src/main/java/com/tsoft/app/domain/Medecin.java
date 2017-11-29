/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsoft.app.domain;

import javax.persistence.Entity;

/**
 *
 * @author tchipnangngansopa
 */
@Entity
public class Medecin extends User {

    private String title;
    private Integer yearGraduation;

    private String healthCenter;
    private String specialist;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYearGraduation() {
        return yearGraduation;
    }

    public void setYearGraduation(Integer yearGraduation) {
        this.yearGraduation = yearGraduation;
    }

    public String getHealthCenter() {
        return healthCenter;
    }

    public void setHealthCenter(String healthCenter) {
        this.healthCenter = healthCenter;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

}
