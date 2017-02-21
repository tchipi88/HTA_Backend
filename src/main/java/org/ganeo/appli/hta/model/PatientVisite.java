/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ganeo.appli.hta.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tchipnangngansopa
 */
@Entity
public class PatientVisite extends AbstractAuditingEntity{
    @NotNull
    @ManyToOne
    private Patient patient;
    
    @NotNull
    @ManyToOne
    private Medecin medecin;
    
    @NotNull
    private LocalDate date_visite;
    
    @Lob
    private String observation;
}
