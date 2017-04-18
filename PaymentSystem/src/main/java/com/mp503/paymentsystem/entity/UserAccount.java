/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mp503.paymentsystem.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mehmetparlak
 */
@Entity
@Table(name = "USER_ACCOUNT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserAccount.findAll", query = "SELECT u FROM UserAccount u")
    , @NamedQuery(name = "UserAccount.findByUserId", query = "SELECT u FROM UserAccount u WHERE u.userId = :userId")
    , @NamedQuery(name = "UserAccount.findByBalance", query = "SELECT u FROM UserAccount u WHERE u.balance = :balance")
    , @NamedQuery(name = "UserAccount.findByAccountType", query = "SELECT u FROM UserAccount u WHERE u.accountType = :accountType")
    , @NamedQuery(name = "UserAccount.findByDate", query = "SELECT u FROM UserAccount u WHERE u.date = :date")})
public class UserAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BALANCE")
    private long balance;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ACCOUNT_TYPE")
    private String accountType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private UserDetails userDetails;

    public UserAccount() {
    }

    public UserAccount(Integer userId) {
        this.userId = userId;
    }

    public UserAccount(Integer userId, long balance, String accountType, Date date) {
        this.userId = userId;
        this.balance = balance;
        this.accountType = accountType;
        this.date = date;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserAccount)) {
            return false;
        }
        UserAccount other = (UserAccount) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mp503.paymentsystem.entity.UserAccount[ userId=" + userId + " ]";
    }
    
}
