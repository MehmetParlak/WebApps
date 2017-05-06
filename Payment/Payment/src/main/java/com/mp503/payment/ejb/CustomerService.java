/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mp503.payment.ejb;

import com.mp503.payment.entity.Customers;
import java.util.List;
import javax.annotation.security.RolesAllowed;
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
public class CustomerService implements CustomerServiceLocal {

    @PersistenceContext(unitName = "PaymentPU")
    EntityManager em;

    @Override
    public Customers getCustomerByUsername(String username) {
        return (Customers) em.createNamedQuery("Customers.findByUsername").setParameter("username", username).getSingleResult();
    }

    @Override
    public Customers getCustomerById(Long id) {
        return (Customers) em.createNamedQuery("Customers.findByCustomerId").setParameter("customerId", id).getSingleResult();
    }

    @RolesAllowed("Admin")
    @TransactionAttribute(REQUIRED)
    @Override
    public List<Customers> getAllCustomers() {
        return (List<Customers>) em.createNamedQuery("Customers.findAll").getResultList();
    }

}
