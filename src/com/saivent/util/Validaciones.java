/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saivent.util;

import java.awt.event.KeyEvent;

/**
 *
 * @author Elliot
 */
public class Validaciones {

    public boolean soloNumeros(KeyEvent evt) {
        boolean bandera = false;
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
           bandera = true;                              
        }
        return bandera;
    }

}
