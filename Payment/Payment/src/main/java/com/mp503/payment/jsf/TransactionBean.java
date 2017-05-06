/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mp503.payment.jsf;

import com.mp503.payment.ejb.MoneyServiceLocal;
import com.mp503.payment.entity.Transactions;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author mehmetparlak
 */
@Named(value = "transactionBean")
@RequestScoped
public class TransactionBean {

    @EJB
    private MoneyServiceLocal moneyService;

    private String senderUsername;
    private String username;
    private String name;
    private String surname;
    private String currency;
    private double balance;
    private List<Transactions> transactionList;
    private List<Transactions> allTransactionList;

    public List<Transactions> getAllTransactionList() {
        return allTransactionList;
    }

    public void setAllTransactionList(List<Transactions> allTransactionList) {
        this.allTransactionList = allTransactionList;
    }
    
    

    public List getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List transactionList) {
        this.transactionList = transactionList;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public TransactionBean(String username, String name, String surname, String currency, double balance) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.currency = currency;
        this.balance = balance;
    }

    public TransactionBean(String username) {
        this.username = username;
    }

    public TransactionBean() {
    }

    public String sendMoney() {
        moneyService.sendMoney(senderUsername, username, balance);
        return "sent";

    }

    public String requestMoney() {
        moneyService.requestMoney(username, senderUsername, balance);
        return "requested";
    }

    //For customers
    public String seeTransactions() {
        moneyService.getCustomerTransactions(username);
        return "seeTransactions";
    }

    //For admins
    public String seeAllTransactions() {
        moneyService.getAllCustomersTransactions();
        return "seeAllTransactions";
    }

    @PostConstruct
    public void setSender() {
        senderUsername = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(senderUsername);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        transactionList = moneyService.getCustomerTransactions(senderUsername);
        allTransactionList = moneyService.getAllCustomersTransactions();

    }

}
