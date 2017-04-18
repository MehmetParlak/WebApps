/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mp503.paymentsystem.bean;

import com.mp503.paymentsystem.entity.UserTransactions;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mehmetparlak
 */
@Local
public interface UserTransactionsFacadeLocal {

    void create(UserTransactions userTransactions);

    void edit(UserTransactions userTransactions);

    void remove(UserTransactions userTransactions);

    UserTransactions find(Object id);

    List<UserTransactions> findAll();

    List<UserTransactions> findRange(int[] range);

    int count();
    
}
