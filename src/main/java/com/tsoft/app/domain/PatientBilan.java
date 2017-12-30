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
public class PatientBilan extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Patient patient;

    @NotNull
    private LocalDate dateBilan = LocalDate.now();

    private String lifestyleQuestion1;
    private boolean lifestyleQuestion2;
    private Integer lifestyleQuestion3;
    private Integer lifestyleQuestion4;
    private String lifestyleQuestion5;
    private boolean lifestyleQuestion6;
    private String lifestyleQuestion7;
    private String lifestyleQuestion8;
    private String lifestyleQuestion9;
    private String lifestyleQuestion10;
    private String lifestyleQuestion11;
    private String lifestyleQuestion12;
    private boolean lifestyleQuestion13;
    private Integer lifestyleQuestion14;
    private Integer lifestyleQuestion15;
    private Integer lifestyleQuestion16;
    private String lifestyleQuestion17;
    private String lifestyleQuestion18;

    private String healthQuestion1;
    private String healthQuestion2;
    private String healthQuestion3;

    private boolean knowledgeQuestion1;
    private boolean knowledgeQuestion2;
    private boolean knowledgeQuestion3;
    private boolean knowledgeQuestion4;
    private boolean knowledgeQuestion5;
    private boolean knowledgeQuestion6;
    private boolean knowledgeQuestion7;
    private boolean knowledgeQuestion8;
    private boolean knowledgeQuestion9;
    private boolean knowledgeQuestion10;
    private boolean knowledgeQuestion11;
    private String knowledgeQuestion12;
    private String knowledgeQuestion13;
    private String knowledgeQuestion14;
    private String knowledgeQuestion15;

    private String medicationQuestion1;
    private String medicationQuestion2;
    private String medicationQuestion3;
    private String medicationQuestion4;
    private String medicationQuestion5;
    private String medicationQuestion6;
    private String medicationQuestion7;
    private String medicationQuestion8;
    private String medicationQuestion9;

    public String getLifestyleQuestion1() {
        return lifestyleQuestion1;
    }

    public void setLifestyleQuestion1(String lifestyleQuestion1) {
        this.lifestyleQuestion1 = lifestyleQuestion1;
    }

    public boolean isLifestyleQuestion2() {
        return lifestyleQuestion2;
    }

    public void setLifestyleQuestion2(boolean lifestyleQuestion2) {
        this.lifestyleQuestion2 = lifestyleQuestion2;
    }

    public Integer getLifestyleQuestion3() {
        return lifestyleQuestion3;
    }

    public void setLifestyleQuestion3(Integer lifestyleQuestion3) {
        this.lifestyleQuestion3 = lifestyleQuestion3;
    }

    public Integer getLifestyleQuestion4() {
        return lifestyleQuestion4;
    }

    public void setLifestyleQuestion4(Integer lifestyleQuestion4) {
        this.lifestyleQuestion4 = lifestyleQuestion4;
    }

    public String getLifestyleQuestion5() {
        return lifestyleQuestion5;
    }

    public void setLifestyleQuestion5(String lifestyleQuestion5) {
        this.lifestyleQuestion5 = lifestyleQuestion5;
    }

    public boolean isLifestyleQuestion6() {
        return lifestyleQuestion6;
    }

    public void setLifestyleQuestion6(boolean lifestyleQuestion6) {
        this.lifestyleQuestion6 = lifestyleQuestion6;
    }

    public String getLifestyleQuestion7() {
        return lifestyleQuestion7;
    }

    public void setLifestyleQuestion7(String lifestyleQuestion7) {
        this.lifestyleQuestion7 = lifestyleQuestion7;
    }

    public String getLifestyleQuestion8() {
        return lifestyleQuestion8;
    }

    public PatientBilan() {
    }

    public void setLifestyleQuestion8(String lifestyleQuestion8) {
        this.lifestyleQuestion8 = lifestyleQuestion8;
    }

    public String getLifestyleQuestion9() {
        return lifestyleQuestion9;
    }

    public void setLifestyleQuestion9(String lifestyleQuestion9) {
        this.lifestyleQuestion9 = lifestyleQuestion9;
    }

    public String getLifestyleQuestion10() {
        return lifestyleQuestion10;
    }

    public void setLifestyleQuestion10(String lifestyleQuestion10) {
        this.lifestyleQuestion10 = lifestyleQuestion10;
    }

    public String getLifestyleQuestion11() {
        return lifestyleQuestion11;
    }

    public void setLifestyleQuestion11(String lifestyleQuestion11) {
        this.lifestyleQuestion11 = lifestyleQuestion11;
    }

    public String getLifestyleQuestion12() {
        return lifestyleQuestion12;
    }

    public void setLifestyleQuestion12(String lifestyleQuestion12) {
        this.lifestyleQuestion12 = lifestyleQuestion12;
    }

    public boolean isLifestyleQuestion13() {
        return lifestyleQuestion13;
    }

    public void setLifestyleQuestion13(boolean lifestyleQuestion13) {
        this.lifestyleQuestion13 = lifestyleQuestion13;
    }

    public Integer getLifestyleQuestion14() {
        return lifestyleQuestion14;
    }

    public void setLifestyleQuestion14(Integer lifestyleQuestion14) {
        this.lifestyleQuestion14 = lifestyleQuestion14;
    }

    public Integer getLifestyleQuestion15() {
        return lifestyleQuestion15;
    }

    public void setLifestyleQuestion15(Integer lifestyleQuestion15) {
        this.lifestyleQuestion15 = lifestyleQuestion15;
    }

    public Integer getLifestyleQuestion16() {
        return lifestyleQuestion16;
    }

    public void setLifestyleQuestion16(Integer lifestyleQuestion16) {
        this.lifestyleQuestion16 = lifestyleQuestion16;
    }

    public String getLifestyleQuestion17() {
        return lifestyleQuestion17;
    }

    public void setLifestyleQuestion17(String lifestyleQuestion17) {
        this.lifestyleQuestion17 = lifestyleQuestion17;
    }

    public String getLifestyleQuestion18() {
        return lifestyleQuestion18;
    }

    public void setLifestyleQuestion18(String lifestyleQuestion18) {
        this.lifestyleQuestion18 = lifestyleQuestion18;
    }

    public String getHealthQuestion1() {
        return healthQuestion1;
    }

    public void setHealthQuestion1(String healthQuestion1) {
        this.healthQuestion1 = healthQuestion1;
    }

    public String getHealthQuestion2() {
        return healthQuestion2;
    }

    public void setHealthQuestion2(String healthQuestion2) {
        this.healthQuestion2 = healthQuestion2;
    }

    public String getHealthQuestion3() {
        return healthQuestion3;
    }

    public void setHealthQuestion3(String healthQuestion3) {
        this.healthQuestion3 = healthQuestion3;
    }

    public boolean isKnowledgeQuestion1() {
        return knowledgeQuestion1;
    }

    public void setKnowledgeQuestion1(boolean knowledgeQuestion1) {
        this.knowledgeQuestion1 = knowledgeQuestion1;
    }

    public boolean isKnowledgeQuestion2() {
        return knowledgeQuestion2;
    }

    public void setKnowledgeQuestion2(boolean knowledgeQuestion2) {
        this.knowledgeQuestion2 = knowledgeQuestion2;
    }

    public boolean isKnowledgeQuestion3() {
        return knowledgeQuestion3;
    }

    public void setKnowledgeQuestion3(boolean knowledgeQuestion3) {
        this.knowledgeQuestion3 = knowledgeQuestion3;
    }

    public boolean isKnowledgeQuestion4() {
        return knowledgeQuestion4;
    }

    public void setKnowledgeQuestion4(boolean knowledgeQuestion4) {
        this.knowledgeQuestion4 = knowledgeQuestion4;
    }

    public boolean isKnowledgeQuestion5() {
        return knowledgeQuestion5;
    }

    public void setKnowledgeQuestion5(boolean knowledgeQuestion5) {
        this.knowledgeQuestion5 = knowledgeQuestion5;
    }

    public boolean isKnowledgeQuestion6() {
        return knowledgeQuestion6;
    }

    public void setKnowledgeQuestion6(boolean knowledgeQuestion6) {
        this.knowledgeQuestion6 = knowledgeQuestion6;
    }

    public boolean isKnowledgeQuestion7() {
        return knowledgeQuestion7;
    }

    public void setKnowledgeQuestion7(boolean knowledgeQuestion7) {
        this.knowledgeQuestion7 = knowledgeQuestion7;
    }

    public boolean isKnowledgeQuestion8() {
        return knowledgeQuestion8;
    }

    public void setKnowledgeQuestion8(boolean knowledgeQuestion8) {
        this.knowledgeQuestion8 = knowledgeQuestion8;
    }

    public boolean isKnowledgeQuestion9() {
        return knowledgeQuestion9;
    }

    public void setKnowledgeQuestion9(boolean knowledgeQuestion9) {
        this.knowledgeQuestion9 = knowledgeQuestion9;
    }

    public boolean isKnowledgeQuestion10() {
        return knowledgeQuestion10;
    }

    public void setKnowledgeQuestion10(boolean knowledgeQuestion10) {
        this.knowledgeQuestion10 = knowledgeQuestion10;
    }

    public boolean isKnowledgeQuestion11() {
        return knowledgeQuestion11;
    }

    public void setKnowledgeQuestion11(boolean knowledgeQuestion11) {
        this.knowledgeQuestion11 = knowledgeQuestion11;
    }

    public String getKnowledgeQuestion12() {
        return knowledgeQuestion12;
    }

    public void setKnowledgeQuestion12(String knowledgeQuestion12) {
        this.knowledgeQuestion12 = knowledgeQuestion12;
    }

    public String getKnowledgeQuestion13() {
        return knowledgeQuestion13;
    }

    public void setKnowledgeQuestion13(String knowledgeQuestion13) {
        this.knowledgeQuestion13 = knowledgeQuestion13;
    }

    public String getKnowledgeQuestion14() {
        return knowledgeQuestion14;
    }

    public void setKnowledgeQuestion14(String knowledgeQuestion14) {
        this.knowledgeQuestion14 = knowledgeQuestion14;
    }

    public String getKnowledgeQuestion15() {
        return knowledgeQuestion15;
    }

    public void setKnowledgeQuestion15(String knowledgeQuestion15) {
        this.knowledgeQuestion15 = knowledgeQuestion15;
    }

    public String getMedicationQuestion1() {
        return medicationQuestion1;
    }

    public void setMedicationQuestion1(String medicationQuestion1) {
        this.medicationQuestion1 = medicationQuestion1;
    }

    public String getMedicationQuestion2() {
        return medicationQuestion2;
    }

    public void setMedicationQuestion2(String medicationQuestion2) {
        this.medicationQuestion2 = medicationQuestion2;
    }

    public String getMedicationQuestion3() {
        return medicationQuestion3;
    }

    public void setMedicationQuestion3(String medicationQuestion3) {
        this.medicationQuestion3 = medicationQuestion3;
    }

    public String getMedicationQuestion4() {
        return medicationQuestion4;
    }

    public void setMedicationQuestion4(String medicationQuestion4) {
        this.medicationQuestion4 = medicationQuestion4;
    }

    public String getMedicationQuestion5() {
        return medicationQuestion5;
    }

    public void setMedicationQuestion5(String medicationQuestion5) {
        this.medicationQuestion5 = medicationQuestion5;
    }

    public String getMedicationQuestion6() {
        return medicationQuestion6;
    }

    public void setMedicationQuestion6(String medicationQuestion6) {
        this.medicationQuestion6 = medicationQuestion6;
    }

    public String getMedicationQuestion7() {
        return medicationQuestion7;
    }

    public void setMedicationQuestion7(String medicationQuestion7) {
        this.medicationQuestion7 = medicationQuestion7;
    }

    public String getMedicationQuestion8() {
        return medicationQuestion8;
    }

    public void setMedicationQuestion8(String medicationQuestion8) {
        this.medicationQuestion8 = medicationQuestion8;
    }

    public String getMedicationQuestion9() {
        return medicationQuestion9;
    }

    public void setMedicationQuestion9(String medicationQuestion9) {
        this.medicationQuestion9 = medicationQuestion9;
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

    public LocalDate getDateBilan() {
        return dateBilan;
    }

    public void setDateBilan(LocalDate dateBilan) {
        this.dateBilan = dateBilan;
    }

}
