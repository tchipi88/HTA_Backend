/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ganeo.appli.hta.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tchipnangngansopa
 */
@Entity
public class Patient_Releve extends AbstractAuditingEntity{
    
    @NotNull
    @ManyToOne
    private Patient patient;
    
    @NotNull
    private Short pa_systolique;
    
    @NotNull
    private short  pa_diastolique ;
    
    @NotNull
    private LocalDate date_releve;
    
    private Short  poids;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Short getPa_systolique() {
        return pa_systolique;
    }

    public void setPa_systolique(Short pa_systolique) {
        this.pa_systolique = pa_systolique;
    }

    public short getPa_diastolique() {
        return pa_diastolique;
    }

    public void setPa_diastolique(short pa_diastolique) {
        this.pa_diastolique = pa_diastolique;
    }

    public LocalDate getDate_releve() {
        return date_releve;
    }

    public void setDate_releve(LocalDate date_releve) {
        this.date_releve = date_releve;
    }

    public Short getPoids() {
        return poids;
    }

    public void setPoids(Short poids) {
        this.poids = poids;
    }
    
    
}
