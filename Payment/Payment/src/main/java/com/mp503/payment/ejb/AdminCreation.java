package com.mp503.payment.ejb;


import com.mp503.payment.entity.SystemUser;
import com.mp503.payment.entity.SystemUserGroup;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Startup
@Singleton
public class AdminCreation 
{
   @PersistenceContext(unitName = "PaymentPU")
    EntityManager em;
    
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
            
            SystemUser admin = new SystemUser("admin1", userPassword);
            SystemUserGroup group = new SystemUserGroup("admin1", "Admin");
            em.persist(admin);
            em.persist(group);
            
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
                Logger.getLogger(AdminCreation.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    @PreDestroy
    public void preDestroy() {
        System.out.println("CreateAdmin: PreDestroy");
    }
}
