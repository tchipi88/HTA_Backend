package com.tsoft.app.web.rest.vm;

import com.tsoft.app.domain.enumeration.Profil;
import com.tsoft.app.service.dto.UserDTO;
import javax.validation.constraints.Size;


/**
 * View Model extending the UserDTO, which is meant to be used in the user
 * management UI.
 */
public class ManagedUserVM extends UserDTO {

    public static final int PASSWORD_MIN_LENGTH = 4;

    public static final int PASSWORD_MAX_LENGTH = 100;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    public ManagedUserVM() {
        // Empty constructor needed for Jackson.
    }

    public ManagedUserVM(Long id, String password, String firstName, String lastName,
            String email, boolean activated,
            Profil profil, String telephone) {

        super(id, firstName, lastName, email, activated,
                profil, telephone);

        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "ManagedUserVM{"
                + "} " + super.toString();
    }
}
