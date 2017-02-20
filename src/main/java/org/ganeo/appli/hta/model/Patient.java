/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ganeo.appli.hta.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tchipnangngansopa
 */
@Entity
public class Patient extends Personne {

    @NotNull
    private LocalDate date_naissance;

    private String lieu_naissance;

    private String profession;

    private Short taille;

    private Short poids;

    @NotNull
    @ManyToOne
    private CHW chw;

    private boolean fumeur;

    private Byte nbre_cigarettes;
    @Enumerated
    private Periodicite periodicite_cigarettes;

    private boolean alcoolique;

    private Byte nbre_bouteilles;

    @Enumerated
    private Periodicite periodicite_boisson;

    private boolean antecedents_familiaux;

    private boolean antecedents_cardiaques;
    private boolean antecedents_hypertension;
    private String antecedents_autres;

    private boolean diabetique;

    private boolean hypertendu;

    public LocalDate getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getLieu_naissance() {
        return lieu_naissance;
    }

    public void setLieu_naissance(String lieu_naissance) {
        this.lieu_naissance = lieu_naissance;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Short getTaille() {
        return taille;
    }

    public void setTaille(Short taille) {
        this.taille = taille;
    }

    public Short getPoids() {
        return poids;
    }

    public void setPoids(Short poids) {
        this.poids = poids;
    }

    public CHW getChw() {
        return chw;
    }

    public void setChw(CHW chw) {
        this.chw = chw;
    }

    public boolean isFumeur() {
        return fumeur;
    }

    public void setFumeur(boolean fumeur) {
        this.fumeur = fumeur;
    }

    public Byte getNbre_cigarettes() {
        return nbre_cigarettes;
    }

    public void setNbre_cigarettes(Byte nbre_cigarettes) {
        this.nbre_cigarettes = nbre_cigarettes;
    }

    public Periodicite getPeriodicite_cigarettes() {
        return periodicite_cigarettes;
    }

    public void setPeriodicite_cigarettes(Periodicite periodicite_cigarettes) {
        this.periodicite_cigarettes = periodicite_cigarettes;
    }

    public boolean isAlcoolique() {
        return alcoolique;
    }

    public void setAlcoolique(boolean alcoolique) {
        this.alcoolique = alcoolique;
    }

    public Byte getNbre_bouteilles() {
        return nbre_bouteilles;
    }

    public void setNbre_bouteilles(Byte nbre_bouteilles) {
        this.nbre_bouteilles = nbre_bouteilles;
    }

    public Periodicite getPeriodicite_boisson() {
        return periodicite_boisson;
    }

    public void setPeriodicite_boisson(Periodicite periodicite_boisson) {
        this.periodicite_boisson = periodicite_boisson;
    }

    public boolean isAntecedents_familiaux() {
        return antecedents_familiaux;
    }

    public void setAntecedents_familiaux(boolean antecedents_familiaux) {
        this.antecedents_familiaux = antecedents_familiaux;
    }

    public boolean isAntecedents_cardiaques() {
        return antecedents_cardiaques;
    }

    public void setAntecedents_cardiaques(boolean antecedents_cardiaques) {
        this.antecedents_cardiaques = antecedents_cardiaques;
    }

    public boolean isAntecedents_hypertension() {
        return antecedents_hypertension;
    }

    public void setAntecedents_hypertension(boolean antecedents_hypertension) {
        this.antecedents_hypertension = antecedents_hypertension;
    }

    public String getAntecedents_autres() {
        return antecedents_autres;
    }

    public void setAntecedents_autres(String antecedents_autres) {
        this.antecedents_autres = antecedents_autres;
    }

    public boolean isDiabetique() {
        return diabetique;
    }

    public void setDiabetique(boolean diabetique) {
        this.diabetique = diabetique;
    }

    public boolean isHypertendu() {
        return hypertendu;
    }

    public void setHypertendu(boolean hypertendu) {
        this.hypertendu = hypertendu;
    }

}
