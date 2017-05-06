/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mp503.payment.ejb;

import javax.ejb.Stateless;

/**
 *
 * @author mehmetparlak
 */
@Stateless
public class SystemService implements SystemServiceLocal {

    @Override
    public void printHello() {

        System.out.println("**********HELLO MEHMET PARLAK**********");
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
