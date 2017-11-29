/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsoft.app.domain.enumeration;

/**
 *
 * @author tchipnangngansopa
 */
public enum Education {
    
     Primary("Primary school"),Secondary("Secondary school"),High("High school"),University("University");

    private String name="";

    Education(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}
