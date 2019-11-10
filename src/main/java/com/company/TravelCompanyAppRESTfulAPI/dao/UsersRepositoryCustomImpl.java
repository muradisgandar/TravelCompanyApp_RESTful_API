/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.TravelCompanyAppRESTfulAPI.dao;

import com.company.TravelCompanyAppRESTfulAPI.entities.Authorities;
import com.company.TravelCompanyAppRESTfulAPI.entities.Travelpackages;
import com.company.TravelCompanyAppRESTfulAPI.entities.Users;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author murad_isgandar
 */
@Repository
public class UsersRepositoryCustomImpl implements UsersRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public int addUserOtherDetails(Users u) {
        //firstly select user from db where detached(which is set in db) object's username matches transient(which will sent) object's username
        Query query = em.createQuery("select u from Users u where u.username =:username");
        query.setParameter("username", u.getUsername());
        List<Users> list = query.getResultList();
        for (Users us : list) {
            u.setId(us.getId());//next set detached object's id to transient object's id field
        }

        u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));//next password is encoded
        em.merge(u);//transient object is merged where it's id matches detached object's object

        /*
        ATTENTION!!!
        Here .persist() only will insert the record.(that's why we don't use it)
        If we use .merge() it will check is there any record exist with the current ID,
        If it exists, it will update otherwise it will insert a new record.
         */
        return u.getId();
    }

    @Override
    public int addUsernameAndPassword(Users u) {
        em.persist(u);
        return u.getId();
    }

}
