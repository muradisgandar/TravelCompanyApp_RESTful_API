/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.TravelCompanyAppRESTfulAPI.services;

import com.company.TravelCompanyAppRESTfulAPI.entities.Travelpackages;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.TravelCompanyAppRESTfulAPI.dao.TravelPackagesRepository;
import javax.transaction.Transactional;

/**
 *
 * @author murad_isgandar
 */
@Service
@Transactional
public class TravelPackagesServiceimpl implements TravelPackagesServiceInter{

    @Autowired
    TravelPackagesRepository customdao;
    
    @Override
    public List<Travelpackages> getAll() {
        List<Travelpackages> result = customdao.findAll();
        return result;
    }

    @Override
    public List<Travelpackages> getAllByParameters(String countryname, String date) {
        List<Travelpackages> result = customdao.getAllByParameters(countryname, date);
        return result;
    }

    @Override
    public boolean update(Travelpackages t) {
        customdao.update(t);
        return true;
    }

    @Override
    public boolean add(Travelpackages t) {
        customdao.save(t);
        return true;
    }

    @Override
    public boolean delete(int id) {
        customdao.deleteById(id);
        return true;
    }
    
    
}
