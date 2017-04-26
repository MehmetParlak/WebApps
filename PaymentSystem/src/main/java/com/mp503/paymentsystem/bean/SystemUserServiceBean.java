package com.mp503.paymentsystem.bean;

import com.mp503.paymentsystem.entity.Customer;
import com.mp503.paymentsystem.entity.SystemUser;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SystemUserServiceBean implements SystemUserService {

    @EJB
    private SystemUserGroupService systemUserGroupServiceBean;

    @EJB
    private CustomerService customerServiceBean;

    @EJB
    private SystemUserService systemUserServiceBean;

    @PersistenceContext
    EntityManager em;

    @PostConstruct
    public void postConstruct() {
        System.out.println("SystemUserService: PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("SystemUserService: PreDestroy");
    }

    @TransactionAttribute(REQUIRED)
    @Override
    public void registerSystemUser(String username, String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes("UTF-8"));
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String userPassword = bigInt.toString(16);

            while (userPassword.length() < 64) {
                userPassword = "0" + userPassword;
            }

            SystemUser systemUser = new SystemUser(username, userPassword);
            em.persist(systemUser);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(SystemUserServiceBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @TransactionAttribute(REQUIRED)
    @Override
    public void registerCustomer(String username, String password, String name, String surname, String email, String currency) {
        this.registerSystemUser(username, password);
        systemUserGroupServiceBean.addSystemUserGroup(username, "Customer");
        SystemUser systemUser = this.getSystemUserByUsername(username);
        Customer customer = customerServiceBean.addCustomer(name, surname, email, currency);
        customer.setUserid(systemUser);

    }

    @TransactionAttribute(REQUIRED)
    @Override
    public synchronized void registerAdmin(String username, String password) {
        this.registerSystemUser(username, password);
        systemUserGroupServiceBean.addSystemUserGroup(username, "Admin");
    }

    @TransactionAttribute(NOT_SUPPORTED)
    @Override
    public synchronized SystemUser getSystemUserByUsername(String username) {
        return (SystemUser) em.createNamedQuery("findSystemUserByUsername").setParameter("username", username).getSingleResult();
    }
    
    @TransactionAttribute(NOT_SUPPORTED)
    @Override
    public void CreateAdmin(String username, String password) {
    
        SystemUser admin=new SystemUser(username, password);
        em.persist(admin);
            }
    
    

}
