/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mp503.payment.jsf;

import com.mp503.payment.ejb.CustomerServiceLocal;
import com.mp503.payment.ejb.MoneyServiceLocal;
import com.mp503.payment.entity.Customers;
import com.mp503.payment.entity.Transactions;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author mehmetparlak
 */
public class CustomerBean {

    @EJB
    private CustomerServiceLocal customerService;

    @EJB
    private MoneyServiceLocal moneyService;

    private String username;
    private String name;
    private String surname;
    private double balance;
    private String currency;
    private List<Customers> customers;
    private List<Transactions> customerTransactions;
    private List<Transactions> moneyRequests;
    private int requstCount = 0;

    //Aslında burdan entity classlara hiç ulaşmamak lazım, şimdilik yapalım sonra düzelteleim
    //Ejb içersindeki metotlardan çağıralım değişkenleri
    public CustomerBean(String username, String name, String surname, double balance, String currency) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.balance = balance;
        this.currency = currency;
    }

    public CustomerBean() {

    }

    public CustomerServiceLocal getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerServiceLocal customerService) {
        this.customerService = customerService;
    }

    public MoneyServiceLocal getMoneyService() {
        return moneyService;
    }

    public void setMoneyService(MoneyServiceLocal moneyService) {
        this.moneyService = moneyService;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Customers> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customers> customers) {
        this.customers = customers;
    }

    public List<Transactions> getCustomerTransactions() {
        return customerTransactions;
    }

    public void setCustomerTransactions(List<Transactions> customerTransactions) {
        this.customerTransactions = customerTransactions;
    }

    public int getRequstCount() {
        return requstCount;
    }

    public void setRequstCount(int requstCount) {
        this.requstCount = requstCount;
    }

    public List<Transactions> getMoneyRequests() {
        return moneyRequests;
    }

    public void setMoneyRequests(List<Transactions> moneyRequests) {
        this.moneyRequests = moneyRequests;
    }

    @PostConstruct
    public void getData() {
        username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        customers = new ArrayList<>();
        Customers c = customerService.getCustomerByUsername(username);
        customerTransactions = moneyService.getCustomerTransactions(username);
        this.name = c.getName();
        this.surname = c.getSurname();
        this.balance = c.getBalance();
        this.currency = c.getCurrency();
        customers.addAll(customerService.getAllCustomers());
        requstCount = moneyService.getMoneyRequest(username).size();
        moneyRequests = moneyService.getMoneyRequest(username);

    }

    public String showRequests() {
        //moneyRequests = moneyService.getMoneyRequest(username);
        return "show";
    }

}
