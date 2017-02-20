/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ganeo.appli.hta.config;

/**
 *
 * @author tchipnangngansopa
 */
public final class Constants {
  //Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";
    // Spring profile for development and production, see http://jhipster.github.io/profiles/
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";  
    
     public static final String SYSTEM_ACCOUNT = "system";
    public static final String SPRING_PROFILE_CLOUD="cloud";

    private Constants() {
    }
}
