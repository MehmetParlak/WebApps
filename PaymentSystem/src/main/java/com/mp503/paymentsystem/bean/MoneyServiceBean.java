package com.mp503.paymentsystem.bean;

import com.mp503.paymentsystem.entity.Customer;
import com.mp503.paymentsystem.entity.Transactions;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MoneyServiceBean implements MoneyService {

    @PersistenceContext
    EntityManager em;

    @EJB
    private CustomerService customerservicebean;

//Buraya sağlam kafayla bak!!!!
    @Override
    public void sendMoney(long senderId, long receiverId, double amount, String receiverCurrency) {

        Customer sender = customerservicebean.getCustomer(senderId);
        Customer receiver = customerservicebean.getCustomer(receiverId);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String ddate = dateFormat.format(date);
        amount = convertcurrency(sender.getCurrency(), receiver.getCurrency(), amount);
        if (amount <= sender.getBalance()) {
            sender.setBalance(sender.getBalance() - amount);
            receiver.setBalance(receiver.getBalance() + amount);
            /*long l = (long) em.createNamedQuery("Transactions.findCount").getSingleResult();*/
           long l=1;
            Transactions t = new Transactions(l, senderId, receiverId, amount, ddate, "S", receiverCurrency);
            em.persist(t);
            em.persist(sender);
            em.persist(receiver);
        }

    }

    //The currency and amount are based on the receiver currency everytime
    //Buraya sağlam kafayla bak!!!!

    @Override
    public void requestMoney(long requesterId, long requestedId, double amount, boolean status) {
        Customer requester = customerservicebean.getCustomer(requesterId);
        Customer requested = customerservicebean.getCustomer(requestedId);
 /*long l = (long) em.createNamedQuery("Transactions.findCount").getSingleResult();*/
           long l=1;        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String ddate = dateFormat.format(date);
        amount = convertcurrency(requested.getCurrency(), requester.getCurrency(), amount);
        Transactions t = new Transactions(l, requestedId, requesterId, amount, ddate, "R", requester.getCurrency());
        em.persist(t);

    }

    @Override
    public void acceptMoneyRequest(long id) {
        Transactions t = em.find(Transactions.class, id);
        Customer sender = customerservicebean.getCustomer(t.getSenderId());
        Customer receiver = customerservicebean.getCustomer(t.getReceiverId());

        double amount = convertcurrency(sender.getCurrency(), t.getCurrency(), t.getAmount());
        if (amount <= sender.getBalance()) {

        }

    }

    @Override
    public void denyMoneyRequest(long id) {
    }

    @Override
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
