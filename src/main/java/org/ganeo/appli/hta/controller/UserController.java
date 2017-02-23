/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ganeo.appli.hta.controller;


//import com.tsoft.service.MailService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.ganeo.appli.hta.config.Constants;
import org.ganeo.appli.hta.domain.User;
import org.ganeo.appli.hta.repository.UserRepository;
import org.ganeo.appli.hta.service.UserService;
import org.ganeo.appli.hta.util.HeaderUtil;
import org.ganeo.appli.hta.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing users.
 *
 * <p>
 * This class accesses the User entity, and needs to fetch its collection of
 * authorities.</p>
 * <p>
 * For a normal use-case, it would be better to have an eager relationship
 * between User and Authority, and send everything to the client side: there
 * would be no DTO, a lot less code, and an outer-join which would be good for
 * performance.
 * </p>
 * <p>
 * We use a DTO for 3 reasons:
 * <ul>
 * <li>We want to keep a lazy association between the user and the authorities,
 * because people will quite often do relationships with the user, and we don't
 * want them to get the authorities all the time for nothing (for performance
 * reasons). This is the #1 goal: we should not impact our users' application
 * because of this use-case.</li>
 * <li> Not having an outer join causes n+1 requests to the database. This is
 * not a real issue as we have by default a second-level cache. This means on
 * the first HTTP call we do the n+1 requests, but then all authorities come
 * from the cache, so in fact it's much better than doing an outer join (which
 * will get lots of data from the database, for each HTTP call).</li>
 * <li> As this manages users, for security reasons, we'd rather have a DTO
 * layer.</li>
 * </ul>
 * <p>
 * Another option would be to have a specific JPA entity graph to handle this
 * case.</p>
 */
@RestController
@RequestMapping("/api")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private MailService mailService;
   

    @Autowired
    private UserService userService;

    /**
     * POST /users : Creates a new user.
     * <p>
     * Creates a new user if the login and email are not already used, and sends
     * an mail with an activation link. The user needs to be activated on
     * creation.
     * </p>
     *
     * @param managedUserDTO the user to create
     * @param request the HTTP request
     * @return the ResponseEntity with status 201 (Created) and with body the
     * new user, or with status 400 (Bad Request) if the login or email is
     * already in use
     * @throws URISyntaxException if the Location URI syntaxt is incorrect
     */
    @PostMapping(value = "/User",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody User managedUserDTO, HttpServletRequest request) throws URISyntaxException {
        log.debug("REST request to save User : {}", managedUserDTO);

        //Lowercase the user login before comparing with database
        if (userRepository.findOneByUsername(managedUserDTO.getUsername().toLowerCase()).isPresent()) {
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert("userManagement", "userexists", "Login already in use"))
                    .body(null);
        } else if (userRepository.findOneByEmail(managedUserDTO.getEmail()).isPresent()) {
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert("userManagement", "emailexists", "Email already in use"))
                    .body(null);
        } else {
            if (managedUserDTO.getId() != null) {
                //update user
                userRepository.save(managedUserDTO);
            } else {
                User newUser = userService.createUser(managedUserDTO);
                //            mailService.sendCreationEmail(newUser, baseUrl);
            }
            String baseUrl = request.getScheme()
                    + // "http"
                    "://"
                    + // "://"
                    request.getServerName()
                    + // "myhost"
                    ":"
                    + // ":"
                    request.getServerPort()
                    + // "80"
                    request.getContextPath();              // "/myContextPath" or "" if deployed in root context

            return ResponseEntity.created(new URI("/app/User/" + managedUserDTO.getId()))
                    .headers(HeaderUtil.createAlert("userManagement.created", managedUserDTO.getUsername()))
                    .body(managedUserDTO);
        }
    }

    /**
     * PUT /users : Updates an existing User.
     *
     * @param managedUserDTO the user to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated
     * user, or with status 400 (Bad Request) if the login or email is already
     * in use, or with status 500 (Internal Server Error) if the user couldnt be
     * updated
     */
    @RequestMapping(value = "/users",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<User> updateUser(@RequestBody User managedUserDTO) {
        log.debug("REST request to update User : {}", managedUserDTO);
        Optional<User> existingUser = userRepository.findOneByEmail(managedUserDTO.getEmail());
        if (existingUser.isPresent() && (!existingUser.get().getId().equals(managedUserDTO.getId()))) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("userManagement", "emailexists", "E-mail already in use")).body(null);
        }
        existingUser = userRepository.findOneByUsername(managedUserDTO.getUsername().toLowerCase());
        if (existingUser.isPresent() && (!existingUser.get().getId().equals(managedUserDTO.getId()))) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("userManagement", "userexists", "Login already in use")).body(null);
        }
                    return ResponseEntity.ok()
                            .headers(HeaderUtil.createAlert("userManagement.updated", managedUserDTO.getUsername()))
                            .body(userRepository.findOne(managedUserDTO.getId()));
                
               // .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

    }

    /**
     * GET /users : get all users.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and with body all users
     * @throws URISyntaxException if the pagination headers couldnt be generated
     */
    @RequestMapping(value = "/users",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public ResponseEntity<List<User>> getAllUsers(Pageable pageable)
            throws URISyntaxException {
        Page<User> page = userRepository.findAll(pageable);
        List<User> managedUserDTOs = page.getContent().stream()
                .collect(Collectors.toList());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/users");
        return new ResponseEntity<>(managedUserDTOs, headers, HttpStatus.OK);
    }

    /**
     * GET /users/:login : get the "login" user.
     *
     * @param login the login of the user to find
     * @return the ResponseEntity with status 200 (OK) and with body the "login"
     * user, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/users/{login:" + Constants.LOGIN_REGEX + "}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable String login) {
        log.debug("REST request to get User : {}", login);
        return userService.getUserWithAuthoritiesByLogin(login)
                .map(managedUserDTO -> new ResponseEntity<>(managedUserDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE USER :login : delete the "login" User.
     *
     * @param login the login of the user to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/users/{login:" + Constants.LOGIN_REGEX + "}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUser(@PathVariable String login) {
        log.debug("REST request to delete User: {}", login);
        userService.deleteUserInformation(login);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("userManagement.deleted", login)).build();
    }

    /**
     * GET /User : get all the User.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of User in
     * body
     */
    @RequestMapping(value = "/User", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listUser() throws Exception {
        log.debug("REST request to get all User");
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    /**
     * GET User/{id}.
     *
     * @param id the id of the User to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the User,
     * or with status 404 (Not Found)
     */
    @RequestMapping(value = "/User/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> findUser(@PathVariable Long id) throws Exception {
        log.debug("REST request to get User : {}", id);
        User v = userRepository.findOne(id);
        return Optional.ofNullable(v)
                .map(result -> new ResponseEntity<>(
                        result,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
   

}
