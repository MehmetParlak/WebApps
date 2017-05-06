/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mp503.payment.ejb;

import com.mp503.payment.entity.Customers;
import com.mp503.payment.entity.Transactions;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mehmetparlak
 */
@Stateless
public class MoneyService implements MoneyServiceLocal {

    @PersistenceContext(unitName = "PaymentPU")
    EntityManager em;

    @EJB
    CustomerServiceLocal customerService;

    @RolesAllowed("Customer")
    @TransactionAttribute(REQUIRED)
    @Override
    public void sendMoney(String senderUsername, String receiverUsername, double amount) {
        Customers sender = customerService.getCustomerByUsername(senderUsername);
        Customers receiver = customerService.getCustomerByUsername(receiverUsername);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String ddate = dateFormat.format(date);
        double convertedAmount = convertcurrency(sender.getCurrency(), receiver.getCurrency(), amount);
        if (amount <= sender.getBalance()) {
            sender.setBalance(sender.getBalance() - amount);
            receiver.setBalance(receiver.getBalance() + convertedAmount);
            String status = "S";//sent
            Transactions t = new Transactions(sender.getUsername(), receiver.getUsername(), amount, ddate, status, sender.getCurrency());
            em.persist(t);
            em.persist(sender);
            em.persist(receiver);
        }

    }

    @RolesAllowed("Customer")
    @TransactionAttribute(REQUIRED)
    @Override
    public void requestMoney(String requested, String requester, double amount) {
        Customers sender = customerService.getCustomerByUsername(requested);
        Customers receiver = customerService.getCustomerByUsername(requester);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String ddate = dateFormat.format(date);
        double convertedAmount = convertcurrency(receiver.getCurrency(), sender.getCurrency(), amount);
        String status = "R";//sent
        Transactions t = new Transactions(sender.getUsername(), receiver.getUsername(), amount, ddate, status, sender.getCurrency());
        em.persist(t);

    }

    @RolesAllowed("Customer")
    @TransactionAttribute(REQUIRED)
    @Override
    public void acceptMoneyRequest(Long id) {
        Transactions t = (Transactions) em.createNamedQuery("Transactions.findByTransactionId").setParameter("transactionId", id).getSingleResult();
        Customers sender = customerService.getCustomerByUsername(t.getSender());
        Customers receiver = customerService.getCustomerByUsername(t.getReceiver());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String ddate = dateFormat.format(date);
        //double convertedAmount = convertcurrency(sender.getCurrency(), receiver.getCurrency(), t.getAmount());
        if (t.getAmount() <= sender.getBalance()) {
            sender.setBalance(sender.getBalance() - t.getAmount());
            receiver.setBalance(receiver.getBalance() + t.getAmount());
            String status = "S";//sent
            em.merge(t);
            em.persist(sender);
            em.persist(receiver);
        }

    }

    @RolesAllowed("Customer")
    @TransactionAttribute(REQUIRED)
    @Override
    public void denyMoneyRequest(Long id) {
        Transactions t = (Transactions) em.createNamedQuery("Transactions.findByTransactionId").setParameter("transactionId", id).getSingleResult();
        t.setStatus("D");
        em.merge(t);
    }//Customers don't see their denied transactions.

    @RolesAllowed("Customer")
    @TransactionAttribute(REQUIRED)
    @Override
    public List<Transactions> getCustomerTransactions(String username) {

        List<Transactions> sentList = (List<Transactions>) em.createNamedQuery("Transactions.findBySender").setParameter("sender", username).getResultList();

        List<Transactions> receivedList = (List<Transactions>) em.createNamedQuery("Transactions.findByReceiver").setParameter("receiver", username).getResultList();

        sentList.addAll(receivedList);//Combined 2 lists

        return sentList;

    }

    @RolesAllowed("Admin")
    @TransactionAttribute(REQUIRED)
    @Override
    public List getAllCustomersTransactions() {
        return (List<Transactions>) em.createNamedQuery("Transactions.findAll").getResultList();
    }

    //@Override
    public double convertcurrency(String senderCurrency, String receiverCurrency, double amount) {

        double convertedAmount = 0;

        if (senderCurrency.equals(receiverCurrency)) {
            convertedAmount = amount;
        } else if (senderCurrency.equals("GBP") && receiverCurrency.equals("USD")) {
            convertedAmount = amount * 1.28;

        } else if (senderCurrency.equals("GBP") && receiverCurrency.equals("EUR")) {
            convertedAmount = amount * 1.20;

        } else if (senderCurrency.equals("USD") && receiverCurrency.equals("GBP")) {
            convertedAmount = amount * 0.78;

        } else if (senderCurrency.equals("USD") && receiverCurrency.equals("EUR")) {
            convertedAmount = amount * 0.93;

        } else if (senderCurrency.equals("EUR") && receiverCurrency.equals("GBP")) {
            convertedAmount = amount * 0.84;

        } else if (senderCurrency.equals("EUR") && receiverCurrency.equals("US")) {
            convertedAmount = amount * 1.07;

        }
        return convertedAmount;
    }
}
