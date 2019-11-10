/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.TravelCompanyAppRESTfulAPI.controller;

import com.company.TravelCompanyAppRESTfulAPI.dto.ResponseDTO;
import com.company.TravelCompanyAppRESTfulAPI.entities.Travelpackages;
import com.company.TravelCompanyAppRESTfulAPI.services.TravelPackagesServiceInter;
import com.company.TravelCompanyAppRESTfulAPI.services.UsersServiceInter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author murad_isgandar
 */
//HTTP Status Codes
/*For Success operations:
200-All tour packets
201-Available packets are found(for search operation)
202-Successfully added
203-Successfully updated 
204-Selected item successfully deleted
 */
 /*For Fail operations:
300-Add operation is failed
301-Update operation is failed
302-Delete operation is failed
 */
@RestController
public class AdminController {

    @Autowired
    TravelPackagesServiceInter service;

    @Autowired
    UsersServiceInter userservice;

    @RequestMapping("/adminpage")
    public ResponseEntity getAll() {
        List<Travelpackages> list = service.getAll();

        if (list != null && !list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("All tour packets", 200, list));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("No available packets are found"));
        }

    }

    @GetMapping("/adminpage/search")
    public ResponseEntity search(@RequestParam(value = "countryname", required = false) String countryname,
            @RequestParam(value = "date", required = false) String date,
            Model model) {
        List<Travelpackages> list = service.getAllByParameters(countryname, date);

        if (list != null && !list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("Available packets are found", 201, list));
        } else {

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("No available packets are found"));
        }

    }

    @PostMapping("/adminpage/add")
    public ResponseEntity add(@RequestParam(value = "countryname") String countryname,
            @RequestParam(value = "date") String date
    ) {
        try {
            Travelpackages t = new Travelpackages();

            t.setCountryname(countryname);
            t.setDate(date);
            if (service.add(t)) {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("Successfully added", 202, t));
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("Add operation is failed", 300));
        }
    }

    @PutMapping("/adminpage/update")
    public ResponseEntity update(@RequestParam(value = "id") Integer id,
            @RequestParam(value = "countryname", required = false) String countryname,
            @RequestParam(value = "date", required = false) String date
    ) {

        try {
            Travelpackages t = new Travelpackages();

            t.setId(id);
            t.setCountryname(countryname);
            t.setDate(date);
            if (service.update(t)) {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("Successfully updated", 203, t));
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("Update operation is failed", 301));
        }

    }

    @DeleteMapping("/adminpage/delete")
    public ResponseEntity delete(@RequestParam(value = "id", required = false) Integer id) {
        try {
            if (service.delete(id)) {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("Selected item successfully deleted", 204, id));
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("Delete operation is failed", 302));
        }

    }

}
