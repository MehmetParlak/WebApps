package com.mp503.paymentsystem.jsf;

import com.mp503.paymentsystem.bean.SystemUserService;
import javax.ejb.EJB;

public class RegistrationController {

    @EJB
    private SystemUserService systemUserServiceBean;

    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String currency;

    public RegistrationController() {
    }

    public String register() {
        systemUserServiceBean.registerCustomer(username, password, name, surname, username, currency);
        return "/login.xhtml?faces-redirect=true&includeViewParams=true";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public SystemUserService getSystemUserServiceBean() {
        return systemUserServiceBean;
    }

    public void setSystemUserServiceBean(SystemUserService systemUserServiceBean) {
        this.systemUserServiceBean = systemUserServiceBean;
    }

}
