/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.TravelCompanyAppRESTfulAPI.dao;

import com.company.TravelCompanyAppRESTfulAPI.entities.Travelpackages;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author murad_isgandar
 */
@Repository
public class TravelPackagesRepositoryCustomImpl implements TravelPackagesRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Travelpackages> getAll() {
        Query query = em.createQuery("select t from Travelpackages t");
        List<Travelpackages> result = query.getResultList();
        return result;

    }

    @Override
    public List<Travelpackages> getAllByParameters(String countryname, String date) {
        if ((countryname == null || countryname.isEmpty()) && (date == null || date.isEmpty())) {
            return getAll();
        }
        
        //request params are empty,that is why, we assign them to null 
//        if(date.isEmpty()){
//            date=null;
//        }
//        if(countryname.isEmpty()){
//            countryname=null;
//        }
        
        Query query = em.createQuery("select t from Travelpackages t where t.countryname like :name or t.date like :date");
        query.setParameter("name", "%" + countryname + "%");
        query.setParameter("date", "%" + date + "%");

        System.out.println("countryname  "+countryname);
        System.out.println("date  "+date);
        List<Travelpackages> result = query.getResultList();
        if (result.size() > 0) {
            return result;
        } else {
            return null;
        }

    }

    @Override
    public boolean update(Travelpackages t) {
        StringBuilder stb = new StringBuilder("update Travelpackages t set t.countryname = t.countryname");

        if (t.getCountryname() != null && !t.getCountryname().isEmpty()) {
            stb.append(",t.countryname = :name");
        }
        if (t.getDate() != null && !t.getDate().isEmpty()) {
            stb.append(",t.date = :date");
        }

        stb.append(" where t.id = :id");
        Query query = em.createQuery(stb.toString());
        if (t.getCountryname() != null && !t.getCountryname().isEmpty()) {
            query.setParameter("name", t.getCountryname());
        }
        if (t.getDate() != null && !t.getDate().isEmpty()) {
            query.setParameter("date", t.getDate());
        }

        query.setParameter("id", t.getId());
        query.executeUpdate();
        return true;

    }

    @Override
    public boolean add(Travelpackages t) {
        if ((t.getCountryname() != null && !t.getCountryname().isEmpty()) && (t.getDate() != null && !t.getDate().isEmpty())) {
            em.persist(t);
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        Travelpackages t = em.find(Travelpackages.class, id);
        em.remove(t);
        return true;
    }

}
