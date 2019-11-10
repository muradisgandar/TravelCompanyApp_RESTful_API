/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.TravelCompanyAppRESTfulAPI.controller;

import com.company.TravelCompanyAppRESTfulAPI.dto.ResponseDTO;
import com.company.TravelCompanyAppRESTfulAPI.dto.UserDTO;
import com.company.TravelCompanyAppRESTfulAPI.entities.Users;
import com.company.TravelCompanyAppRESTfulAPI.services.AuthoritiesServiceInter;
import com.company.TravelCompanyAppRESTfulAPI.services.UsersServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author murad_isgandar
 */

//HTTP status codes:
/*
202-Information successfully added
203-Process is failed!
 */

@RestController
public class LoginAndRegistration {

    @Autowired
    UsersServiceInter service;

    @Autowired
    AuthoritiesServiceInter authoritiesService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity userRegister(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "mail") String mail) {

        try {
            Users u = new Users();

            u.setUsername(username);
            u.setMail(mail);
            u.setPassword(password);

            u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));//for encoding user password then addUserOtherDetails db
            u.setEnabled(true);
            int id = service.addUsernameAndPassword(u);

            if (id > 0) {
                authoritiesService.addAuthority(u);
                UserDTO userdto = new UserDTO(id, username, mail, password);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("User successfully registered", 202, userdto));
            } else {
                throw new Exception();
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("Process is failed!", 203));
        }

    }

}
