/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mp503.payment.ejb;

import com.mp503.payment.entity.SystemUser;
import com.mp503.payment.entity.SystemUserGroup;
import javax.ejb.Local;

/**
 *
 * @author mehmetparlak
 */
@Local
public interface SystemServiceLocal {

    public void printHello();

    public void registerAdmin(String username, String password);

    /**
     *
     * @param username
     * @param password
     * @param firstname
     * @param surname
     * @param email
     * @param currency
     */
    public void registerCustomer(String username, String password, String firstname, String surname, String email, double balance, String currency);

    //public void createAdmin(String username, String password);

    public String securepassword(String password);

    public SystemUser getUserByName(String name);

    public String getUserGroupByName(String name);
}
