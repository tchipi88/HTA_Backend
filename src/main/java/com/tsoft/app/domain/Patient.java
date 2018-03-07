/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsoft.app.domain;

import com.tsoft.app.domain.enumeration.Education;
import com.tsoft.app.domain.enumeration.Gender;
import com.tsoft.app.domain.enumeration.RecommandationFrequenceProgramSuvi;
import com.tsoft.app.domain.enumeration.RecommandationVisitDoctor;
import com.tsoft.app.domain.enumeration.Risque;
import com.tsoft.app.domain.enumeration.StatutMarital;
import com.tsoft.app.service.template.Libelle;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 *
 * @author tchipnangngansopa
 */
@Entity
@Document(indexName = "patient")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Patient extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Libelle
    private String lastName;

    private String firstName;

    @Enumerated
    @NotNull
    private Gender gender;
    @NotNull
    private LocalDate birthDay;

    private String birthPlace;

    private String telephone;

    private String email;

    private String town;

    private String district;

    @Enumerated
    private StatutMarital statutMarital;
    private String job;

    @Enumerated
    private Education education;

    private boolean pregnant;

    private boolean heartCardiacFamily;
    private boolean diabetesFamily;
    private boolean hypertensionFamily;

    private boolean bloodpressureMesured;
    private LocalDate dateLastBloodpressureMesured;
    private LocalDate dateNextBloodpressureMesure;
    private boolean bloodpressureTreatment;
    private boolean bloodpressureTomesure;

    private boolean hypertension;
    private boolean diabetique;
    private boolean heartAttack;
    private boolean stroke;

    private String lastDoctorVisit;

    private Integer height;

    private Float weight;

    private boolean drinkAlcohol;
    private String frequencyDrinkAlcohol;

    @NotNull
    @ManyToOne
    private Chw chw;

    private boolean smoker;
    private String frequencySmokeCigarettes;

    private Integer paDiastolique;
    private Integer paSystolique;

    //Recommandation
    @Enumerated(EnumType.STRING)
    private Risque cvdRisk;

    //programme hygieno dietetique
    private boolean recommandationlifesytleAdvice;
    @Enumerated
    private RecommandationFrequenceProgramSuvi recommandationFrequenceProgramSuvi;
    @Enumerated(EnumType.STRING)
    private RecommandationVisitDoctor recommandationVisitDoctor;

    private LocalDate dateLastConsultation;
    private boolean consulte;
    private boolean diagnosticHypertension;
    private boolean diagnosticPrescriptionMedicale;

    private LocalDate dateNextConsultation;

    private LocalDate dateFirstBilan;

    private LocalDate dateLastBilan;

    private boolean bilanChecked;

    private boolean defineTarget;

    private LocalDate dateLastDefineTarget;

    private boolean monitoringTreatment;

    public Patient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public StatutMarital getStatutMarital() {
        return statutMarital;
    }

    public void setStatutMarital(StatutMarital statutMarital) {
        this.statutMarital = statutMarital;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public boolean isPregnant() {
        return pregnant;
    }

    public void setPregnant(boolean pregnant) {
        this.pregnant = pregnant;
    }

    public boolean isHeartCardiacFamily() {
        return heartCardiacFamily;
    }

    public void setHeartCardiacFamily(boolean heartCardiacFamily) {
        this.heartCardiacFamily = heartCardiacFamily;
    }

    public boolean isDiabetesFamily() {
        return diabetesFamily;
    }

    public void setDiabetesFamily(boolean diabetesFamily) {
        this.diabetesFamily = diabetesFamily;
    }

    public boolean isHypertensionFamily() {
        return hypertensionFamily;
    }

    public void setHypertensionFamily(boolean hypertensionFamily) {
        this.hypertensionFamily = hypertensionFamily;
    }

    public boolean isBloodpressureMesured() {
        return bloodpressureMesured;
    }

    public void setBloodpressureMesured(boolean bloodpressureMesured) {
        this.bloodpressureMesured = bloodpressureMesured;
    }

    public LocalDate getDateLastBloodpressureMesured() {
        return dateLastBloodpressureMesured;
    }

    public void setDateLastBloodpressureMesured(LocalDate lastDateBloodPressureMesured) {
        this.dateLastBloodpressureMesured = lastDateBloodPressureMesured;
    }

    public boolean isBloodpressureTreatment() {
        return bloodpressureTreatment;
    }

    public void setBloodpressureTreatment(boolean bloodpressureTreatment) {
        this.bloodpressureTreatment = bloodpressureTreatment;
    }

    public boolean isHypertension() {
        return hypertension;
    }

    public void setHypertension(boolean hypertension) {
        this.hypertension = hypertension;
    }

    public boolean isDiabetique() {
        return diabetique;
    }

    public void setDiabetique(boolean diabetique) {
        this.diabetique = diabetique;
    }

    public boolean isHeartAttack() {
        return heartAttack;
    }

    public void setHeartAttack(boolean heartAttack) {
        this.heartAttack = heartAttack;
    }

    public boolean isSmoker() {
        return smoker;
    }

    public void setSmoker(boolean smoker) {
        this.smoker = smoker;
    }

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

    public Risque getCvdRisk() {
        return cvdRisk;
    }

    public void setCvdRisk(Risque cvdRisk) {
        this.cvdRisk = cvdRisk;
    }

    public boolean isRecommandationlifesytleAdvice() {
        return recommandationlifesytleAdvice;
    }

    public void setRecommandationlifesytleAdvice(boolean recommandationlifesytleAdvice) {
        this.recommandationlifesytleAdvice = recommandationlifesytleAdvice;
    }

    public RecommandationFrequenceProgramSuvi getRecommandationFrequenceProgramSuvi() {
        return recommandationFrequenceProgramSuvi;
    }

    public void setRecommandationFrequenceProgramSuvi(RecommandationFrequenceProgramSuvi recommandationFrequenceProgramSuvi) {
        this.recommandationFrequenceProgramSuvi = recommandationFrequenceProgramSuvi;
    }

    public RecommandationVisitDoctor getRecommandationVisitDoctor() {
        return recommandationVisitDoctor;
    }

    public void setRecommandationVisitDoctor(RecommandationVisitDoctor recommandationVisitDoctor) {
        this.recommandationVisitDoctor = recommandationVisitDoctor;
    }

    public boolean isStroke() {
        return stroke;
    }

    public void setStroke(boolean stroke) {
        this.stroke = stroke;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public boolean isDrinkAlcohol() {
        return drinkAlcohol;
    }

    public void setDrinkAlcohol(boolean drinkAlcohol) {
        this.drinkAlcohol = drinkAlcohol;
    }

    public Chw getChw() {
        return chw;
    }

    public void setChw(Chw chw) {
        this.chw = chw;
    }

    public LocalDate getDateLastConsultation() {
        return dateLastConsultation;
    }

    public void setDateLastConsultation(LocalDate dateLastConsultation) {
        this.dateLastConsultation = dateLastConsultation;
    }

    public LocalDate getDateLastDefineTarget() {
        return dateLastDefineTarget;
    }

    public void setDateLastDefineTarget(LocalDate dateLastDefineTarget) {
        this.dateLastDefineTarget = dateLastDefineTarget;
    }

    public String getFrequencyDrinkAlcohol() {
        return frequencyDrinkAlcohol;
    }

    public void setFrequencyDrinkAlcohol(String frequencyDrinkAlcohol) {
        this.frequencyDrinkAlcohol = frequencyDrinkAlcohol;
    }

    public String getFrequencySmokeCigarettes() {
        return frequencySmokeCigarettes;
    }

    public void setFrequencySmokeCigarettes(String frequencySmokeCigarettes) {
        this.frequencySmokeCigarettes = frequencySmokeCigarettes;
    }

    public LocalDate getDateNextConsultation() {
        return dateNextConsultation;
    }

    public void setDateNextConsultation(LocalDate dateNextConsultation) {
        this.dateNextConsultation = dateNextConsultation;
    }

    public LocalDate getDateFirstBilan() {
        return dateFirstBilan;
    }

    public void setDateFirstBilan(LocalDate dateFirstBilan) {
        this.dateFirstBilan = dateFirstBilan;
    }

    public LocalDate getDateLastBilan() {
        return dateLastBilan;
    }

    public void setDateLastBilan(LocalDate dateLastBilan) {
        this.dateLastBilan = dateLastBilan;
    }

    public boolean isBilanChecked() {
        return bilanChecked;
    }

    public void setBilanChecked(boolean bilanChecked) {
        this.bilanChecked = bilanChecked;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public boolean isConsulte() {
        return consulte;
    }

    public void setConsulte(boolean consulte) {
        this.consulte = consulte;
    }

    public boolean isDiagnosticHypertension() {
        return diagnosticHypertension;
    }

    public void setDiagnosticHypertension(boolean diagnosticHypertension) {
        this.diagnosticHypertension = diagnosticHypertension;
    }

    public boolean isDiagnosticPrescriptionMedicale() {
        return diagnosticPrescriptionMedicale;
    }

    public void setDiagnosticPrescriptionMedicale(boolean diagnosticPrescriptionMedicale) {
        this.diagnosticPrescriptionMedicale = diagnosticPrescriptionMedicale;
    }

    public boolean isDefineTarget() {
        return defineTarget;
    }

    public void setDefineTarget(boolean defineTarget) {
        this.defineTarget = defineTarget;
    }

    public String getLastDoctorVisit() {
        return lastDoctorVisit;
    }

    public void setLastDoctorVisit(String lastDoctorVisit) {
        this.lastDoctorVisit = lastDoctorVisit;
    }

    public boolean isMonitoringTreatment() {
        return monitoringTreatment;
    }

    public void setMonitoringTreatment(boolean monitoringTreatment) {
        this.monitoringTreatment = monitoringTreatment;
    }

    public boolean isBloodpressureTomesure() {
        return bloodpressureTomesure;
    }

    public void setBloodpressureTomesure(boolean bloodpressureTomesure) {
        this.bloodpressureTomesure = bloodpressureTomesure;
    }

    public LocalDate getDateNextBloodpressureMesure() {
        return dateNextBloodpressureMesure;
    }

    public void setDateNextBloodpressureMesure(LocalDate dateNextBloodpressureMesure) {
        this.dateNextBloodpressureMesure = dateNextBloodpressureMesure;
    }


}
