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
public class PatientTreatment extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    private Patient patient;

    @NotNull
    private LocalDate dateReleve = LocalDate.now();

    private boolean prescription;

    private String treatmentDuration;

    private boolean question1;
    private boolean question2;
    private boolean question3;
    private boolean question4;
    private boolean question5;
    private boolean question6;
    private String question7;

    private int score;

    public PatientTreatment() {
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

    public LocalDate getDateReleve() {
        return dateReleve;
    }

    public void setDateReleve(LocalDate dateReleve) {
        this.dateReleve = dateReleve;
    }

    public boolean isPrescription() {
        return prescription;
    }

    public void setPrescription(boolean prescription) {
        this.prescription = prescription;
    }

    public String getTreatmentDuration() {
        return treatmentDuration;
    }

    public void setTreatmentDuration(String treatmentDuration) {
        this.treatmentDuration = treatmentDuration;
    }

    public boolean isQuestion1() {
        return question1;
    }

    public void setQuestion1(boolean question1) {
        this.question1 = question1;
    }

    public boolean isQuestion2() {
        return question2;
    }

    public void setQuestion2(boolean question2) {
        this.question2 = question2;
    }

    public boolean isQuestion3() {
        return question3;
    }

    public void setQuestion3(boolean question3) {
        this.question3 = question3;
    }

    public boolean isQuestion4() {
        return question4;
    }

    public void setQuestion4(boolean question4) {
        this.question4 = question4;
    }

    public boolean isQuestion5() {
        return question5;
    }

    public void setQuestion5(boolean question5) {
        this.question5 = question5;
    }

    public boolean isQuestion6() {
        return question6;
    }

    public void setQuestion6(boolean question6) {
        this.question6 = question6;
    }

    public String getQuestion7() {
        return question7;
    }

    public void setQuestion7(String question7) {
        this.question7 = question7;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
