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
public class PatientRecommandation extends AbstractAuditingEntity{
    
    @NotNull
    @ManyToOne
    private Patient patient;
    
    @NotNull
    private LocalDate date_recommandation;
    
    @NotNull
    private Risque risque;
}
