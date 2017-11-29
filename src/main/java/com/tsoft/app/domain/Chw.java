/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsoft.app.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tchipnangngansopa
 */
@Entity
public class Chw extends User {


    @ManyToOne
    @NotNull
    private Medecin medecin;
    
    public Integer yearBeginning;

    public Integer getYearBeginning() {
        return yearBeginning;
    }

    public void setYearBeginning(Integer yearBeginning) {
        this.yearBeginning = yearBeginning;
    }
    
    

   

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }
    
    
}
