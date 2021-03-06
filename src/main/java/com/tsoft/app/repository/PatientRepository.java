/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.tsoft.app.repository;

import com.tsoft.app.domain.Patient;
import com.tsoft.app.domain.enumeration.Risque;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author tchipi
 */
/**
 * Spring Data JPA repository for the Patient entity.
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("select p from Patient p where p.chw.email= ?#{ principal?.username }")
    public Page<Patient> findAllByChw(Pageable pageable);


    @Query("select p from Patient p where p.chw.medecin.email= ?#{ principal?.username }")
    public Page<Patient> findAllByMEdecin(Pageable pageable);

    @Query("select count(p) from Patient p where p.chw.medecin.email= ?#{ principal?.username }")
    public Long countByMEdecin();


    public Long countByCreatedDateBetween(ZonedDateTime fromDate, ZonedDateTime toDate);

    public Long countByCreatedDateBetweenAndCreatedBy(ZonedDateTime fromDate, ZonedDateTime toDate, String currentUserLogin);

    public Long countByCreatedDateBetweenAndCvdRisk(ZonedDateTime fromDate, ZonedDateTime toDate, Risque risque);

    public Long countByCreatedDateBetweenAndCreatedByAndCvdRisk(ZonedDateTime fromDate, ZonedDateTime toDate, String currentUserLogin,
            Risque risque);

    public long countByDateLastConsultationBetween(LocalDate fromDate, LocalDate toDate);

    public long countByDateLastConsultationBetweenAndCreatedBy(LocalDate fromDate, LocalDate toDate, String currentUserLogin);

    @Query("select p from Patient p where  p.bloodpressureTreatment=true  and p.chw.email= ?#{ principal?.username }")
    public Page<Patient> findAllByChwIsTreatement(Pageable pageable);

    @Query("select p from Patient p where p.bloodpressureTreatment=true  and  p.chw.medecin.email= ?#{ principal?.username }")
    public Page<Patient> findAllByMEdecinIsTreatement(Pageable pageable);

    public Page<Patient> findAllByBloodpressureTreatment(boolean b, Pageable pageable);


    public Page<Patient> findAllByChwEmailOrderByCvdRisk(String currentUserLogin, Pageable pageable);

    public Page<Patient> findAllByChwMedecinEmailOrderByCvdRisk(String currentUserLogin, Pageable pageable);

    public Page<Patient> findAllOrderByCvdRisk(Pageable pageable);

    public Page<Patient> findAllByChwEmail(String currentUserLogin, Pageable pageable);

    public Page<Patient> findAllByChwMedecinEmail(String currentUserLogin, Pageable pageable);

    //filtre  cvdRisk
    @Query("select p from Patient p where p.cvdRisk!='LOW'")
    public Page<Patient> findAllByHighRisk(Pageable pageable);

    @Query("select p from Patient p where  p.cvdRisk!='LOW'  and p.chw.email= ?#{ principal?.username }")
    public Page<Patient> findAllByChwHighRisk(Pageable pageable);

    @Query("select p from Patient p where p.cvdRisk!='LOW'  and  p.chw.medecin.email= ?#{ principal?.username }")
    public Page<Patient> findAllByMedecinHighRisk(Pageable pageable);

    @Query("select count(p) from Patient p where p.cvdRisk='HIGH'  and  p.chw.medecin.email= ?#{ principal?.username }")
    public Long countByMEdecinHighRisk();

    //filtre  Recommandation

    @Query("select p from Patient p where p.recommandationVisitDoctor!='BP_TBP'  and  p.chw.medecin.email= ?#{ principal?.username }")
    public Page<Patient> findAllByMedecinRecommandationVisitDoctor(Pageable pageable);

    @Query("select p from Patient p where  p.recommandationVisitDoctor!='BP_TBP'  and p.chw.email= ?#{ principal?.username }")
    public Page<Patient> findAllByChwRecommandationVisitDoctor(Pageable pageable);

    @Query("select p from Patient p where p.recommandationVisitDoctor!='BP_TBP'")
    public Page<Patient> findAllByRecommandationVisitDoctor(Pageable pageable);

    //monitoring Blood Pressure

    public Page<Patient> findAllByBloodpressureTomesure(boolean b, Pageable pageable);

    @Query("select p from Patient p where  p.bloodpressureTomesure=true  and p.chw.email= ?#{ principal?.username }")
    public Page<Patient> findAllByChwBloodpressureTomesure(Pageable pageable);

    @Query("select p from Patient p where p.bloodpressureTomesure=true  and  p.chw.medecin.email= ?#{ principal?.username }")
    public Page<Patient> findAllByMedecinBloodpressureTomesure(Pageable pageable);

}
