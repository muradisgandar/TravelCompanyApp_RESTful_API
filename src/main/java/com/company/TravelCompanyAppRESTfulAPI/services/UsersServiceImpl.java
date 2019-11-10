/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.TravelCompanyAppRESTfulAPI.services;

import com.company.TravelCompanyAppRESTfulAPI.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.TravelCompanyAppRESTfulAPI.dao.UsersRepository;
import java.util.List;
import javax.transaction.Transactional;

/**
 *
 * @author murad_isgandar
 */
@Service
@Transactional
public class UsersServiceImpl implements UsersServiceInter {

    @Autowired
    UsersRepository userdao;

    @Override
    public List getAllUsers() {
        List<Users> list = userdao.findAll();
        return list;
    }

    @Override
    public int addUserOtherDetails(Users u) {
        userdao.addUserOtherDetails(u);
        return u.getId();
    }

    @Override
    public int addUsernameAndPassword(Users u) {
        userdao.addUsernameAndPassword(u);
        return u.getId();
    }

    @Override
    public Users setEnable(Integer id) {
//        Optional<Users> opt = userdao.findById(id);  we don't write this code ,beacuse it throws exception if any parameter is null
//        Users u = opt.get();
        Users u = userdao.findUserById(id);
        if(u.isEnabled()){
            u.setEnabled(false);//if enable is true ,admin changes it to false so user can't login app
        }
        else
            u.setEnabled(true);//if enable is false,admin changes it to true,so he gives access for login to user
        
        userdao.save(u);
        
        return u;
    }

}
