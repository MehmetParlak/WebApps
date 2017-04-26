package com.mp503.paymentsystem.bean;

import javax.ejb.Local;

@Local
public interface SystemUserGroupService 
{
    public void addSystemUserGroup(String username, String groupName);
    public String getGroupByUsername(String username);
    public String getGroupByID(Long id);

    void createAdminGroup(String username, String role);
}
