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
public class PatientTargetchanges extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Patient patient;

    @NotNull
    private LocalDate dateTarget = LocalDate.now();

    private boolean targetClinic;

    private boolean targetAlcohol;

    private boolean targetFood;

    private boolean targetSport;

    private boolean targetTobacco;

    private boolean targetSalt;

    private boolean targetTobacco1;

    private boolean targetTobacco2;

    private boolean targetTobacco3;

    private boolean targetTobacco4;

    private boolean targetAlcohol1;

    private boolean targetAlcohol2;

    private boolean targetAlcohol3;

    private boolean targetAlcohol4;

    private boolean targetSport1;

    private boolean targetSport2;

    private boolean targetSport3;

    private boolean targetSport4;

    private boolean targetSport5;

    private boolean targetFood1;

    private boolean targetFood2;

    private boolean targetFood3;

    private boolean targetSalt1;

    private boolean targetSalt2;

    private boolean targetSalt3;

    private boolean targetSalt4;

    private boolean targetSalt5;

    private boolean targetClinic1;

    private boolean targetClinic2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientTargetchanges)) {
            return false;
        }
        PatientTargetchanges other = (PatientTargetchanges) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tsoft.app.domain.PatientTargetchanges[ id=" + id + " ]";
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getDateTarget() {
        return dateTarget;
    }

    public void setDateTarget(LocalDate dateTarget) {
        this.dateTarget = dateTarget;
    }

    public boolean isTargetClinic() {
        return targetClinic;
    }

    public void setTargetClinic(boolean targetClinic) {
        this.targetClinic = targetClinic;
    }

    public boolean isTargetAlcohol() {
        return targetAlcohol;
    }

    public void setTargetAlcohol(boolean targetAlcohol) {
        this.targetAlcohol = targetAlcohol;
    }

    public boolean isTargetFood() {
        return targetFood;
    }

    public void setTargetFood(boolean targetFood) {
        this.targetFood = targetFood;
    }

    public boolean isTargetSport() {
        return targetSport;
    }

    public void setTargetSport(boolean targetSport) {
        this.targetSport = targetSport;
    }

    public boolean isTargetTobacco() {
        return targetTobacco;
    }

    public void setTargetTobacco(boolean targetTobacco) {
        this.targetTobacco = targetTobacco;
    }

    public boolean isTargetSalt() {
        return targetSalt;
    }

    public void setTargetSalt(boolean targetSalt) {
        this.targetSalt = targetSalt;
    }

    public boolean isTargetTobacco1() {
        return targetTobacco1;
    }

    public void setTargetTobacco1(boolean targetTobacco1) {
        this.targetTobacco1 = targetTobacco1;
    }

    public boolean isTargetTobacco2() {
        return targetTobacco2;
    }

    public void setTargetTobacco2(boolean targetTobacco2) {
        this.targetTobacco2 = targetTobacco2;
    }

    public boolean isTargetTobacco3() {
        return targetTobacco3;
    }

    public void setTargetTobacco3(boolean targetTobacco3) {
        this.targetTobacco3 = targetTobacco3;
    }

    public boolean isTargetTobacco4() {
        return targetTobacco4;
    }

    public void setTargetTobacco4(boolean targetTobacco4) {
        this.targetTobacco4 = targetTobacco4;
    }

    public boolean isTargetAlcohol1() {
        return targetAlcohol1;
    }

    public void setTargetAlcohol1(boolean targetAlcohol1) {
        this.targetAlcohol1 = targetAlcohol1;
    }

    public boolean isTargetAlcohol2() {
        return targetAlcohol2;
    }

    public void setTargetAlcohol2(boolean targetAlcohol2) {
        this.targetAlcohol2 = targetAlcohol2;
    }

    public boolean isTargetAlcohol3() {
        return targetAlcohol3;
    }

    public void setTargetAlcohol3(boolean targetAlcohol3) {
        this.targetAlcohol3 = targetAlcohol3;
    }

    public boolean isTargetAlcohol4() {
        return targetAlcohol4;
    }

    public void setTargetAlcohol4(boolean targetAlcohol4) {
        this.targetAlcohol4 = targetAlcohol4;
    }

    public boolean isTargetSport1() {
        return targetSport1;
    }

    public void setTargetSport1(boolean targetSport1) {
        this.targetSport1 = targetSport1;
    }

    public boolean isTargetSport2() {
        return targetSport2;
    }

    public void setTargetSport2(boolean targetSport2) {
        this.targetSport2 = targetSport2;
    }

    public boolean isTargetSport3() {
        return targetSport3;
    }

    public void setTargetSport3(boolean targetSport3) {
        this.targetSport3 = targetSport3;
    }

    public boolean isTargetSport4() {
        return targetSport4;
    }

    public void setTargetSport4(boolean targetSport4) {
        this.targetSport4 = targetSport4;
    }

    public boolean isTargetSport5() {
        return targetSport5;
    }

    public void setTargetSport5(boolean targetSport5) {
        this.targetSport5 = targetSport5;
    }

    public boolean isTargetFood1() {
        return targetFood1;
    }

    public void setTargetFood1(boolean targetFood1) {
        this.targetFood1 = targetFood1;
    }

    public boolean isTargetFood2() {
        return targetFood2;
    }

    public void setTargetFood2(boolean targetFood2) {
        this.targetFood2 = targetFood2;
    }

    public boolean isTargetFood3() {
        return targetFood3;
    }

    public void setTargetFood3(boolean targetFood3) {
        this.targetFood3 = targetFood3;
    }

    public boolean isTargetSalt1() {
        return targetSalt1;
    }

    public void setTargetSalt1(boolean targetSalt1) {
        this.targetSalt1 = targetSalt1;
    }

    public boolean isTargetSalt2() {
        return targetSalt2;
    }

    public void setTargetSalt2(boolean targetSalt2) {
        this.targetSalt2 = targetSalt2;
    }

    public boolean isTargetSalt3() {
        return targetSalt3;
    }

    public void setTargetSalt3(boolean targetSalt3) {
        this.targetSalt3 = targetSalt3;
    }

    public boolean isTargetSalt4() {
        return targetSalt4;
    }

    public void setTargetSalt4(boolean targetSalt4) {
        this.targetSalt4 = targetSalt4;
    }

    public boolean isTargetSalt5() {
        return targetSalt5;
    }

    public void setTargetSalt5(boolean targetSalt5) {
        this.targetSalt5 = targetSalt5;
    }

    public boolean isTargetClinic1() {
        return targetClinic1;
    }

    public void setTargetClinic1(boolean targetClinic1) {
        this.targetClinic1 = targetClinic1;
    }

    public boolean isTargetClinic2() {
        return targetClinic2;
    }

    public void setTargetClinic2(boolean targetClinic2) {
        this.targetClinic2 = targetClinic2;
    }

}
