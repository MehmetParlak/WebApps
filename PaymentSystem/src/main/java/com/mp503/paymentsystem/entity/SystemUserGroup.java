/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mp503.paymentsystem.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mehmetparlak
 */
@Entity
@Table(name = "SYSTEMUSERGROUP", catalog = "", schema = "MEHMET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SystemUserGroup.findAll", query = "SELECT s FROM SystemUserGroup s")
    , @NamedQuery(name = "SystemUserGroup.findById", query = "SELECT s FROM SystemUserGroup s WHERE s.id = :id")
    , @NamedQuery(name = "SystemUserGroup.findByUsername", query = "SELECT s FROM SystemUserGroup s WHERE s.username = :username")
    , @NamedQuery(name = "SystemUserGroup.findByGroupname", query = "SELECT s FROM SystemUserGroup s WHERE s.groupname = :groupname")})
public class SystemUserGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "GROUPNAME")
    private String groupname;

    public SystemUserGroup() {
    }

    public SystemUserGroup(Long id) {
        this.id = id;
    }

    public SystemUserGroup(Long id, String username, String groupname) {
        this.id = id;
        this.username = username;
        this.groupname = groupname;
    }

    public SystemUserGroup(String username, String groupName) {
       this.username = username;
        this.groupname = groupname;    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SystemUserGroup)) {
            return false;
        }
        SystemUserGroup other = (SystemUserGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mp503.paymentsystem.entity.SystemUserGroup[ id=" + id + " ]";
    }
    
}
