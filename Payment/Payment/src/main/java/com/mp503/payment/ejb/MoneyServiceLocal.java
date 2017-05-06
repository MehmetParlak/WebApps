/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mp503.payment.ejb;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mehmetparlak
 */
@Local
public interface MoneyServiceLocal {

    public void sendMoney(String senderUsername,String receiverUsername,double amaount);

    public void requestMoney(String senderUsername,String receiverUsername,double amaount);

    public void acceptMoneyRequest(Long id);

    public void denyMoneyRequest(Long id);

    public List getCustomerTransactions(String username);

    public List getAllCustomersTransactions();

    public List getMoneyRequest(String username);

}
