/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.TravelCompanyAppRESTfulAPI.dao;

import com.company.TravelCompanyAppRESTfulAPI.entities.Travelpackages;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author murad_isgandar
 */
public interface TravelPackagesRepository extends JpaRepository<Travelpackages, Integer>, TravelPackagesRepositoryCustom {
    
//    @Query("update Travelpackages t set t.countryname = t.countryname or t.date :date where t.id :id")
//    public boolean updatePackage(Travelpackages t);
    
//    @Query(value = "select t from Travelpackages t where t.countryname = ?1 or t.date = ?2")
//    public List<Travelpackages> getAllByParameters(String countryname, String date);
    
    
}
