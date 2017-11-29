/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsoft.app.domain;

import java.time.LocalDate;
import javax.persistence.Column;
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
public class PatientConsultation extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    private Patient patient;

    @NotNull
    private LocalDate dateConsultation = LocalDate.now();

    @Column
    private Integer paDiastolique;

    @Column
    private Integer paSystolique;

    @Column
    private Float weight;

    @Column
    private Integer height;

    @Column
    private Integer waist;

    @Column
    private boolean diagnosticHypertension;

    @Column
    private Integer targetPaDiastolique;

    @Column
    private Integer targetPaSystolique;

    @Column
    private boolean saltReduction;

    @Column
    private boolean physicalActivity;

    @Column
    private boolean healthyFood;

    @Column
    private boolean smookingCessation;

    @Column
    private boolean moderationAlcohool;

    @Column
    private String otherDiagnostic;

    @Column
    private boolean prescriptionWithDrug;

    @Column
    private LocalDate dateNextVisite;

    public Integer getPaDiastolique() {
        return paDiastolique;
    }

    public void setPaDiastolique(Integer paDiastolique) {
        this.paDiastolique = paDiastolique;
    }

    public Integer getPaSystolique() {
        return paSystolique;
    }

    public void setPaSystolique(Integer paSystolique) {
        this.paSystolique = paSystolique;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWaist() {
        return waist;
    }

    public void setWaist(Integer waist) {
        this.waist = waist;
    }

    public Integer getTargetPaDiastolique() {
        return targetPaDiastolique;
    }

    public void setTargetPaDiastolique(Integer targetPaDiastolique) {
        this.targetPaDiastolique = targetPaDiastolique;
    }

    public Integer getTargetPaSystolique() {
        return targetPaSystolique;
    }

    public void setTargetPaSystolique(Integer targetPaSystolique) {
        this.targetPaSystolique = targetPaSystolique;
    }

    public boolean isSaltReduction() {
        return saltReduction;
    }

    public void setSaltReduction(boolean saltReduction) {
        this.saltReduction = saltReduction;
    }

    public boolean isPhysicalActivity() {
        return physicalActivity;
    }

    public void setPhysicalActivity(boolean physicalActivity) {
        this.physicalActivity = physicalActivity;
    }

    public boolean isHealthyFood() {
        return healthyFood;
    }

    public void setHealthyFood(boolean healthyFood) {
        this.healthyFood = healthyFood;
    }

    public boolean isSmookingCessation() {
        return smookingCessation;
    }

    public void setSmookingCessation(boolean smookingCessation) {
        this.smookingCessation = smookingCessation;
    }

    public boolean isModerationAlcohool() {
        return moderationAlcohool;
    }

    public void setModerationAlcohool(boolean moderationAlcohool) {
        this.moderationAlcohool = moderationAlcohool;
    }

    public String getOtherDiagnostic() {
        return otherDiagnostic;
    }

    public void setOtherDiagnostic(String otherDiagnostic) {
        this.otherDiagnostic = otherDiagnostic;
    }

    public boolean isPrescriptionWithDrug() {
        return prescriptionWithDrug;
    }

    public void setPrescriptionWithDrug(boolean prescriptionWithDrug) {
        this.prescriptionWithDrug = prescriptionWithDrug;
    }

    public LocalDate getDateNextVisite() {
        return dateNextVisite;
    }

    public void setDateNextVisite(LocalDate dateNextVisite) {
        this.dateNextVisite = dateNextVisite;
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

    public LocalDate getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(LocalDate dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public boolean isDiagnosticHypertension() {
        return diagnosticHypertension;
    }

    public void setDiagnosticHypertension(boolean diagnosticHypertension) {
        this.diagnosticHypertension = diagnosticHypertension;
    }

    public PatientConsultation() {
    }

}
