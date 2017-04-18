/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mp503.paymentsystem.bean;

import com.mp503.paymentsystem.entity.UserTransactions;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mehmetparlak
 */
@Stateless
public class UserTransactionsFacade extends AbstractFacade<UserTransactions> implements UserTransactionsFacadeLocal {

    @PersistenceContext(unitName = "com.mp503_PaymentSystem_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserTransactionsFacade() {
        super(UserTransactions.class);
    }
    
}
