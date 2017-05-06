package com.mp503.payment.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author mehmetparlak
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "SystemUserGroup.findAll", query = "SELECT s FROM SystemUserGroup s")
    , @NamedQuery(name = "SystemUserGroup.findById", query = "SELECT s FROM SystemUserGroup s WHERE s.id = :id")
    , @NamedQuery(name = "SystemUserGroup.findByUsername", query = "SELECT s FROM SystemUserGroup s WHERE s.username = :username")
    , @NamedQuery(name = "SystemUserGroup.findByGroupname", query = "SELECT s FROM SystemUserGroup s WHERE s.groupname = :groupname")})
public class SystemUserGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @NotNull
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(unique = true)
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
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
        this.groupname = groupName;
    }

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
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.username);
        hash = 59 * hash + Objects.hashCode(this.groupname);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SystemUserGroup other = (SystemUserGroup) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.groupname, other.groupname)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SystemUserGroup{" + "id=" + id + ", username=" + username + ", groupname=" + groupname + '}';
    }

}
