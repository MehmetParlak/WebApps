/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mp503.payment.jsf;

import com.mp503.payment.ejb.SystemServiceLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author mehmetparlak
 */
@Named(value = "registrationBean")
@RequestScoped
public class RegistrationBean {

    @EJB
    private SystemServiceLocal systemService;
    
    private String username;
    private String userpassword;
    private String name;
    private String surname;
    private String email;
 
    
    
    public RegistrationBean() {
    }
    
    public void register(){
        System.out.println("Hello");;
    }

    public SystemServiceLocal getSystemService() {
        return systemService;
    }

    public void setSystemService(SystemServiceLocal systemService) {
        this.systemService = systemService;
    }
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
