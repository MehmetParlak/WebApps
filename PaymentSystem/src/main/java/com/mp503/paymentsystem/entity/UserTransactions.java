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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mehmetparlak
 */
@Entity
@Table(name = "USER_TRANSACTIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserTransactions.findAll", query = "SELECT u FROM UserTransactions u")
    , @NamedQuery(name = "UserTransactions.findById", query = "SELECT u FROM UserTransactions u WHERE u.id = :id")
    , @NamedQuery(name = "UserTransactions.findBySenderId", query = "SELECT u FROM UserTransactions u WHERE u.senderId = :senderId")
    , @NamedQuery(name = "UserTransactions.findByReceiverId", query = "SELECT u FROM UserTransactions u WHERE u.receiverId = :receiverId")
    , @NamedQuery(name = "UserTransactions.findByDate", query = "SELECT u FROM UserTransactions u WHERE u.date = :date")
    , @NamedQuery(name = "UserTransactions.findByAmount", query = "SELECT u FROM UserTransactions u WHERE u.amount = :amount")
    , @NamedQuery(name = "UserTransactions.findByStatus", query = "SELECT u FROM UserTransactions u WHERE u.status = :status")})
public class UserTransactions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SENDER_ID")
    private int senderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RECEIVER_ID")
    private int receiverId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNT")
    private int amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUS")
    private Boolean status;

    public UserTransactions() {
    }

    public UserTransactions(Integer id) {
        this.id = id;
    }

    public UserTransactions(Integer id, int senderId, int receiverId, Date date, int amount, Boolean status) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.date = date;
        this.amount = amount;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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
        if (!(object instanceof UserTransactions)) {
            return false;
        }
        UserTransactions other = (UserTransactions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mp503.paymentsystem.entity.UserTransactions[ id=" + id + " ]";
    }
    
}
