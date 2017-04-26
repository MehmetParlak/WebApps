package com.mp503.paymentsystem.bean;

import com.mp503.paymentsystem.entity.Customer;
import com.mp503.paymentsystem.entity.SystemUser;
import com.mp503.paymentsystem.entity.Transactions;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CustomerServiceBean implements CustomerService {

    @PersistenceContext
    EntityManager em;

    @PostConstruct
    public void postConstruct() {
        System.out.println("CustomerService: PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("CustomerService: PreDestroy");
    }

    @Override
    public synchronized Customer getCustomer(Customer customer) {
        return customer;
    }//not neccesary

    @Override
    public synchronized Customer getCustomer(Long id) {
        return em.find(Customer.class, id);
    }

    @Override
    public synchronized List<Customer> getAllCustomers() {
        List<Customer> customers = em.createNamedQuery("Customer.findAll").getResultList();
        return customers;
    }

    @Override
    public synchronized List<Transactions> getCustomerTransactions(long id) {
        List<Transactions> transactions = em.createNamedQuery("Transactions.findBySenderId").setParameter("senderid", id).getResultList();
        return transactions;
    }

    @Override
    public synchronized List<Transactions> getAllCustomersTransactions() {
        List<Transactions> transactions = em.createNamedQuery("Transactions.findAll").getResultList();
        return transactions;
    }

    @TransactionAttribute(REQUIRED)
    @Override
    public Customer addCustomer(String firstname, String surname, String email, String currency) {
     double initialBalance = 1000.00;
        if (currency.equals("EUR")){
            initialBalance = initialBalance * 1.20;
        } else if (currency.equals("USD")) {
            initialBalance = initialBalance * 1.28;
        }
        
        Customer customer=new Customer(Long.MIN_VALUE, firstname, surname, email, initialBalance, currency);
        em.persist(customer);
        return customer;
    }


}
