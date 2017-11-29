/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsoft.app.init;

import com.tsoft.app.domain.Chw;
import com.tsoft.app.domain.Medecin;
import com.tsoft.app.domain.Patient;
import com.tsoft.app.domain.enumeration.Gender;
import com.tsoft.app.domain.enumeration.Risque;
import com.tsoft.app.repository.PatientRepository;
import com.tsoft.app.service.ChwService;
import com.tsoft.app.service.MedecinService;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author tchipi
 */
@Component
public class SecurityDemoData implements DemoData {

    @Autowired
    MedecinService medecinService;
    @Autowired
    ChwService chwService;
    @Autowired
    PatientRepository patientWeightRepository;

    @Override
    public void populateData(HttpServletRequest req) throws Exception {
        DataFactory df = new DataFactory();

        for (int i = 1; i <= 3; i++) {
            Medecin medecin = new Medecin();
            medecin.setEmail(i + df.getEmailAddress());
            medecin.setNom(df.getLastName());
            medecin.setTown(df.getCity());
            medecinService.createMedecin(medecin);

            for (int j = 1; j <= 3; j++) {
                Chw chw = new Chw();
                chw.setMedecin(medecin);
                chw.setEmail((j * 100) + df.getEmailAddress());
                chw.setNom(df.getLastName());
                chw.setTown(df.getCity());
                chwService.createChw(chw);
                for (int n = 1; n <= 15; n++) {
                    Patient p = new Patient();
                    p.setLastName(df.getLastName());
                    p.setFistName(df.getFirstName());
                    p.setBirthDay(LocalDate.now().minusYears(n));
                    p.setBirthPlace(df.getCity());
                    p.setGender(n % 2 == 0 ? Gender.F : Gender.M);
                    p.setTown(n % 2 == 0 ? "Yaounde" : "Douala");
                    p.setTelephone("679994949");
                    p.setEmail(df.getEmailAddress());
                    p.setDistrict(df.getAddress());
                    p.setHeight(df.getNumberBetween(159, 200));
                    p.setWeight(75.5F);

                    p.setCvdRisk(Risque.MEDIUM);

                    p.setPaDiastolique(df.getNumberBetween(100, 280));
                    p.setPaSystolique(df.getNumberBetween(100, 140));
                    p.setSmoker((n % 2 == 0));
                    p.setDiabetique((n % 2 == 0));
                    p.setDiabetesFamily((n % 2 == 0));
                    p.setHypertension((n % 2 == 0));
                    p.setHypertensionFamily((n % 2 == 0));
                    p.setHeartCardiacFamily((n % 2 == 0));
                    p.setHeartAttack((n % 2 == 0));

                    p.setChw(chw);

                    patientWeightRepository.save(p);

                }

            }

        }

    }

}
