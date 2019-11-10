/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.TravelCompanyAppRESTfulAPI.dao;

import com.company.TravelCompanyAppRESTfulAPI.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author murad_isgandar
 */
public interface UsersRepository extends JpaRepository<Users, Integer> ,UsersRepositoryCustom{
    
    public Users findUserByUsername(String username);
    
    public Users findUserById(Integer id);
    
}
