/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ganeo.appli.hta.model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tchipnangngansopa
 */
@Entity
public class Video extends AbstractAuditingEntity {
   @NotNull
   @ManyToOne
    private Theme theme;
    @NotNull
    private String titre;
    @Lob
    private String description;
    @NotNull
    private String auteur;
    private Short duree;
    private String lien;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Short getDuree() {
        return duree;
    }

    public void setDuree(Short duree) {
        this.duree = duree;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
    
    

}
