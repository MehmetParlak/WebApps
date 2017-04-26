package com.mp503.paymentsystem.bean;

import com.mp503.paymentsystem.entity.SystemUserGroup;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SystemUserGroupServiceBean implements SystemUserGroupService {

    @PersistenceContext
    EntityManager em;

    @TransactionAttribute(REQUIRED)
    @Override
    public synchronized void addSystemUserGroup(String username, String groupName) {
        SystemUserGroup systemUserGroup = new SystemUserGroup(username, groupName);
        em.persist(systemUserGroup);
    }

    @Override
    public synchronized String getGroupByUsername(String username) {
        return em.createNamedQuery("findGroupByUsername").setParameter("username", username).getSingleResult().toString();
    }

    @Override
    public String getGroupByID(Long id) {
        return em.createNamedQuery("Systemusergroup.findById").setParameter("ID", id).getSingleResult().toString();
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("SystemUserGroupService: PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("SystemUserGroupService: PreDestroy");
    }

    @TransactionAttribute(NOT_SUPPORTED)
    @Override
    public void createAdminGroup(String username, String role) {

        SystemUserGroup group = new SystemUserGroup("admin1", "Admin");
        em.persist(group);

    }

}
