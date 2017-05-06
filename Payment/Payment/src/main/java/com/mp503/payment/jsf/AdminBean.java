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
import javax.faces.context.FacesContext;

/**
 *
 * @author mehmetparlak
 */
public class AdminBean {

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
    private List<Transactions> allTransactionList;

    public AdminBean() {
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

    public List<Transactions> getAllTransactionList() {
        return allTransactionList;
    }

    public void setAllTransactionList(List<Transactions> allTransactionList) {
        this.allTransactionList = allTransactionList;
    }


    @PostConstruct
    public void getData() {
        customers=customerService.getAllCustomers();
        allTransactionList = moneyService.getAllCustomersTransactions();

    }

}
