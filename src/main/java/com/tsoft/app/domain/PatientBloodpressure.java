/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsoft.app.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tchipnangngansopa
 */
@Entity
public class PatientBloodpressure extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Patient patient;

    @NotNull
    private Integer paSystolique;

    @NotNull
    private Integer paDiastolique;

    @NotNull
    private Float weight;

    @NotNull
    private LocalDate dateReleve = LocalDate.now();

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Integer getPaSystolique() {
        return paSystolique;
    }

    public void setPaSystolique(Integer paSystolique) {
        this.paSystolique = paSystolique;
    }

    public Integer getPaDiastolique() {
        return paDiastolique;
    }

    public void setPaDiastolique(Integer paDiastolique) {
        this.paDiastolique = paDiastolique;
    }

    public LocalDate getDateReleve() {
        return dateReleve;
    }

    public void setDateReleve(LocalDate dateReleve) {
        this.dateReleve = dateReleve;
    }

    public PatientBloodpressure() {
    }

}
