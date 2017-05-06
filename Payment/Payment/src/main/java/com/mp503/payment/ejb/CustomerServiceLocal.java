/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mp503.payment.ejb;

import com.mp503.payment.entity.Customers;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mehmetparlak
 */
@Local
public interface CustomerServiceLocal {

    public Customers getCustomerByUsername(String username);

    public Customers getCustomerById(Long id);

    public List<Customers> getAllCustomers();
    
}
