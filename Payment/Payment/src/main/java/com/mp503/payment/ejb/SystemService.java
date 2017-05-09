/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mp503.payment.ejb;

import com.mp503.payment.entity.Customers;
import com.mp503.payment.entity.SystemUser;
import com.mp503.payment.entity.SystemUserGroup;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mehmetparlak
 */
@Stateless
@TransactionAttribute(NOT_SUPPORTED)
public class SystemService implements SystemServiceLocal {

    @PersistenceContext(unitName = "PaymentPU")
    EntityManager em;

    @Override
    public void printHello() {

        System.out.println("**********HELLO MEHMET PARLAK**********");
    }

    /**
     *
     * @param username
     * @param password
     */
    /*@TransactionAttribute(NOT_SUPPORTED)
    @Override
    public void createAdmin(String username, String password) {
        SystemUser user = new SystemUser(username, password);
        SystemUserGroup usergroup = new SystemUserGroup(username, "Admin");
        em.persist(user);
        em.persist(usergroup);

    }*/
    @RolesAllowed("Admin")
    @TransactionAttribute(REQUIRED)
    @Override
    public void registerAdmin(String username, String password) {
        SystemUser user = new SystemUser(username, password);
        user.setUserpassword(securepassword(password));
        SystemUserGroup usergroup = new SystemUserGroup(username, "Admin");
        em.persist(user);
        em.persist(usergroup);

    }

    @TransactionAttribute(REQUIRED)
    @Override
    public void registerCustomer(String username, String password, String firstname, String surname, String email, double balance, String currency) {

        SystemUser user = new SystemUser(username, password);
        user.setUserpassword(securepassword(password));
        SystemUserGroup usergroup = new SystemUserGroup(username, "Customer");
        Customers customers = new Customers(firstname, surname, email, balance, currency, user.getUsername());
        em.persist(user);
        em.persist(usergroup);
        em.persist(customers);

    }

    @Override
    public String securepassword(String password) {
        String securePassword = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes("UTF-8"));
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String userPassword = bigInt.toString(16);
            while (userPassword.length() < 64) {
                userPassword = "0" + userPassword;
            }
            securePassword = userPassword;

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            //Logger.getLogger(CreateAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return securePassword;
    }

    @Override
    public SystemUser getUserByName(String name) {
        return (SystemUser) em.createNamedQuery("SystemUser.findByUsername").setParameter("username", name).getSingleResult();
    }

    @Override
    public String getUserGroupByName(String name) {
        SystemUserGroup ug = (SystemUserGroup) em.createNamedQuery("SystemUserGroup.findByUsername").setParameter("username", name).getSingleResult();
        System.out.println("-------------MEHMET PARLAK---------------");
        System.out.println("-------------" + ug.getGroupname() + "---------------");
        System.out.println("-------------MEHMET PARLAK---------------");

        return ug.getGroupname();
    }

    public int validateUser(String name, String email) {

        
        System.out.println("BEN SSENIN TAAAAA");
        System.out.println("USERNAME: "+name+" EMAIL: "+email);
        int isValidate = 0;
        long userCount = (long) em.createNamedQuery("SystemUser.findCountByUsername").setParameter("username", name).getSingleResult();
        long emailCount = (long) em.createNamedQuery("Customers.findCountByEmail").setParameter("email", email).getSingleResult();

        long asd = (long) em.createNamedQuery("SystemUser.findCountByUsername").setParameter("username", "Mentals").getSingleResult();

        
        System.out.println("***********USERCOUNT*********");
        System.out.println(userCount);
        System.out.println("***********EMAILCOUNT*********");
        System.out.println(emailCount);

        System.out.println("BUDA ASD "+asd);
        
        if (userCount <= 0 && emailCount <= 0) {
            System.out.println("VALID GORUNUYOR");
            isValidate = 0;
        } else if (userCount <= 0 && emailCount > 0) {
            System.out.println("EMAIL DUBLIKE");
            isValidate = 1;
        } else if (userCount > 0 && emailCount <= 0) {
            System.out.println("USERNAME DUBLIKE");
            isValidate = 2;
        } else {
            System.out.println("IKISIDE DUBLIKE");
            isValidate = 3;
        }
        System.out.println("Al Sana Deger: "+isValidate);
        return isValidate;
    }
}
