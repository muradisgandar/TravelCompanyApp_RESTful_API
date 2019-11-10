/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.TravelCompanyAppRESTfulAPI.dto;

import com.company.TravelCompanyAppRESTfulAPI.entities.Travelpackages;
import lombok.Data;

/**
 *
 * @author murad_isgandar
 */
@Data
public class UserDTO {

    private Integer id;
    private String username;
    private String mail;
    private String password;

    public UserDTO() {
    }

    public UserDTO(Integer id, String username, String mail, String password) {
        this.id = id;
        this.username = username;
        this.mail = mail;
        this.password = password;
    }

}
