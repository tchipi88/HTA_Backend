package com.tsoft.app.service.dto;

import com.tsoft.app.domain.User;
import com.tsoft.app.domain.enumeration.Profil;
import java.time.Instant;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Email;

/**
 * A DTO representing a user, with his authorities.
 */
public class UserDTO {

    private Long id;

    @Email
    @Size(min = 5, max = 100)
    private String email;

    @Size(max = 50)
    private String prenom;

    @Size(max = 50)
    private String nom;

    private String telephone;

    private boolean activated = false;

    private Profil profil;

    private Instant resetDate;

    public UserDTO() {
        // Empty constructor needed for MapStruct.
    }

    public UserDTO(User user) {
        this(user.getId(), user.getPrenom(), user.getNom(), user.getEmail(),
                user.getActivated(), user.getProfil(), user.getTelephone(), user.getResetDate());
    }

    public UserDTO(Long id, String firstName, String lastName, String email,
            boolean activated, Profil profil, String telephone, Instant resetDate) {

        this.id = id;
        this.prenom = firstName;
        this.nom = lastName;
        this.email = email;
        this.activated = activated;

        this.profil = profil;
        this.telephone = telephone;

        this.resetDate = resetDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Override
    public String toString() {
        return "UserDTO{"
                + ", firstName='" + prenom + '\''
                + ", lastName='" + nom + '\''
                + ", email='" + email + '\''
                + ", activated=" + activated
                + "}";
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public Instant getResetDate() {
        return resetDate;
    }

    public void setResetDate(Instant resetDate) {
        this.resetDate = resetDate;
    }

}
