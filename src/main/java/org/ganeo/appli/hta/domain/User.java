/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ganeo.appli.hta.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.ganeo.appli.hta.config.Constants;
import org.hibernate.annotations.NaturalId;

/**
 *
 * @author tchipnangngansopa
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "profil", discriminatorType = DiscriminatorType.STRING)
public class User extends Personne {

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    @NaturalId
    private String username;

    @NotNull
    @JsonIgnore
    private String password;

    @NotNull
    private boolean activated = false;

    private ZonedDateTime lastAccessDate = null;

    @Size(max = 20)
    @Column(name = "reset_key", length = 20)
    private String resetKey;

    @Column(name = "reset_date", nullable = true)
    private ZonedDateTime resetDate = null;
    @Column
    private byte loginAttempts;

    @Enumerated
    private Language langKey;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Language getLangKey() {
        return langKey;
    }

    public void setLangKey(Language langKey) {
        this.langKey = langKey;
    }

    public ZonedDateTime getLastAccessDate() {
        return lastAccessDate;
    }

    public void setLastAccessDate(ZonedDateTime lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public ZonedDateTime getResetDate() {
        return resetDate;
    }

    public void setResetDate(ZonedDateTime resetDate) {
        this.resetDate = resetDate;
    }

    public byte getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(byte loginAttempts) {
        this.loginAttempts = loginAttempts;
    }
    

}
