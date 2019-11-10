/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.TravelCompanyAppRESTfulAPI.services;

import com.company.TravelCompanyAppRESTfulAPI.entities.Users;
import java.util.List;

/**
 *
 * @author murad_isgandar
 */
public interface UsersServiceInter {
    
    public List getAllUsers();
    
    public Users setEnable(Integer id);

    public int addUserOtherDetails(Users u);

    public int addUsernameAndPassword(Users u);
    
    
    
    
}
