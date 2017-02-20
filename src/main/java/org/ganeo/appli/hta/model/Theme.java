/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ganeo.appli.hta.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tchipnangngansopa
 */
@Entity
public class Theme extends AbstractAuditingEntity{
   @NotNull
   private String libelle;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
   
   
}
