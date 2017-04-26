package com.mp503.paymentsystem.bean;

import com.mp503.paymentsystem.entity.Customer;
import com.mp503.paymentsystem.entity.SystemUser;
import com.mp503.paymentsystem.entity.Transactions;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CustomerService {

    public Customer addCustomer(String firstname, String surname, String email, String currency);

    public Customer getCustomer(Customer customer);

    public Customer getCustomer(Long id);

    public List<Customer> getAllCustomers();

    public List<Transactions> getCustomerTransactions(long id);

    public List<Transactions> getAllCustomersTransactions();
}
