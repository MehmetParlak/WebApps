/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mp503.paymentsystem.bean;

import com.mp503.paymentsystem.entity.UserDetails;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mehmetparlak
 */
@Local
public interface UserDetailsFacadeLocal {

    void create(UserDetails userDetails);

    void edit(UserDetails userDetails);

    void remove(UserDetails userDetails);

    UserDetails find(Object id);

    List<UserDetails> findAll();

    List<UserDetails> findRange(int[] range);

    int count();
    
}
