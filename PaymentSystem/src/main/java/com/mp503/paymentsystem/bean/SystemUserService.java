package com.mp503.paymentsystem.bean;

import com.mp503.paymentsystem.entity.SystemUser;
import javax.ejb.Local;


@Local
public interface SystemUserService 
{
    public void registerSystemUser(String username, String password);
    public void registerCustomer(String username, String password, String name, String surname, String email, String currency);
    public void registerAdmin(String username, String password);
    public SystemUser getSystemUserByUsername(String username);

    void CreateAdmin(String username, String password);
}
