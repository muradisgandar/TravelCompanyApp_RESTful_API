/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.TravelCompanyAppRESTfulAPI.controller;

import com.company.TravelCompanyAppRESTfulAPI.dto.ResponseDTO;
import com.company.TravelCompanyAppRESTfulAPI.entities.Travelpackages;
import com.company.TravelCompanyAppRESTfulAPI.entities.Users;
import com.company.TravelCompanyAppRESTfulAPI.services.TravelPackagesServiceInter;
import com.company.TravelCompanyAppRESTfulAPI.services.UsersServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
public class UserController {

    @Autowired
    UsersServiceInter userservice;

    @Autowired
    TravelPackagesServiceInter travelservice;

    @PostMapping("/userpage/add")
    public ResponseEntity userAdd(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "surname") String surname,
            @RequestParam(value = "age") Integer age,
            @RequestParam(value = "gender") String gender,
            @RequestParam(value = "phoneNumber") String phoneNumber,
            @RequestParam(value = "mail") String mail,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "tour_id") Travelpackages tourId
    ) {

        try {
            int id = userservice.addUserOtherDetails(new Users(null, name, surname, age, gender, phoneNumber, mail, username, password, true, tourId));

            if (id > 0) {
                Users users = new Users(id, name, surname, age, gender, phoneNumber, username, mail, password, true, tourId);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("Information successfully added", 202, users));
            } else {
                throw new Exception();
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("Process is failed!", 203));
        }

    }

}
