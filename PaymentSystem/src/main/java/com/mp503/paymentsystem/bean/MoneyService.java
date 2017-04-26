package com.mp503.paymentsystem.bean;

import javax.ejb.Local;


@Local
public interface MoneyService
{
    public void sendMoney(long senderId, long receiverId, double amount, String currency);
    public void requestMoney(long requesterId, long requestedId, double amount, boolean status);
    public void acceptMoneyRequest(long id);
    public void denyMoneyRequest(long id);
    public double convertcurrency(String senderCurrency, String receiverCurrency,double amount);
}
