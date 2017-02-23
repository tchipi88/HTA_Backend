/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ganeo.appli.hta.repository;

import org.ganeo.appli.hta.domain.CHW;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 *
 * @author tchipi
 */
/**
 * Spring Data JPA repository for the CHW entity.
 */
@SuppressWarnings("unused")
public interface CHWRepository extends JpaRepository<CHW, Long> {

    

}

