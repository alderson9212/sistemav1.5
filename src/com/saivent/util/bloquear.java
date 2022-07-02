/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saivent.util;

import com.saivent.principal.Run;
import com.sistema.controller.UsuariosController;
import com.sistema.modelo.UsuarioDTO;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author wilmer
 */
public class bloquear extends JDialog {
     
    UsuariosController controller = new UsuariosController();
    
    String usuario = "";
    public bloquear(JFrame principal, boolean modal,String usuario) {
        super(principal, modal);
        JPasswordField  input = new JPasswordField();
        this.add(input);
        setSize(340, 60);
        setLocationRelativeTo(null);
        setTitle("INGRESA CONTRASEÑA");
        setVisible(true);
        setDefaultCloseOperation(0);
        input.setHorizontalAlignment(JTextField.CENTER);
        this.usuario = usuario;
        desbloquear(input);

    }

    public boolean desbloquear(JPasswordField txt) {
        txt.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(c == KeyEvent.VK_ENTER){
                    UsuarioDTO user = null;// controller.usuarioByNombre(usuario);
                    System.out.println("passosos:"+user.getNombre());
                    if(user!=null){
                       
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
               
            }

            @Override
            public void keyReleased(KeyEvent e) {
              
            }
        });
        return true;
    }
    
    

}
