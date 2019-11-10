/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.TravelCompanyAppRESTfulAPI.dao;

import com.company.TravelCompanyAppRESTfulAPI.entities.Travelpackages;
import java.util.List;

/**
 *
 * @author murad_isgandar
 */
public interface TravelPackagesRepositoryCustom {

    public List<Travelpackages> getAll();

    public List<Travelpackages> getAllByParameters(String countryname,String date);

    public boolean update(Travelpackages t);

    public boolean add(Travelpackages t);

    public boolean delete(int id);
}
