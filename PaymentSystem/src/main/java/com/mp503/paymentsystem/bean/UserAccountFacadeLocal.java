/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mp503.paymentsystem.bean;

import com.mp503.paymentsystem.entity.UserAccount;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mehmetparlak
 */
@Local
public interface UserAccountFacadeLocal {

    void create(UserAccount userAccount);

    void edit(UserAccount userAccount);

    void remove(UserAccount userAccount);

    UserAccount find(Object id);

    List<UserAccount> findAll();

    List<UserAccount> findRange(int[] range);

    int count();
    
}
