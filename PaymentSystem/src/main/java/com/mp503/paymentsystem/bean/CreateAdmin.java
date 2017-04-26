package com.mp503.paymentsystem.bean;


import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Startup
@Singleton
public class CreateAdmin 
{
    @PersistenceContext
    EntityManager em;
    
    @EJB
    SystemUserServiceBean admin;
    @EJB
    SystemUserGroupServiceBean group;
    
    @PostConstruct
    public void dbInit() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update("password1".getBytes("UTF-8"));
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String userPassword = bigInt.toString(16);
            while ( userPassword.length() < 64 ) {
                userPassword = "0"+userPassword;
            }
            
            /*admin = new SystemUser("admin1", userPassword);
            group = new SystemUserGroup("admin1", "Admin");*/
            
            admin.CreateAdmin("admin1", userPassword);
            group.createAdminGroup("admin1", "Admin");
            //em.persist(admin);
            //em.persist(group);
            
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
                Logger.getLogger(CreateAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    @PreDestroy
    public void preDestroy() {
        System.out.println("CreateAdmin: PreDestroy");
    }
}
