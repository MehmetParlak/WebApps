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
    SystemServiceLocal systemService;

    private String username;
    private String userpassword;
    private String name;
    private String surname;
    private String email;
    private String currency;
    private double balance = 1000.0d;

    public double getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public RegistrationBean() {
    }

    public RegistrationBean(String username, String userpassword, String name, String surname, String email, String currency) {
        this.username = username;
        this.userpassword = userpassword;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.currency = currency;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String registerCustomer() {
        systemService.registerCustomer(username, userpassword, name, surname, email, balance, currency);
        return "customer";
    }

    public String registerAdmin() {
        systemService.registerAdmin(username, userpassword);
        return "admin";
    }
}
