/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mp503.paymentsystem.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "USER_DETAILS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserDetails.findAll", query = "SELECT u FROM UserDetails u")
    , @NamedQuery(name = "UserDetails.findByUserId", query = "SELECT u FROM UserDetails u WHERE u.userId = :userId")
    , @NamedQuery(name = "UserDetails.findByFirstName", query = "SELECT u FROM UserDetails u WHERE u.firstName = :firstName")
    , @NamedQuery(name = "UserDetails.findBySurname", query = "SELECT u FROM UserDetails u WHERE u.surname = :surname")
    , @NamedQuery(name = "UserDetails.findByEmailAddress", query = "SELECT u FROM UserDetails u WHERE u.emailAddress = :emailAddress")
    , @NamedQuery(name = "UserDetails.findByPhoneNumber", query = "SELECT u FROM UserDetails u WHERE u.phoneNumber = :phoneNumber")
    , @NamedQuery(name = "UserDetails.findByRegistrationDate", query = "SELECT u FROM UserDetails u WHERE u.registrationDate = :registrationDate")
    , @NamedQuery(name = "UserDetails.findByUserGroups", query = "SELECT u FROM UserDetails u WHERE u.userGroups = :userGroups")})
public class UserDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SURNAME")
    private String surname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "USER_GROUPS")
    private String userGroups;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userDetails")
    private UserAccount userAccount;

    public UserDetails() {
    }

    public UserDetails(Integer userId) {
        this.userId = userId;
    }

    public UserDetails(Integer userId, String firstName, String surname, String emailAddress, String phoneNumber, Date registrationDate, String userGroups) {
        this.userId = userId;
        this.firstName = firstName;
        this.surname = surname;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
        this.userGroups = userGroups;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(String userGroups) {
        this.userGroups = userGroups;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
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
        if (!(object instanceof UserDetails)) {
            return false;
        }
        UserDetails other = (UserDetails) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mp503.paymentsystem.entity.UserDetails[ userId=" + userId + " ]";
    }
    
}
